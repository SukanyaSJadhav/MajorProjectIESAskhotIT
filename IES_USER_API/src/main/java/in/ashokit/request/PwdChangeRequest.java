package in.ashokit.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PwdChangeRequest {

    private Integer userId;

    private String email;

    private String pwd;

    private String confirmPwd;


}
