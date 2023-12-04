package com.cagri.loanapplication.controller;

import com.cagri.loanapplication.LoanApplication;
import com.cagri.loanapplication.dto.CreditScoreDto;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.repository.CreditScoreRepository;
import com.cagri.loanapplication.repository.UserRepository;
import com.cagri.loanapplication.response.CreditScoreResponse;
import com.cagri.loanapplication.service.CreditScoreService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/credit")
public class CreditScoreController {

    CreditScoreService creditScoreService;
    UserRepository userRepository;
    CreditScoreRepository creditScoreRepository;

    //used for Dependency Injection
    public CreditScoreController(CreditScoreService creditScoreService, UserRepository userRepository,CreditScoreRepository creditScoreRepository
    ) {
        this.creditScoreRepository = creditScoreRepository;
        this.creditScoreService = creditScoreService;
        this.userRepository = userRepository;
    }

    //A written Request to add credit information
    @PostMapping("/addCredit")
    public CreditScoreResponse addCreditScore(@RequestBody @Valid final CreditScoreDto creditScoreDto) {
        Optional<User> idValue = userRepository.findById(creditScoreDto.getUser());
        if (idValue.isEmpty()) {
            LoanApplication.logger.error("Credit Score is not added");
        } else {
            LoanApplication.logger.info("Credit Score is added");
        }
        return creditScoreService.addCredit(creditScoreDto);
    }

    //Written for application request
    @GetMapping("/application/{birthdate}/{userTc}")
    public ResponseEntity<User> makeAnApplication(@PathVariable String birthdate,
                                                  @PathVariable String userTc) {
        return new ResponseEntity<>(creditScoreService.makeAnApplication(birthdate, userTc), HttpStatus.ACCEPTED);
    }

}
