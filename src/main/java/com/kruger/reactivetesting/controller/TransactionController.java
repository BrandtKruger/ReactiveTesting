package com.kruger.reactivetesting.controller;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.spi.ConnectionFactory;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

import java.net.URI;
import java.util.concurrent.Flow;

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
    Publisher<Transaction> findAll(){
        return this.transactionRepository.findAll();
    }
    @GetMapping("/cardNumber")
    Publisher<Transaction> getByCardNumber(@PathVariable("cardNumber") String cardNumber){
        return this.transactionRepository.getTransactionsByCardNumber(cardNumber);
    }

    @GetMapping("/{merchantId}")
    Publisher<Transaction> getByMerchantId(@PathVariable("merchantId") String merchantId){
        return this.transactionRepository.getTransactionByMerchantId(merchantId);
    }

    @GetMapping("/{terminalId}")
    Publisher<Transaction> getByTerminalId(@PathVariable("terminalId") String terminalId){
        return this.transactionRepository.getTransactionByTerminalId(terminalId);
    }

    @GetMapping("/{posId}/{operatorId}")
    Publisher<Transaction> getByPosIdAndOperatorId(@PathVariable("posId") String posId,
                                                   @PathVariable("operatorId") String operatorId){
        return this.transactionRepository.getTransactionByPosIdAndOperatorId(posId, operatorId);
    }

    @PostMapping
    Publisher<ResponseEntity<Transaction>> create(@RequestBody Transaction transaction){
        return this.transactionRepository
                .save(transaction)
                .map( tr -> ResponseEntity.created(URI.create("/transactions/" + tr.getTransactionId())).
                        contentType(MediaType.APPLICATION_JSON)
                        .build());
    }

    @DeleteMapping("/delete/{transactionId}")
    Publisher<Transaction> deleteByTransactionId(@PathVariable("transactionId") Long transactionId){
        return this.transactionRepository.deleteById(transactionId);
    }
}
