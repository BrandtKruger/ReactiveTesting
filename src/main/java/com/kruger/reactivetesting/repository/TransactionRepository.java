package com.kruger.reactivetesting.repository;

import com.kruger.reactivetesting.model.Transaction;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {

    @Query("SELECT transactionId, amount, amountOther, merchantId, terminalId, posId, operatorId, cardNumber, cvv, cardExpDate FROM transaction tx WHERE tx.cardNumber= :cardNumber")
    Flux<Transaction> findTransactionsByCardNumber(String cardNumber);

    @Query("SELECT transactionId, amount, amountOther, merchantId, terminalId, posId, operatorId, cardNumber, cvv, cardExpDate FROM transaction tx WHERE tx.merchantId= :merchantId")
    Flux<Transaction> findTransactionByMerchantId(String merchantId);

    @Query("SELECT transactionId, amount, amountOther, merchantId, terminalId, posId, operatorId, cardNumber, cvv, cardExpDate FROM transaction tx WHERE tx.terminalId= :terminalId")
    Flux<Transaction> findTransactionByTerminalId(String terminalId);

    @Query("SELECT transactionId, amount, amountOther, merchantId, terminalId, posId, operatorId, cardNumber, cvv, cardExpDate FROM transaction tx WHERE tx.posId = :posId AND tx.operatorId= :operatorId")
    Flux<Transaction> findTransactionByPosIdAndOperatorId(String posId, String operatorId);

    @Query("SELECT transactionId, amount, amountOther, merchantId, terminalId, posId, operatorId, cardNumber, cvv, cardExpDate FROM transaction tx WHERE tx.transactionID = :transactionId")
    Mono<Transaction> findByTransactionId(String transactionId);
}
