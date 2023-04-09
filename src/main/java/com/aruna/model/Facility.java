package com.aruna.model;

import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Facility {
    private Integer requestId;
    private String facilityType;
    private BigDecimal facilityAmount;
    private Integer facilityTerm;
    private float facilityRate;
    private Integer customerIdFk;
    private String facilityStatus;
    private LocalDateTime facilityApproveDate;
    private LocalDateTime facilityRequestDate;
    private String facilityApproveUser;
    private String purpose;
}
