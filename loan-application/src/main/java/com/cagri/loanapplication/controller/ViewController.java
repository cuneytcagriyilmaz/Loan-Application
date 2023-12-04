package com.cagri.loanapplication.controller;

import com.cagri.loanapplication.LoanApplication;
import com.cagri.loanapplication.dto.CreditScoreDto;
import com.cagri.loanapplication.dto.UserDto;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.exception.LoginException;
import com.cagri.loanapplication.exception.RegisterException;
import com.cagri.loanapplication.service.CreditScoreService;
import com.cagri.loanapplication.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
public class ViewController {

    UserService userService;
    CreditScoreService creditScoreService;

    public ViewController(UserService userService, CreditScoreService creditScoreService) {
        this.userService = userService;
        this.creditScoreService = creditScoreService;
    }
    @ModelAttribute("user")
    public UserDto userDto() {
        return new UserDto();
    }

    @ModelAttribute("credit")
    public CreditScoreDto creditScoreDto() {
        return new CreditScoreDto();
    }

    @GetMapping("/userpage")
    public String showAllUser(Model model) {
        List<UserDto> listUsers = userService.getAllUsers();
        model.addAttribute("listUsers", listUsers);
        return "/userpage";
    }


    @GetMapping("/creditpage")
    public String showAllCredit(Model model) {
        List<CreditScoreDto> listCredits = creditScoreService.getAllCredits();
        model.addAttribute("listCredits", listCredits);
        return "/creditpage";
    }

    @GetMapping("/registration")
    public String showRegistrationForm() {
        return "registration";
    }

    @GetMapping("/addcreditscore")
    public String showCreditScoreForm() {
        return "addcreditscore";
    }

    @PostMapping("/registration")
    public String registerUserAccount(@ModelAttribute("user") UserDto userDto) throws RegisterException {
        userService.register(userDto);
        return "redirect:/registration?success";
    }

    @PostMapping("/addcreditscore")
    public String addCredit(@ModelAttribute("credit") CreditScoreDto creditScoreDto) {
        Optional<User> idValue = Optional.ofNullable(userService.getUserByUserId(creditScoreDto.getUser()));
        if (idValue.isEmpty()) {
            LoanApplication.logger.error("Credit Score is not added");
        } else {
            LoanApplication.logger.info("Credit Score is added");
        }
        creditScoreService.addCredit(creditScoreDto);
        return "redirect:/addcreditscore?success";
    }

    @GetMapping("/")
    public String showLogin() {
        return "login";
    }

    //Check for Credentials
    @PostMapping("/login")
    public String login(@ModelAttribute(name = "loginForm") UserDto userDto, Model model) throws LoginException {
        String userTc = userDto.getUserTc();
        String passwd = userDto.getPassword();
        userService.login(userDto);
        if (userTc.equals(userDto.getUserTc()) && passwd.equals(userDto.getPassword())) {
            model.addAttribute("userTc", userTc);
            model.addAttribute("passwd", passwd);
            return "/index";
        } else

            model.addAttribute("error", "Incorrect userTc & Password");
        return "/login";
    }


    @GetMapping("/showFormForUpdate/{userId}")
    public String showFormForUpdate(@PathVariable(value = "userId") Long userId, Model model) {
        User user = userService.getUserByUserId(userId);
        userService.updateUser(user);
        model.addAttribute("user", user);
        return "updateUser";
    }
    @PostMapping("/update")
    public String updateUserAccount(@ModelAttribute("user") UserDto user) throws RegisterException {
        userService.updateUser(User.fromResource(user));
        return "redirect:/userpage";

    }



    @GetMapping("/deleteUser/{userId}")
    public String deleteUser(@PathVariable(value = "userId") Long userId) {
        userService.deleteUser(userId);
        return "redirect:/userpage";
    }


    @GetMapping("/makeanapplication")
    public String showMakeAnApplication(){
        return "makeanapplication";
    }

    @PostMapping("/makeanapplication")
    public String makeAnApplication(UserDto userDto) {
        creditScoreService.makeAnApplication(userDto.getBirthdate(),userDto.getUserTc());
        return "redirect:/makeanapplication?success";
    }

}
