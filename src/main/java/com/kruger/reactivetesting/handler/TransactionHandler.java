package com.kruger.reactivetesting.handler;

import com.kruger.reactivetesting.event.TransactionEvent;
import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.service.TransactionServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import java.time.Duration;
import static org.springframework.http.MediaType.APPLICATION_JSON;

@AllArgsConstructor
@Component
public class TransactionHandler {

    private TransactionServiceImpl transactionService;

    public Mono<ServerResponse> createTransaction(ServerRequest request) {
        Mono<Transaction> txMono = request.bodyToMono(Transaction.class).flatMap(transaction -> transactionService.save(transaction));
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txMono, Transaction.class);
    }

    public Mono<ServerResponse> findAll(ServerRequest request) {
        Flux<Transaction> txFlux = transactionService.getAllTransactions();
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txFlux, Transaction.class);
    }

    public Mono<ServerResponse> getTransactionById(ServerRequest request) {
        String transactionId = request.pathVariable("transactionId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Mono<Transaction> txMono = transactionService.getTransactionById(Long.valueOf(transactionId));
        return txMono.flatMap(tx -> ServerResponse.ok()
                .contentType(APPLICATION_JSON)
                .body(BodyInserters.fromObject(tx)))
                .switchIfEmpty(notFound);
    }

    public Mono<ServerResponse> getByCardNumber(ServerRequest request) {
        String cardNumber = request.pathVariable("cardNumber");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Transaction> txFlux = transactionService.getTransactionsByCardNumber(cardNumber);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txFlux, Transaction.class);
    }

    public Mono<ServerResponse> getByMerchantId(ServerRequest request) {
        String merchantId = request.pathVariable("merchantId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Transaction> txFlux = transactionService.getTransactionByMerchantId(merchantId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txFlux, Transaction.class);
    }

    public Mono<ServerResponse> getByTerminalId(ServerRequest request) {
        String terminalId = request.pathVariable("terminalId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Transaction> txFlux = transactionService.getTransactionByTerminalId(terminalId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txFlux, Transaction.class);
    }

    public Mono<ServerResponse> getByPosIdAndOperatorId(ServerRequest request) {
        String posId = request.pathVariable("posId");
        String operatorId = request.pathVariable("operatorId");
        Mono<ServerResponse> notFound = ServerResponse.notFound().build();
        Flux<Transaction> txFlux = transactionService.getTransactionByPosIdAndOperatorId(posId, operatorId);
        return ServerResponse.ok().contentType(APPLICATION_JSON).body(txFlux, Transaction.class);
    }

    public Mono<ServerResponse> streamEvents(ServerRequest serverRequest){
        return ServerResponse.ok().contentType(MediaType.TEXT_EVENT_STREAM).body(Flux.interval(Duration.ofSeconds(1))
                .map(val -> new TransactionEvent("" + val, "Devglan User Event")), TransactionEvent.class);
    }

}
