package com.cagri.loanapplication.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
@Getter
@Setter
public class CreditScoreDto implements Serializable {

    public Long creditScoreId;
    public int creditScore;
    public int status;
    public Double totalBorrowAmount;
    public Long user;
}