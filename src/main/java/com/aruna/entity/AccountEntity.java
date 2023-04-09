package com.aruna.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "ACCOUNT")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class AccountEntity {

    @Id
    @Column(name = "ACCOUNT_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer accountIdPk;
    @Column(name = "ACCOUNT_NO")
    private String accountNo;
    @Column(name = "ACCOUNT_TYPE")
    private String accountType;
    @Column(name = "AVAILABLE_BALANCE")
    private BigDecimal availableBalance;
    @Column(name = "CURRENT_BALANCE")
    private BigDecimal currentBalance;
    @Column(name = "HOLD_BALANCE")
    private BigDecimal holdBalance;
    @Column(name = "ACCOUNT_STATUS")
    private String accountStatus;
    @Column(name = "OPEN_DATE")
    private LocalDateTime openDate;
    @Column(name = "CLOSE_DATE")
    private LocalDateTime closeDate;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;
    @Column(name = "CUSTOMER_ID_FK")
    private Integer customerIdFk;
}
