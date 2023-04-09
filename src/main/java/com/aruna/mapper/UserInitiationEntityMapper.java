package com.aruna.mapper;

import com.aruna.entity.CustomerEntity;
import com.aruna.entity.UserEntity;
import com.aruna.model.Customer;
import com.aruna.model.User;
import com.aruna.utilities.CustomerStatus;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class UserInitiationEntityMapper {

    public UserEntity map(User user) {
        return UserEntity.builder()
                .branchIdFk(user.getBranchId())
                .userName(user.getUserName())
                .userIdPk(user.getUserId())
                .password(user.getPassword())
                .userEmail(user.getEmail())
                .customerType(user.getCustomerType())
                .build();
    }


    public CustomerEntity mapCustomerEntity(User user) {
        Customer customer = user.getCustomer();
        return CustomerEntity.builder()
                .customerStatus(CustomerStatus.ACTIVE.toString())
                .dateOfBirth(customer.getDateOfBirth())
                .gender(customer.getGender())
                .lastName(customer.getLastName())
                .firstName(customer.getFirstName())
                .idNo(customer.getIdNo())
                .civilStatus(customer.getCivilStatus())
                .mobileNumber(customer.getMobileNumber())
                .address1(customer.getAddress1())
                .address2(customer.getAddress2())
                .build();
    }

}
