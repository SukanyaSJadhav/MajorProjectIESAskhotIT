package in.ashokit.controller;

import in.ashokit.request.LoginRequest;
import in.ashokit.request.SignUpRequest;
import in.ashokit.response.LoginResponse;
import in.ashokit.response.SignUpResponse;
import in.ashokit.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class UserRestController {

    private final UserService userService;

    @Autowired
    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<SignUpResponse> saveUser(@RequestBody SignUpRequest request) {
        SignUpResponse isSaved = userService.saveUser(request);
        return new ResponseEntity<>(isSaved, HttpStatus.OK);
    }

    @PostMapping("/user/login")
    public ResponseEntity<LoginResponse> loginToApplication(@RequestBody LoginRequest request) {
        var result = userService.userLogin(request);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    //this is new branch

}
