package com.aruna.service;

import com.aruna.entity.*;
import com.aruna.mapper.FacilityEntityMapper;
import com.aruna.mapper.FacilityEntityOutMapper;
import com.aruna.model.Facility;
import com.aruna.repository.*;
import com.aruna.utilities.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
@AllArgsConstructor
public class FacilityRequestImpl implements FacilityRequestService{

    private static int NO_OF_MONTH_FOR_TERM = 12;
    private FacilityEntityMapper facilityEntityMapper;
    private FacilityEntityOutMapper facilityEntityOutMapper;
    private FacilityRequestRepository facilityRepository;
    private RentalFacilityScheduleRepository rentalFacilityScheduleRepository;
    private LoanFacilityRepository loanFacilityRepository;
    private CustomerRepository customerRepository;
    private AccountRepository accountRepository;

    private AccountTransactionRepository accountTransactionRepository;

    @Override
    @Transactional
    public Facility facilityRequest(Facility facility) throws CustomerException {
        Facility facilityResponse = null;
        try {
            Optional<CustomerEntity> customerEntity = customerRepository.findById(facility.getCustomerIdFk());
            if (!customerEntity.isPresent()) {
                throw new Exception("Invalid customer");
            }
            facility.setFacilityRequestDate(LocalDateTime.now());
            facility.setFacilityStatus(FacilityStatus.APPROVED.name());
            facility.setFacilityApproveUser("SYSTEM");
            facilityResponse = facilityEntityOutMapper
                    .map(facilityRepository
                            .save(facilityEntityMapper
                                    .map(facility)));

            if (Optional.ofNullable(facilityResponse).isPresent()) {

                BigDecimal totalInterestAmount = calTotalInterestAmount(facilityResponse.getFacilityAmount(),
                        facilityResponse.getFacilityTerm(), facilityResponse.getFacilityRate());

                BigDecimal monthlyRentalAmount = facilityResponse.getFacilityAmount()
                        .add(totalInterestAmount)
                        .divide(BigDecimal.valueOf(facilityResponse.getFacilityTerm())
                                .multiply(BigDecimal.valueOf(NO_OF_MONTH_FOR_TERM)), 2, RoundingMode.HALF_UP);


                int noOfMonths = facilityResponse.getFacilityTerm() * NO_OF_MONTH_FOR_TERM;

                BigDecimal totalRentalAmount = facilityResponse.getFacilityAmount().add(totalInterestAmount);

                LoanFacilityEntity facilityEntity = LoanFacilityEntity.builder()
                        .requestIdFk(facilityResponse.getRequestId())
                        .totalInterestAmount(totalInterestAmount)
                        .noOfTerms(noOfMonths)
                        .facilityNo(Utilities.getAccountNo("L"))
                        .facilityAmount(facilityResponse.getFacilityAmount())
                        .totalRentalAmount(totalRentalAmount)
                        .rentalAmount(monthlyRentalAmount)
                        .termAmount(BigDecimal.ZERO)
                        .termInterest(BigDecimal.ZERO)
                        .customerIdFk(facilityResponse.getCustomerIdFk())
                        .termType(TermTypes.MONTHLY.name())
                        .totalPaidAmount(BigDecimal.ZERO)
                        .build();

                LoanFacilityEntity response = loanFacilityRepository.save(facilityEntity);

                List<RentalFacilityScheduler> rentalFacilitySchedulers = populateRentalFacilityScheduleTable(noOfMonths, monthlyRentalAmount, response, totalRentalAmount);

                rentalFacilityScheduleRepository.saveAll(rentalFacilitySchedulers);

                AccountEntity entity = accountRepository.findByCustomerIdFk(facilityResponse.getCustomerIdFk());
            List<AccountTransactionEntity> transactionEntities = new ArrayList<>();

                transactionEntities.add(Utilities.getTransactionDetails(response.getFacilityAmount(), entity.getAccountIdPk(),
                        TransactionType.CREDIT.name(), "LOAN AMOUNT"));
                transactionEntities.add(Utilities.getTransactionDetails(response.getFacilityAmount(), entity.getAccountIdPk(),
                        TransactionType.DEBIT.name(), "WITHDRAW"));
                accountTransactionRepository.saveAll(transactionEntities);
            }
        } catch (Exception e) {
            throw new CustomerException(e.getMessage());
        }

        return facilityResponse;
    }

    private BigDecimal calTotalInterestAmount(BigDecimal loanAmount, Integer loanPeriod, float interestRate) {

           BigDecimal total = loanAmount.multiply(BigDecimal.valueOf(loanPeriod))
                   .multiply(BigDecimal.valueOf(interestRate))
                   .divide(BigDecimal.valueOf(100));

           return total;
    }

    private List<RentalFacilityScheduler> populateRentalFacilityScheduleTable(Integer noOfMonths, BigDecimal rentalAmount,
                                                                              LoanFacilityEntity loanFacilityEntity, BigDecimal totalRentalAmount) {
        List<RentalFacilityScheduler> list = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now();
        for(int i = 1; i <= noOfMonths; i++) {
            if (i == noOfMonths) {
                BigDecimal remainTatal = rentalAmount.multiply(BigDecimal.valueOf(noOfMonths-1));
                rentalAmount = totalRentalAmount.subtract(remainTatal);
            }

            RentalFacilityScheduler rentalFacilityScheduler = RentalFacilityScheduler.builder()
                    .rentalAmount(rentalAmount)
                    .rentalNo(i)
                    .rentalDate(now.plusMonths(i))
                    .createDate(now)
                    .loanFacilityEntity(loanFacilityEntity)
                    .rentalStatus(RentalStatus.PENDING.name())
                    .build();

            list.add(rentalFacilityScheduler);

        }

        return list;
    }


}
