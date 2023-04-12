package com.aruna.model;

import com.aruna.entity.AccountEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
public class FacilityDetails {

    private List<LoanDetails> loans;

    private AccountEntity account;
}
