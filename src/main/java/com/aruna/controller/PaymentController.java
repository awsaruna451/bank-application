package com.aruna.controller;

import com.aruna.entity.AccountTransactionEntity;
import com.aruna.model.Payment;
import com.aruna.service.PaymentService;
import com.aruna.service.TransactionService;
import com.aruna.utilities.ApiResponse;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/payment")
@CrossOrigin(origins = "*")
public class PaymentController {
    @Autowired
    private PaymentService paymentService;
    @Autowired
    private TransactionService transactionService;

    @PostMapping
    public ResponseEntity<ApiResponse> payment(@RequestBody Payment payment, HttpServletRequest request) throws Exception {
        ApiResponse<Payment> response = null;
        Payment userResponse =  paymentService.payment(payment);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response=new ApiResponse<>(true, userResponse);
         }
        return ResponseEntity.ok(response);
    }

    @GetMapping("/transaction/{accountId}")
    public ResponseEntity<ApiResponse> getPaymentTransaction(@PathVariable Integer accountId, HttpServletRequest request) throws Exception {
        ApiResponse<List<AccountTransactionEntity>> response = null;
        List<AccountTransactionEntity> userResponse =  transactionService.getTransaction(accountId);
         if (Optional.ofNullable(userResponse).isPresent()) {
             response = new ApiResponse<>(true, userResponse);
         } else {
             response = new ApiResponse<>(true, new ArrayList<>());
         }
        return ResponseEntity.ok(response);
    }

}
