package com.aruna.entity;


import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "ACCOUNT_TRANSACTION")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class AccountTransactionEntity {
    @Id
    @Column(name = "LOAN_TRANSACTION_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer transactionIdPk;
    @Column(name = "TRANSACTION_AMOUNT")
    private BigDecimal transactionAmount;
    @Column(name = "TRANSACTION_DATE")
    private LocalDateTime transactionDate;
    @Column(name = "TRANSACTION_TYPE")
    private String transactionType;
    @Column(name = "TRANSACTION_DESCRIPTION")
    private String transactionDescription;
    @Column(name = "ACCOUNT_ID_FK")
    private Integer accountIdFk;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createDate;


}
