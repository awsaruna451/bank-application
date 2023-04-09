package com.aruna.model;

import lombok.*;

import java.time.LocalDate;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class Customer {
    private Integer customerIdPk;
    private String firstName;
    private String lastName;
    private String idNo;
    private LocalDate dateOfBirth;
    private String gender;
    private String civilStatus;
    private String mobileNumber;
    private String customerStatus;
    private String address1;
    private String address2;

}
