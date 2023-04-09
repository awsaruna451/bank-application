package com.aruna.model;

import lombok.*;

import java.math.BigDecimal;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Payment {

    private BigDecimal paymentAmount;
    private Integer accountIdPk;
    private String description;
    private String loanNo;
}
