package in.ashokit.service;

import in.ashokit.entity.UserInfoEntity;
import in.ashokit.repo.UserInfoRepository;
import in.ashokit.request.LoginRequest;
import in.ashokit.request.PwdChangeRequest;
import in.ashokit.request.SignUpRequest;
import in.ashokit.response.DashboardReponse;
import in.ashokit.response.LoginResponse;
import in.ashokit.response.SignUpResponse;
import in.ashokit.utils.EmailUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private final UserInfoRepository userRepo;
    private final EmailUtils emailUtils;

    public UserServiceImpl(UserInfoRepository userRepo, EmailUtils emailUtils) {
        this.userRepo = userRepo;
        this.emailUtils = emailUtils;
    }

    @Override
    public SignUpResponse saveUser(SignUpRequest request) {
        SignUpResponse response = new SignUpResponse("");

        UserInfoEntity userExist = userRepo.findByEmail(request.getEmail());
        if (userExist != null) {
            response.setResponseMsg("Duplicate email");
            return response;
        }
        String tempPwd = generateTempPwd();
        request.setPwd(tempPwd);
        request.setPwdChanged(false);
        UserInfoEntity entity = new UserInfoEntity();
        BeanUtils.copyProperties(request, entity);
        userRepo.save(entity);

        String subject = "IES - Account Created";
        String body = "Your Password to Login: " + tempPwd;
        boolean isSent = emailUtils.sendEmail(request.getEmail(), subject, body);
        if (isSent) {
            response.setResponseMsg("successfully created " + entity);
            return response;
        }
        response.setResponseMsg("Not able to create " + entity);
        return response;
    }

    @Override
    public LoginResponse userLogin(LoginRequest request) {
        LoginResponse response = new LoginResponse();
        UserInfoEntity entity = new UserInfoEntity();
        entity.setEmail(request.getEmail());
        entity.setPwd(request.getPwd());

        Example<UserInfoEntity> of = Example.of(entity);
        List<UserInfoEntity> entities = userRepo.findAll(of);
        if (!entities.isEmpty()) {
            UserInfoEntity user = entities.get(0);
            if (user.isPwdChanged()) {
                //second login
                response.setPwdChanged(true);
                response.setValidLogin(true);
                response.setUserId(user.getUserId());
                response.setUserType(user.getUserType());
                //set Dashboard data
                DashboardReponse dashboard = new DashboardReponse();
                dashboard.setPlansCount(6L);
                dashboard.setBenefitAmtTotal(3400.00);
                dashboard.setCitizenApCnt(10001L);
                dashboard.setCitizenDnCnt(500L);
                response.setDashboard(dashboard);
            } else {
                //first login
                response.setPwdChanged(false);
                response.setValidLogin(true);
            }
        } else {
            response.setValidLogin(false);
        }
        return response;
    }

    @Override
    public LoginResponse updatePwd(PwdChangeRequest request) {
        LoginResponse response = new LoginResponse();
        Integer userId = request.getUserId();
        Optional<UserInfoEntity> findById = userRepo.findById(userId);
        if (findById.isEmpty()) {
            UserInfoEntity entity = findById.get();
            entity.setPwd(request.getPwd());
            entity.setPwdChanged(true);
            userRepo.save(entity);

            response.setValidLogin(true);
            DashboardReponse dashboard = new DashboardReponse();
            dashboard.setPlansCount(6L);
            dashboard.setBenefitAmtTotal(3400.00);
            dashboard.setCitizenApCnt(10001L);
            dashboard.setCitizenDnCnt(500L);
            response.setDashboard(dashboard);
        }
        return response;
    }

    @Override
    public boolean recoverPwd(String email) {
        var exist = userRepo.findByEmail(email);
        if (exist == null) {
            return false;
        }
        String subject = "IES RECOVER PASSWORD";
        String body = "Your Password: " + exist.getPwd();
        return emailUtils.sendEmail(email, subject, body);
    }

    public static String generateTempPwd() {
        String allChars = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
        int length = 12;
        SecureRandom random = new SecureRandom();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < length; i++) {
            int randomIndex = random.nextInt(allChars.length());
            password.append(allChars.charAt(randomIndex));
        }
        return password.toString();
    }

}
