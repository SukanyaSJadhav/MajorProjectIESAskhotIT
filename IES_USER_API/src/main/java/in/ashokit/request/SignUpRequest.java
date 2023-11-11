package in.ashokit.request;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
//Binding class
public class SignUpRequest {
	
	private Integer userId;
	
	private String name;
	
	private String email;
	
	private String pwd;
	
	private String gender;
	
	private LocalDate dob;
	
	private Long phno;
	
	private Long ssn;
	
	private String userType;
	
	private boolean pwdChanged;
}
