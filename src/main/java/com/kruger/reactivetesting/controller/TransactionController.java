package com.kruger.reactivetesting.controller;

import com.kruger.reactivetesting.event.TransactionEvent;
import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import com.kruger.reactivetesting.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.net.URI;
import java.time.Duration;

@Data
@AllArgsConstructor
@NoArgsConstructor
@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private TransactionServiceImpl transactionService;

    @GetMapping("/all")
    public Flux<Transaction> findAll(){
        return this.transactionService.getAllTransactions();
    }

    @GetMapping("/{transactionId}" )
    public Mono<ResponseEntity<Transaction>> getTransactionById(@PathVariable("transactionId") Long transactionId){
        return transactionService.getTransactionById(transactionId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{cardNumber}")
    public Flux<ResponseEntity<Transaction>> getByCardNumber(@PathVariable("cardNumber") String cardNumber){
        return transactionService.getTransactionsByCardNumber(cardNumber)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{merchantId}")
    public Flux<ResponseEntity<Transaction>> getByMerchantId(@PathVariable("merchantId") String merchantId){
        return transactionService.getTransactionByMerchantId(merchantId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{terminalId}")
    public Flux<ResponseEntity<Transaction>> getByTerminalId(@PathVariable("terminalId") String terminalId){
        return transactionService.getTransactionByMerchantId(terminalId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @GetMapping("/{posId}/{operatorId}")
    public Flux<ResponseEntity<Transaction>> getByPosIdAndOperatorId(@PathVariable("posId") String posId,
                                                                     @PathVariable("operatorId") String operatorId){
        return transactionService.getTransactionByPosIdAndOperatorId(posId, operatorId)
                .map(transaction -> ResponseEntity.ok(transaction))
                .defaultIfEmpty(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Mono<ResponseEntity<Transaction>> createTransaction(@RequestBody Transaction transaction){
        return transactionService.save(transaction)
                .map(savedTransaction -> ResponseEntity.ok(savedTransaction));
    }

    @GetMapping(value = "/events", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
    public Flux<TransactionEvent> emitEvents(){
        return Flux.interval(Duration.ofSeconds(1))
                .map(val -> new TransactionEvent("" + val, "Devglan Transaction Event"));
    }

}
