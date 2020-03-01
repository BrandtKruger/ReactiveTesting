package com.kruger.reactivetesting.controller;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import com.kruger.reactivetesting.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.reactivestreams.Publisher;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.net.URI;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionServiceImpl transactionService;
    private TransactionRepository transactionRepository;

    @GetMapping("/all")
    Publisher<Transaction> findAll(){
        return this.transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}" )
    public Mono<ResponseEntity<Transaction>> getById(@PathVariable("transactionId") Long transactionId){
        return transactionService.getTransactionById(transactionId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{cardNumber}")
    Publisher<Transaction> getByCardNumber(@PathVariable("cardNumber") String cardNumber){
        return this.transactionService.getTransactionsByCardNumber(cardNumber);
    }

    @GetMapping("/{merchantId}")
    Publisher<Transaction> getByMerchantId(@PathVariable("merchantId") String merchantId){
        return this.transactionService.getTransactionByMerchantId(merchantId);
    }

    @GetMapping("/{terminalId}")
    Publisher<Transaction> getByTerminalId(@PathVariable("terminalId") String terminalId){
        return this.transactionService.getTransactionByTerminalId(terminalId);
    }

    @GetMapping("/{posId}/{operatorId}")
    Publisher<Transaction> getByPosIdAndOperatorId(@PathVariable("posId") String posId,
                                                   @PathVariable("operatorId") String operatorId){
        return this.transactionService.getTransactionByPosIdAndOperatorId(posId, operatorId);
    }

    @PostMapping
    Publisher<ResponseEntity<Transaction>> create(@RequestBody Transaction transaction){
        return this.transactionService
                .save(transaction)
                .map( tr -> ResponseEntity.created(URI.create("/transactions/" + tr.getTransactionId())).
                        contentType(MediaType.APPLICATION_JSON)
                        .build());
    }

//    @DeleteMapping("/delete/{transactionId}")
//    Publisher<Transaction> deleteByTransactionId(@PathVariable("transactionId") Long transactionId){
//        return this.transactionRepository.deleteById(transactionId);
//    }
}
