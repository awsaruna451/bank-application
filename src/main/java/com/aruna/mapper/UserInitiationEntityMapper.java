package com.aruna.mapper;

import com.aruna.entity.CustomerEntity;
import com.aruna.entity.Role;
import com.aruna.entity.UserEntity;
import com.aruna.model.Customer;
import com.aruna.model.User;
import com.aruna.utilities.CustomerStatus;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class UserInitiationEntityMapper {

    private final PasswordEncoder passwordEncoder;

    public UserEntity map(User user) {
        return UserEntity.builder()
                .branchIdFk(user.getBranchId())
                .userName(user.getUserName())
                .userIdPk(user.getUserId())
                .password(passwordEncoder.encode(user.getPassword()))
                .userEmail(user.getEmail())
                .role(Role.USER)
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
