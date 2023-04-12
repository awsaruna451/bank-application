package com.aruna.entity;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Setter
@Getter
@Table(name = "CUSTOMER")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class CustomerEntity {

    @Id
    @Column(name = "CUSTOMER_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer customerIdPk;
    @Column(name = "FIRST_NAME")
    private String firstName;
    @Column(name = "LAST_NAME")
    private String lastName;
    @Column(name = "ID_NO")
    private String idNo;
    @Column(name = "DATE_OF_BIRTH")
    private LocalDate dateOfBirth;
    @Column(name = "GENDER")
    private String gender;
    @Column(name = "CIVIL_STATUS")
    private String civilStatus;
    @Column(name = "MOBILE_NUMBER")
    private String mobileNumber;
    @Column(name = "CUSTOMER_STATUS")
    private String customerStatus;
    @Column(name = "ADDRESS_1")
    private String address1;
    @Column(name = "ADDRESS_2")
    private String address2;

    @OneToOne(cascade = CascadeType.ALL , mappedBy = "customerEntity")
    private UserEntity userEntity;
}
