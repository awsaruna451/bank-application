package com.aruna.service;

import com.aruna.model.Payment;
import com.aruna.model.User;
import com.aruna.utilities.CustomerException;

public interface PaymentService {

    Payment payment(Payment payment) throws CustomerException;
}
