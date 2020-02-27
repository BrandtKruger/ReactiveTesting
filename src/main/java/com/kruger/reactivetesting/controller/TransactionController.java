package com.kruger.reactivetesting.controller;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
@Data
public class TransactionController {

    private final TransactionRepository transactionRepository;
    private final DatabaseClient databaseClient;
    private final ConnectionFactory connectionFactory;

    public TransactionController(TransactionRepository transactionRepository, DatabaseClient databaseClient, ConnectionFactory connectionFactory) {
        this.transactionRepository = transactionRepository;
        this.databaseClient = databaseClient;
        this.connectionFactory = connectionFactory;
    }

    @GetMapping("/")
    public Flux<Transaction> findAll(){
        return transactionRepository.findAll();
    }
}
