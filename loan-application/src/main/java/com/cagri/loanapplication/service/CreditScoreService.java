package com.cagri.loanapplication.service;

import com.cagri.loanapplication.dto.CreditScoreDto;
import com.cagri.loanapplication.entity.User;
import com.cagri.loanapplication.response.CreditScoreResponse;

import java.util.List;

public interface CreditScoreService {


    CreditScoreResponse addCredit(CreditScoreDto creditScoreDto);

    User makeAnApplication(String birthdate, String userTc);

    List<CreditScoreDto> getAllCredits();



}
