package com.aruna.mapper;

import com.aruna.entity.CustomerEntity;
import com.aruna.entity.UserEntity;
import com.aruna.model.Customer;
import com.aruna.model.User;
import com.aruna.utilities.CustomerStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInitiationEntityOutMapper {

    public User map(UserEntity user) {
        return User.builder()
                .userId(user.getUserIdPk())
                .branchId(user.getBranchIdFk())
                .userName(user.getUsername())
                .password(user.getPassword())
                .email(user.getUserEmail())
                .customerType(user.getCustomerType())
                .customer(Optional.ofNullable(user.getCustomerEntity())
                        .map(this::mapCustomer).orElse(null)
                ).build();
    }


    public Customer mapCustomer(CustomerEntity customerEntity) {
        return Customer.builder()
                .customerStatus(customerEntity.getCustomerStatus())
                .dateOfBirth(customerEntity.getDateOfBirth())
                .gender(customerEntity.getGender())
                .lastName(customerEntity.getLastName())
                .firstName(customerEntity.getFirstName())
                .idNo(customerEntity.getIdNo())
                .civilStatus(customerEntity.getCivilStatus())
                .mobileNumber(customerEntity.getMobileNumber())
                .customerIdPk(customerEntity.getCustomerIdPk())
                .address1(customerEntity.getAddress1())
                .address2(customerEntity.getAddress2())
                .build();
    }

}
