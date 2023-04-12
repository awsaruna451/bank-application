package com.aruna.service;

import com.aruna.entity.*;
import com.aruna.mapper.UserInitiationEntityMapper;
import com.aruna.mapper.UserInitiationEntityOutMapper;
import com.aruna.model.AuthenticationRequest;
import com.aruna.model.User;
import com.aruna.repository.AccountRepository;
import com.aruna.repository.CustomerRepository;
import com.aruna.repository.TokenRepository;
import com.aruna.repository.UserRepository;
import com.aruna.utilities.*;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    private final AuthenticationManager authenticationManager;

    private final PasswordEncoder passwordEncoder;

    private TokenRepository tokenRepository;

    private JwtService jwtService;
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

            UserEntity respnseEntity = userRepository.save(userEntity1);

            responseUser = initiationEntityOutMapper
                    .map(respnseEntity);

            var jwtToken = jwtService.generateToken(userEntity1);
            var refreshToken = jwtService.generateRefreshToken(userEntity1);
            saveUserToken(respnseEntity, jwtToken);

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

    private void saveUserToken(UserEntity user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUserName(),
                        request.getPassword()
                )
        );
        var user = userRepository.findByUserName(request.getUserName());

        var jwtToken = jwtService.generateToken(user);
        var refreshToken = jwtService.generateRefreshToken(user);
        revokeAllUserTokens(user);
        saveUserToken(user, jwtToken);
        return AuthenticationResponse.builder()
                .accessToken(jwtToken)
                .customerId(user.getCustomerEntity().getCustomerIdPk())
                .refreshToken(refreshToken)
                .build();
    }

    private void revokeAllUserTokens(UserEntity user) {
        var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getUserIdPk());
        if (validUserTokens.isEmpty())
            return;
        validUserTokens.forEach(token -> {
            token.setExpired(true);
            token.setRevoked(true);
        });
        tokenRepository.saveAll(validUserTokens);
    }

}
