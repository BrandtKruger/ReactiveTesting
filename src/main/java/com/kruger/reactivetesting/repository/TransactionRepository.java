package com.kruger.reactivetesting.repository;

import com.kruger.reactivetesting.model.Transaction;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {

    @Query("SELECT * FROM transaction WHERE cardnumber= $1")
    Flux<Transaction> findTransactionsByCardNumber(String cardNumber);

    @Query("SELECT * FROM transaction WHERE merchantid= $1")
    Flux<Transaction> findTransactionByMerchantId(String merchantId);

    @Query("SELECT * FROM transaction WHERE terminalid= $1")
    Flux<Transaction> findTransactionByTerminalId(String terminalId);

    @Query("SELECT * FROM transaction WHERE posid = $1 AND operatorid= $2")
    Flux<Transaction> findTransactionByPosIdAndOperatorId(String posId, String operatorId);

    @Query("SELECT * FROM transaction WHERE transactionid = $1")
    Mono<Transaction> findByTransactionId(String transactionId);
}
