package com.kruger.reactivetesting.handler;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.service.TransactionServiceImpl;
import lombok.extern.java.Log;
import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.BodyInserters.fromObject;
import static org.springframework.web.reactive.function.server.ServerResponse.notFound;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;
import static reactor.core.publisher.Mono.from;

@Component
public class TransactionHandler {

    private TransactionServiceImpl transactionService;

    public TransactionHandler(TransactionServiceImpl transactionService) {
        this.transactionService = transactionService;
    }

    public Mono<ServerResponse> createTransaction(ServerRequest request) {
        Mono<Transaction> txMono = request.bodyToMono(Transaction.class).flatMap(transaction -> transactionService.save(transaction));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txMono, Transaction.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(transactionService.getAllTransactions(), Transaction.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getTransactionById(ServerRequest request) {
        return from(transactionService.getTransactionById(Long.valueOf(request.pathVariable("transactionId"))))
                .flatMap(u -> ok()
                        .contentType(APPLICATION_JSON)
                        .body(fromObject(u)))
                .switchIfEmpty(notFound().build());
    }

    public Mono<ServerResponse> getByCardNumber(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(transactionService.getTransactionsByCardNumber(request.pathVariable("cardNumber")), Transaction.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getByMerchantId(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(transactionService.getTransactionByMerchantId(request.pathVariable("merchantId")), Transaction.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getByTerminalId(ServerRequest request) {
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(transactionService.getTransactionByTerminalId(request.pathVariable("getTransactionByTerminalId")), Transaction.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

    public Mono<ServerResponse> getByPosIdAndOperatorId(ServerRequest request) {
        String posId = request.pathVariable("posId");
        String operatorId = request.pathVariable("operatorId");
        return ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(transactionService.getTransactionByPosIdAndOperatorId(posId, operatorId), Transaction.class)
                .switchIfEmpty(ServerResponse.noContent().build());
    }

}