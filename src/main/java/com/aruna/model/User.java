package com.aruna.model;

import lombok.*;

@Builder(toBuilder = true)
@Setter
@Getter
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@ToString
@EqualsAndHashCode
public class User {
    private Integer userId;
    private String userName;
    private String password;
    private String email;
    private String customerType;
    private String branchId;
    private Customer customer;
}
