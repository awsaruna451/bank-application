package com.aruna.entity;


import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "LOAN_FACILITY")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class LoanFacilityEntity {
    @Id
    @Column(name = "FACILITY_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer facilityIdPk;
    @Column(name = "REQUEST_ID_FK")
    private Integer requestIdFk;
    @Column(name = "CUSTOMER_ID_FK")
    private Integer customerIdFk;
    @Column(name = "FACILITY_AMOUNT")
    private BigDecimal facilityAmount;
    @Column(name = "TOTAL_INTEREST_AMOUNT")
    private BigDecimal totalInterestAmount;
    @Column(name = "TOTAL_PAID_AMOUNT")
    private BigDecimal totalPaidAmount;
    @Column(name = "RENTAL_AMOUNT")
    private BigDecimal rentalAmount;
    @Column(name = "TOTAL_RENTAL_AMOUNT")
    private BigDecimal totalRentalAmount;
    @Column(name = "NO_TERM")
    private Integer noOfTerms;
    @Column(name = "TERM_TYPE")
    private String termType;
    @Column(name = "TERM_AMOUNTS")
    private BigDecimal termAmount;
    @Column(name = "TERM_INTERESTS")
    private BigDecimal termInterest;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
    @Column(name = "FACILITY_NO")
    private String facilityNo;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "loanFacilityEntity", cascade = CascadeType.ALL)
    private List<RentalFacilityScheduler> termFacilityDetails;

}
