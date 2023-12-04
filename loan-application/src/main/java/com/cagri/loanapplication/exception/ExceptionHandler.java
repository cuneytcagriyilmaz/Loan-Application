package com.cagri.loanapplication.exception;

import com.cagri.loanapplication.response.Meta;
import com.cagri.loanapplication.response.UserResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.WebRequest;

public class ExceptionHandler extends Exception{

    @org.springframework.web.bind.annotation.ExceptionHandler(RegisterException.class)
    public ResponseEntity<Object> generateUser(RegisterException registerException, WebRequest webRequest) {
        UserResponse userResponse = new UserResponse();
        Meta response = new Meta();
        response.errorCode = 2000;
        response.errorDescription = "Enter the information correctly.";
        userResponse.meta = response;
        ResponseEntity<Object> objectResponseEntity = new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
        return objectResponseEntity;
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(RegisterException.class)
    public ResponseEntity<Object> login(LoginException loginException, WebRequest webRequest) {
        UserResponse userResponse = new UserResponse();
        Meta response = new Meta();
        response.errorCode = 3000;
        response.errorDescription = "Username or Password is wrong. Please enter the information correctly";
        userResponse.meta = response;
        return new ResponseEntity<>(userResponse, HttpStatus.NOT_FOUND);
    }


}
