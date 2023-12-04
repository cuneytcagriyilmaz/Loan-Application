package com.cagri.loanapplication.service.impl;

import com.cagri.loanapplication.LoanApplication;
import com.cagri.loanapplication.dto.CreditScoreDto;
import com.cagri.loanapplication.entity.CreditScore;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.repository.CreditScoreRepository;
import com.cagri.loanapplication.repository.UserRepository;
import com.cagri.loanapplication.response.CreditScoreResponse;
import com.cagri.loanapplication.response.Meta;
import com.cagri.loanapplication.service.CreditScoreService;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;


@Service
public class CreditScoreServiceImpl implements CreditScoreService {

    CreditScoreRepository creditScoreRepository;
    UserServiceImpl userServiceImpl;
    UserRepository userRepository;

    //Used for Dependency Injection
    public CreditScoreServiceImpl(CreditScoreRepository creditScoreRepository, UserServiceImpl userServiceImpl, UserRepository userRepository) {
        this.userServiceImpl = userServiceImpl;
        this.creditScoreRepository = creditScoreRepository;
        this.userRepository = userRepository;
    }

    //Service method written to fill credit information
    public CreditScoreResponse addCredit(CreditScoreDto creditScoreDto) {
        CreditScoreResponse creditScoreResponse = new CreditScoreResponse();
        creditScoreResponse.meta=new Meta(2000, "Adding credit information successful");
        CreditScore creditScore = new CreditScore();
        creditScore.setStatus(creditScoreDto.getStatus());
        mapToEntity(creditScoreDto, creditScore);
        creditScoreRepository.save(creditScore).getCreditScoreId();
        return creditScoreResponse;
    }

    //It was written for the negative message
    public void negativeMessage(CreditScore creditScore){
        String feedBackMessage = " SMS SIMULATION ==> The message 'Your Credit Card application was rejected because your Credit Score is "+creditScore.getCreditScore()+ "' was sent to the phone number " +creditScore.getUser().getPhoneNumber() + ".";
       // LoanApplication.logger.info(feedBackMessage);
        System.out.println(feedBackMessage);
    }

    //It was written for the positive message
    public void positiveMessage(CreditScore creditScore){
        //"Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL")
        String feedBackMessage = " SMS SIMULATION ==>  The message 'Your Credit Card application has been approved. Your credit card limit: " + creditScore.getTotalBorrowAmount() + " TL.' was sent to the phone number " +creditScore.getUser().getPhoneNumber() + ".";
        //LoanApplication.logger.info(feedBackMessage);
        System.out.println(feedBackMessage);
    }


    //Service method written for loan application
    public User makeAnApplication(String birthdate, String userTc){
        double creditLimitRate = 4;
        CreditScore creditScore = creditScoreRepository.findCreditScoreByUser_BirthdateAndUser_UserTc(birthdate, userTc);
        if (creditScore.getCreditScore() < 500) {
            creditScore.setStatus(0);
            LoanApplication.logger.info("Your Credit Card application has been rejected because your credit score is " + creditScore.getCreditScore());
            negativeMessage(creditScore);
        } else if (creditScore.getCreditScore() >= 500 && creditScore.getCreditScore() < 1000) {
            if (creditScore.getUser().getMonthlyIncome() < 5000) {
                creditScore.setStatus(1);
                creditScore.setTotalBorrowAmount(10000.0);
                LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                positiveMessage(creditScore);
            } else if (creditScore.getUser().getMonthlyIncome() >= 5000 && creditScore.getUser().getMonthlyIncome() < 10000) {
                creditScore.setStatus(1);
                if (creditScore.getUser().getGuarentee() > 0) {
                    double guarenteeRate = creditScore.getUser().getGuarentee() / 5.0;
                    creditScore.setTotalBorrowAmount(20000.0 + guarenteeRate);
                    LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                    positiveMessage(creditScore);

                } else {
                    creditScore.setTotalBorrowAmount(20000.0);
                    LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                    positiveMessage(creditScore);

                }
            } else {
                double creditAmount = creditScore.getUser().getMonthlyIncome() * creditLimitRate / 2;
                creditScore.setStatus(1);
                if (creditScore.getUser().getGuarentee() > 0) {
                    double guarenteeRate = creditScore.getUser().getGuarentee() / 4.0;
                    creditScore.setTotalBorrowAmount(creditAmount + guarenteeRate);
                    LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                    positiveMessage(creditScore);

                } else {
                    creditScore.setTotalBorrowAmount(creditAmount);
                    LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                    positiveMessage(creditScore);

                }
            }

        } else {
            double creditAmount = creditScore.getUser().getMonthlyIncome() * creditLimitRate;
            creditScore.setStatus(1);
            if (creditScore.getUser().getGuarentee() > 0) {
                double guarenteeRate = creditScore.getUser().getGuarentee() / 2.0;
                creditScore.setTotalBorrowAmount(creditAmount + guarenteeRate);
                LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                positiveMessage(creditScore);

            } else {
                creditScore.setTotalBorrowAmount(creditAmount);
                LoanApplication.logger.info("Your Credit Card application has been approved. Your credit card limit:" + creditScore.getTotalBorrowAmount() + " TL");
                positiveMessage(creditScore);

            }
        }
        creditScoreRepository.save(creditScore);
        return creditScore.getUser();
    }
//////////////////////////////////////////
    @Override
    public List<CreditScoreDto> getAllCredits() {
        List<CreditScoreDto> creditScoreDtos = new ArrayList<>();
        Iterable<CreditScore> creditScores = creditScoreRepository.findAll();
        for (CreditScore creditScore: creditScores){
            CreditScoreDto creditScoreDto = new CreditScoreDto();
            mapToDTO(creditScore,creditScoreDto);
            creditScoreDtos.add(creditScoreDto);
        }
        return creditScoreDtos;
    }


    private CreditScoreDto mapToDTO(final CreditScore creditScore, final CreditScoreDto creditScoreDto) {
        creditScoreDto.setCreditScoreId(creditScore.getCreditScoreId());
        creditScoreDto.setCreditScore(creditScore.getCreditScore());
        creditScoreDto.setStatus(creditScore.getStatus());
        creditScoreDto.setTotalBorrowAmount(creditScore.getTotalBorrowAmount());
        creditScoreDto.setUser(creditScore.getUser() == null ? null : creditScore.getUser().getUserId());
        return creditScoreDto;
    }

    private CreditScore mapToEntity(final CreditScoreDto creditScoreDto, final CreditScore creditScore) {

        creditScore.setCreditScoreId(creditScore.getCreditScoreId());
        creditScore.setCreditScore(creditScoreDto.getCreditScore());
        creditScore.setStatus(creditScore.getStatus());

        final User user = creditScoreDto.getUser() == null ? null : userRepository.findById(creditScoreDto.getUser())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "user not found"));
        creditScore.setUser(user);

        return creditScore;
    }



}