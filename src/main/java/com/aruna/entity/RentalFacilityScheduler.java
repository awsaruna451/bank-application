package com.aruna.entity;


import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "RENTAL_FACILITY_SCHEDULER")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class RentalFacilityScheduler {
    @Id
    @Column(name = "SCHEDULE_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer scheduleIdPk;
    @Column(name = "RENTAL_AMOUNT")
    private BigDecimal rentalAmount;
    @Column(name = "RENTAL_NO")
    private Integer rentalNo;
    @Column(name = "RENTAL_DATE")
    private LocalDateTime rentalDate;
    @Column(name = "CREATE_DATE")
    private LocalDateTime createDate;
    @Column(name = "UPDATE_DATE")
    private LocalDateTime updateDate;
    @Column(name = "RENTAL_STATUS")
    private String rentalStatus;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "facility_id_fk")
    private LoanFacilityEntity loanFacilityEntity;

}
