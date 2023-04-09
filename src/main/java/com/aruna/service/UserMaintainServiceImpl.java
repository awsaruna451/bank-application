package com.aruna.service;

import com.aruna.entity.AccountEntity;
import com.aruna.entity.CustomerEntity;
import com.aruna.entity.UserEntity;
import com.aruna.mapper.UserInitiationEntityMapper;
import com.aruna.mapper.UserInitiationEntityOutMapper;
import com.aruna.model.User;
import com.aruna.repository.AccountRepository;
import com.aruna.repository.CustomerRepository;
import com.aruna.repository.UserRepository;
import com.aruna.utilities.*;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Optional;

@AllArgsConstructor
@Service
public class UserMaintainServiceImpl implements UserMaintainService {

    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;
    private UserInitiationEntityMapper entityMapper;
    private UserInitiationEntityOutMapper initiationEntityOutMapper;
    @Override
    public User createUser(User user) throws CustomerException {
        User responseUser = null;
        try {

            UserEntity userEntity = userRepository.findByUserName(user.getUserName());

            if (Optional.ofNullable(userEntity).isPresent()) {
                throw new CustomerException("User exists");
            }

            CustomerEntity customerEntity = customerRepository.findByIdNo(user.getCustomer().getIdNo());

            if (Optional.ofNullable(customerEntity).isPresent()) {
                throw new IllegalArgumentException("Customer exists");
            }

            UserEntity userEntity1 = entityMapper.map(user);
            CustomerEntity customerEntity1 = entityMapper.mapCustomerEntity(user);
            userEntity1.setCustomerEntity(customerEntity1);

            responseUser = initiationEntityOutMapper
                    .map(userRepository
                            .save(userEntity1));


            AccountEntity account = null;
            if (Optional.ofNullable(responseUser).isPresent()
                    && CustomerStatus.ACTIVE.name().equals(responseUser.getCustomer().getCustomerStatus())) {  // assume this bank officer approval is granted
                account = AccountEntity
                        .builder()
                        .createdDate(LocalDateTime.now())
                        .accountNo(Utilities.getAccountNo(null))
                        .accountStatus(AccountStatus.ACTIVE.name())
                        .accountType(AccountType.SAVINGS.name())
                        .currentBalance(BigDecimal.ZERO)
                        .availableBalance(BigDecimal.ZERO)
                        .holdBalance(BigDecimal.ZERO)
                        .openDate(LocalDateTime.now())
                        .customerIdFk(responseUser.getCustomer().getCustomerIdPk())
                        .build();

            }
            accountRepository.save(account);
        }
        catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }

        return responseUser;
    }

}
