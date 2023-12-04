package com.cagri.loanapplication.controller;

import com.cagri.loanapplication.dto.UserDto;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.exception.LoginException;
import com.cagri.loanapplication.exception.RegisterException;
import com.cagri.loanapplication.response.UserResponse;
import com.cagri.loanapplication.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    //used for Dependency Injection
    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    //Rest request written for the user to register
    @PostMapping("/register")
    public UserResponse register(@RequestBody UserDto userDto) throws RegisterException {
        logger.info("Registration success");
        return userService.register(userDto);
    }

    //Rest request written for the user to login
    @PostMapping("/login")
    public UserResponse login(@RequestBody UserDto userDto) throws LoginException {
        logger.info("Login process success.");
        return userService.login(userDto);
    }

    //Rest request written for deletion of user registration
    @DeleteMapping("/delete/{userId}")
    public String deleteUser(@PathVariable Long userId, @RequestHeader("AuthorizationTicket") String ticket) {
        if (userService.authorizeUser(ticket)) {
            userService.deleteUser(userId);
            logger.info("Deletion successful.");
            return "User has been deleted with id:" + userId;
        } else
            logger.info("Unexpected Ticket");
        return null;
    }

    //Rest request written to update user information
    @PutMapping("/update")
    public User updateUser(@RequestBody User user, @RequestHeader("AuthorizationTicket") String ticket) {
        if (userService.authorizeUser(ticket)) {
            logger.info("Update process successful");
            return userService.updateUser(user);

        } else {
            logger.info("Unexpected ticked");

            return null;
        }
    }

    //Rest request written to attract all users
    @GetMapping("/users")
    public List<UserDto> getAllUsers(@RequestHeader("AuthorizationTicket") String ticket) {
        if (userService.authorizeUser(ticket)) {
            logger.info("All Users returned is successfully");
            return userService.getAllUsers();
        } else {
            logger.info("Unexpected ticket");
            return null;
        }
    }

/*
    @GetMapping("/userAll")
    public String getAllUsers(Model model) {
        List<UserDto> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "userAll";


    }
    */

}