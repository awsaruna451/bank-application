package com.aruna.service;

import com.aruna.entity.AccountEntity;
import com.aruna.entity.AccountTransactionEntity;
import com.aruna.entity.LoanFacilityEntity;
import com.aruna.entity.RentalFacilityScheduler;
import com.aruna.model.Payment;
import com.aruna.repository.AccountRepository;
import com.aruna.repository.AccountTransactionRepository;
import com.aruna.repository.LoanFacilityRepository;
import com.aruna.repository.RentalFacilityScheduleRepository;
import com.aruna.utilities.CustomerException;
import com.aruna.utilities.RentalStatus;
import com.aruna.utilities.TransactionType;
import com.aruna.utilities.Utilities;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class PaymentServiceImpl implements PaymentService{
    private AccountRepository accountRepository;
    private LoanFacilityRepository loanFacilityRepository;
    private RentalFacilityScheduleRepository rentalFacilityScheduleRepository;

    private AccountTransactionRepository accountTransactionRepository;
    @Override
    @Transactional
    public Payment payment(Payment payment) throws CustomerException {

        Optional<AccountEntity> account = accountRepository.findById(payment.getAccountIdPk());
        if (!account.isPresent()) {
            throw new CustomerException("Invalid account number");
        }
        AccountEntity accountEntity = account.get();
        accountEntity.setCurrentBalance(accountEntity.getCurrentBalance().add(payment.getPaymentAmount()));
        accountEntity.setAvailableBalance(accountEntity.getCurrentBalance().subtract(accountEntity.getHoldBalance()));

       AccountTransactionEntity transactionEntity = Utilities.getTransactionDetails(payment.getPaymentAmount(),
                accountEntity.getAccountIdPk(), TransactionType.CREDIT.name(), "LOAN PAYMENT");
        accountTransactionRepository.save(transactionEntity);


        LoanFacilityEntity loanFacilityEntity = loanFacilityRepository.findByFacilityNo(payment.getLoanNo());

        if (!Optional.ofNullable(loanFacilityEntity).isPresent()) {
            throw new CustomerException("Invalid loan number");
        }

        BigDecimal rentalPayment = payment.getPaymentAmount();
        BigDecimal dueAmount = BigDecimal.ZERO;
        List<RentalFacilityScheduler> dueList = new ArrayList<>();
        for(RentalFacilityScheduler rentalFacilityScheduler:loanFacilityEntity.getTermFacilityDetails()) {
            if (RentalStatus.PENDING.name().equals(rentalFacilityScheduler.getRentalStatus())) {
                   if (rentalPayment.compareTo(rentalFacilityScheduler.getRentalAmount()) >= 0) {
                       rentalFacilityScheduler.setRentalStatus(RentalStatus.DUE.name());
                       rentalFacilityScheduler.setUpdateDate(LocalDateTime.now());
                       rentalPayment = rentalPayment.subtract(rentalFacilityScheduler.getRentalAmount());
                       dueAmount = dueAmount.add(rentalFacilityScheduler.getRentalAmount());
                       dueList.add(rentalFacilityScheduler);
                   } else {
                       break;
                   }
            }
        }
        rentalFacilityScheduleRepository.saveAll(dueList);

      transactionEntity = Utilities.getTransactionDetails(dueAmount,
                accountEntity.getAccountIdPk(), TransactionType.DEBIT.name(), "LOAN DUE");

        accountTransactionRepository.save(transactionEntity);
        BigDecimal currentBalance = accountEntity.getCurrentBalance().subtract(dueAmount);
        accountEntity.setCurrentBalance(currentBalance);
        accountEntity.setAvailableBalance(currentBalance.subtract(accountEntity.getHoldBalance()));

        accountRepository.save(accountEntity);

        return payment;
    }
}
