package com.cagri.loanapplication.entity;

import com.cagri.loanapplication.dto.UserDto;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.log4j.Log4j2;

import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
@Entity
@Table(name = "users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long userId;

    @Column(name = "user_Tc")
    private String userTc;
    @Column(name = "user_Name")
    private String userName;
    @Column(name = "user_Surname")
    private String userSurname;
    @NotNull
    private String phoneNumber;
    @NotNull
    private Long monthlyIncome;
    @NotNull
    private String birthdate;
    @NotNull
    private int guarentee;
    @NotNull
    private String password;

    public static User fromResource(UserDto userDto) {
        User user = new User();
        user.setUserId(userDto.userId);
        user.setUserTc(userDto.userTc);
        user.setUserName(userDto.userName);
        user.setUserSurname(userDto.userSurname);
        user.setPhoneNumber(userDto.phoneNumber);
        user.setMonthlyIncome(userDto.monthlyIncome);
        user.setBirthdate(userDto.birthdate);
        user.setGuarentee(userDto.guarentee);
        user.setPassword(userDto.password);
        return user;
    }

    public static UserDto toResource(User user) {
        UserDto userDto = new UserDto();
        userDto.userId = user.getUserId();
        userDto.userTc = user.getUserTc();
        userDto.userName = user.getUserName();
        userDto.userSurname = user.getUserSurname();
        userDto.phoneNumber = user.getPhoneNumber();
        userDto.monthlyIncome = user.getMonthlyIncome();
        userDto.birthdate = user.getBirthdate();
        userDto.guarentee = user.getGuarentee();
        userDto.password = user.getPassword();
        return userDto;
    }

}
