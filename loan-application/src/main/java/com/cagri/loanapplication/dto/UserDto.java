package com.cagri.loanapplication.dto;

import com.cagri.loanapplication.entity.CreditScore;
import lombok.Getter;
import lombok.Setter;
import java.io.Serializable;

@Getter
@Setter
public class UserDto implements Serializable {
    public Long userId;
    public String userTc;
    public String userName;
    public String userSurname;
    public String phoneNumber;
    public Long monthlyIncome;
    public String birthdate;
    public int guarentee;
    public String password;
    public CreditScore creditScore;

}