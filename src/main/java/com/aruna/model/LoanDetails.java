package com.aruna.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@Builder
public class LoanDetails {

    private String loanNo;
    private Integer id;
    private BigDecimal rentalAmount;
}
