package com.kruger.reactivetesting.router;

import com.kruger.reactivetesting.handler.TransactionHandler;
import com.kruger.reactivetesting.repository.TransactionRepository;
import com.kruger.reactivetesting.service.TransactionServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.web.reactive.function.server.RequestPredicates.*;

@Configuration
public class TransactionRouter {

    @Autowired
    private TransactionServiceImpl transactionService;

    @Bean
    public RouterFunction<ServerResponse> route() {
        TransactionHandler transactionHandler = new TransactionHandler(transactionService);
        return RouterFunctions
                .route(POST("/transaction").and(contentType(APPLICATION_JSON)), transactionHandler:: createTransaction)
                .andRoute(GET("/transaction/all").and(accept(APPLICATION_JSON)), transactionHandler::findAll)
                .andRoute(GET("/transaction/{transactionId}").and(accept(APPLICATION_JSON)), transactionHandler::getTransactionById)
                .andRoute(GET("/transaction/{cardNumber}").and(accept(APPLICATION_JSON)), transactionHandler::getByCardNumber)
                .andRoute(GET("/transaction/{merchantId}").and(accept(APPLICATION_JSON)), transactionHandler::getByMerchantId)
                .andRoute(GET("/transaction/{terminalId}").and(accept(APPLICATION_JSON)), transactionHandler::getByTerminalId)
                .andRoute(GET("/transaction/{posId}/{operatorId}").and(accept(APPLICATION_JSON)), transactionHandler::getByPosIdAndOperatorId)
                .andRoute(GET("/transaction/{posId}/{operatorId}").and(accept(APPLICATION_JSON)), transactionHandler::getByPosIdAndOperatorId)
                .andRoute(GET("/transaction/events/stream").and(accept(APPLICATION_JSON)), transactionHandler :: streamEvents);
    }

}
