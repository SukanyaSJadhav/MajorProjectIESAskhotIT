package in.ashokit.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "USER_INFO")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class UserInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
