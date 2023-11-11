package in.ashokit.service;

import in.ashokit.request.LoginRequest;
import in.ashokit.request.PwdChangeRequest;
import in.ashokit.request.SignUpRequest;
import in.ashokit.response.LoginResponse;
import in.ashokit.response.SignUpResponse;

public interface UserService {

    SignUpResponse saveUser(SignUpRequest request);

    LoginResponse userLogin(LoginRequest request);

    LoginResponse updatePwd(PwdChangeRequest request);

    boolean recoverPwd(String email);


}
