package com.cagri.loanapplication.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import lombok.extern.log4j.Log4j2;
import java.io.Serializable;

@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Log4j2
@Entity
public class CreditScore implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long creditScoreId;
    @NotNull
    private int creditScore;
    private int status;
    private Double totalBorrowAmount;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;




}
