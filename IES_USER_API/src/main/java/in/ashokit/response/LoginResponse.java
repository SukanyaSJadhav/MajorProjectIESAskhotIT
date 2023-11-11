package in.ashokit.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LoginResponse {
	
	private Integer userId;
	
	private String name;
	
	private String userType;
	
	private boolean isValidLogin;
	
	private boolean pwdChanged;
	
	private DashboardReponse dashboard;
	

}
