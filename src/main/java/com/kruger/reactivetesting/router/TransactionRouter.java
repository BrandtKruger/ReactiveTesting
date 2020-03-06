package com.kruger.reactivetesting.router;

import com.kruger.reactivetesting.handler.TransactionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.config.EnableWebFlux;
import org.springframework.web.reactive.function.server.RouterFunction;;
import org.springframework.web.reactive.function.server.ServerResponse;
import static org.springframework.web.reactive.function.server.RouterFunctions.route;

@Configuration
@EnableWebFlux
public class TransactionRouter {

    @Bean
    public RouterFunction<ServerResponse> routerFunction(TransactionHandler transactionHandler) {

        return route()
                .POST("/transaction", transactionHandler::createTransaction)
                .GET("/transaction", transactionHandler::findAll)
                .GET("/transaction/transactionId/{transactionId}", transactionHandler::getTransactionById)
                .GET("/transaction/cardNumber/{cardNumber}", transactionHandler::getByCardNumber)
                .GET("/transaction/merchantId/{merchantId}", transactionHandler::getByMerchantId)
                .GET("/transaction/terminalId/{terminalId}", transactionHandler::getByTerminalId)
                .GET("/transaction/posIdAndOperatorId/{posId}/{operatorId}", transactionHandler::getByPosIdAndOperatorId)
                .build();
    }

}
