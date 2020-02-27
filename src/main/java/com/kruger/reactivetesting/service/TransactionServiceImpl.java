package com.kruger.reactivetesting.service;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.Serializable;

@Data
@Service
public class TransactionServiceImpl implements iTransactionService, Serializable {

    private TransactionRepository transactionRepository;
    private DatabaseClient databaseClient;
    private final ConnectionFactory connectionFactory;

    public TransactionServiceImpl(TransactionRepository transactionRepository, DatabaseClient databaseClient, ConnectionFactory connectionFactory) {
        this.transactionRepository = transactionRepository;
        this.databaseClient = databaseClient;
        this.connectionFactory = connectionFactory;
    }

    @Override
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @Override
    public Mono<Transaction> getTransactionById(Long transactionId) {
        return transactionRepository.findById(transactionId);
    }

    @Override
    public Flux<Transaction> getTransactionsByCardNumber(String cardNumber) {
        return transactionRepository.getTransactionsByCardNumber(cardNumber);
    }

    @Override
    public Flux<Transaction> getTransactionByMerchantId(String merchantId) {
        return transactionRepository.getTransactionByMerchantId(merchantId);
    }

    @Override
    public Flux<Transaction> getTransactionByTerminalId(String terminalId) {
        return transactionRepository.getTransactionByTerminalId(terminalId);
    }

    @Override
    public Flux<Transaction> getTransactionByPosIdAndOperatorId(String posId, String operatorId) {
        return transactionRepository.getTransactionByPosIdAndOperatorId(posId, operatorId);
    }
}
