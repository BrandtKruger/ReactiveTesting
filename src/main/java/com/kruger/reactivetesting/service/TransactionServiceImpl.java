package com.kruger.reactivetesting.service;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionServiceImpl implements iTransactionService {

    private TransactionRepository transactionRepository;

    @Override
    public Flux<Transaction> getAllTransactions() {
        return null;
    }

    @Override
    public Mono<Transaction> getTransactionById(Long transactionId) {
        return null;
    }

    @Override
    public Flux<Transaction> getTransactionsByCardNumber(String cardNumber) {
        return null;
    }

    @Override
    public Flux<Transaction> getTransactionByMerchantId(String merchantId) {
        return null;
    }

    @Override
    public Flux<Transaction> getTransactionByTerminalId(String terminalId) {
        return null;
    }

    @Override
    public Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId) {
        return null;
    }
}
