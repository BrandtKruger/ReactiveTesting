package com.kruger.reactivetesting.service;

import com.kruger.reactivetesting.model.Transaction;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface iTransactionService {

    Flux<Transaction> getAllTransactions();

    Mono<Transaction> getTransactionById(Long transactionId);

    Flux<Transaction> getTransactionsByCardNumber(String cardNumber);

    Flux<Transaction> getTransactionByMerchantId(String merchantId);

    Flux<Transaction> getTransactionByTerminalId(String terminalId);

    Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId);
}
