package com.cagri.loanapplication.response;

import com.cagri.loanapplication.dto.UserDto;

import java.io.Serializable;

public class UserResponse extends BaseResponse implements Serializable {
    public UserDto userDto;
    public String authorizationTicket;
}