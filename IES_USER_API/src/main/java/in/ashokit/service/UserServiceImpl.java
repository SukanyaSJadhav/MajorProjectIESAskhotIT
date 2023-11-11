package in.ashokit.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import in.ashokit.repo.UserInfoRepository;
import in.ashokit.request.LoginRequest;
import in.ashokit.request.PwdChangeRequest;
import in.ashokit.request.SignUpRequest;
import in.ashokit.response.LoginResponse;

@Service
public class UserServiceImpl implements UserService {

	
	@Autowired
	private UserInfoRepository userRepo;
	@Override
	public boolean saveUser(SignUpRequest request) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public LoginResponse userLogin(LoginRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public LoginResponse updatePwd(PwdChangeRequest request) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean recoverPwd(String email) {
		// TODO Auto-generated method stub
		return false;
	}

}
