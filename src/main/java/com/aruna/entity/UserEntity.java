package com.aruna.entity;


import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Setter
@Getter
@Table(name = "USER")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Data
public class UserEntity {

    @Id
    @Column(name = "USER_ID_PK", length = 20)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer userIdPk;
    @Column(name = "USER_NAME")
    private String userName;
    @Column(name = "PASSWORD")
    private String password;
    @Column(name = "EMAIL")
    private String userEmail;
    @Column(name = "CUSTOMER_TYPE")
    private String customerType;
    @Column(name = "BRANCH_ID_FK")
    private String branchIdFk;
    @Column(name = "CREATED_DATE")
    private LocalDateTime createdDate;


    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "CUSTOMER_ID_FK", referencedColumnName = "CUSTOMER_ID_PK")
    private CustomerEntity customerEntity;
}
