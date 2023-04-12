package com.aruna.entity;


import lombok.*;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "FACILITY_REQUEST")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class FacilityRequestEntity {
    @Id
    @Column(name = "REQUEST_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer requestIdPk;
    @Column(name = "TYPE")
    private String type;
    @Column(name = "FACILITY_AMOUNT")
    private BigDecimal amount;
    @Column(name = "TERM")
    private Integer term;
    @Column(name = "RATE")
    private float rate;
    @Column(name = "CUSTOMER_ID_FK")
    private Integer customerIdFk;
    @Column(name = "STATUS")
    private String status;
    @Column(name = "REQUEST_DATE")
    private LocalDateTime requestDate;
    @Column(name = "APPROVE_USER")
    private String approvedUser;
    @Column(name = "APPROVE_DATE")
    private LocalDateTime approvedDate;
    @Column(name = "PURPOSE")
    private String purpose;

}
