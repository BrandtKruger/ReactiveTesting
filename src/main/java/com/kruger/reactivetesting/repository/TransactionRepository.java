package com.kruger.reactivetesting.repository;

import com.kruger.reactivetesting.model.Transaction;
import org.springframework.data.r2dbc.repository.query.Query;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;

@Repository
public interface TransactionRepository extends ReactiveCrudRepository<Transaction, Long> {

    @Query("SELECT * FROM transaction WHERE  cardNumber= :cardNumber")
    Flux<Transaction> getTransactionsByCardNumber(String cardNumber);

    @Query("SELECT * FROM transaction WHERE  merchantId= :merchantId")
    Flux<Transaction> getTransactionByMerchantId(String merchantId);

    @Query("SELECT * FROM transaction WHERE  terminalId= :terminalId")
    Flux<Transaction> getTransactionByTerminalId(String terminalId);

    @Query("SELECT * FROM transaction WHERE posId = :posId AND operatorId= :operatorId")
    Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId);

}
