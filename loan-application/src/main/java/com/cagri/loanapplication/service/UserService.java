package com.cagri.loanapplication.service;


import com.cagri.loanapplication.dto.UserDto;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.exception.LoginException;
import com.cagri.loanapplication.exception.RegisterException;
import com.cagri.loanapplication.response.UserResponse;
import com.cagri.loanapplication.exception.*;

import java.util.List;

public interface UserService {
    UserResponse register(UserDto userDto) throws RegisterException;

    UserResponse login(UserDto userDto) throws LoginException;

    void deleteUser(Long userId);

    User updateUser(User user);

    List<UserDto> getAllUsers();
///////////////////////////////////

    User getUserByUserId(Long id);

    Boolean authorizeUser(String ticket);

    User getUserByUserBirthdateAndUserTc(String birthdate, String userTc);



}
