package com.kruger.reactivetesting;

import com.kruger.reactivetesting.configuration.TransactionRestConfiguration;
import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Flux;


@WebFluxTest
@RunWith(SpringRunner.class)
@Import(TransactionRestConfiguration.class)
@ExtendWith(MockitoExtension.class)
public class TestTransactionRest {

    @MockBean
    private TransactionRepository transactionRepository;

    @Autowired
    private WebTestClient webTestClient;

    //@Test
    public void getAllTransactions() throws Exception{

        Mockito.when(this.transactionRepository.findAll())
                .thenReturn(Flux.just(new Transaction((long) 15, 1144, 0, "123456",
                        "876543", "POS3", "John", "54300000021156", "234", "1221"),
                        new Transaction((long) 16, 2343, 0, "123456",
                        "876543", "POS3", "John", "54300000021156", "234", "1221")));

        this.webTestClient
                .get()
                .uri("http://localhost:8080/transactions")
                .exchange()
                .expectStatus().isOk()
                .expectHeader().contentType(MediaType.APPLICATION_JSON)
                .expectBody()
                .jsonPath("@.[0].transactionId").isEqualTo("15")
                .jsonPath("@.[0].cardNumber").isEqualTo("54300000021156");

    }
}
