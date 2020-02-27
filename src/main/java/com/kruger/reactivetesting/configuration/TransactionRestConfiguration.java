package com.kruger.reactivetesting.configuration;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.http.MediaType;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import static org.springframework.web.reactive.function.server.RouterFunctions.route;
import static org.springframework.web.reactive.function.server.ServerResponse.ok;

@Configuration
@EnableR2dbcRepositories
public class TransactionRestConfiguration {

    @Bean
    RouterFunction<ServerResponse> routes(TransactionRepository transactionRepository){
        return route(RequestPredicates.GET("/transactions"), request -> ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(transactionRepository.findAll(), Transaction.class));
    }
}
