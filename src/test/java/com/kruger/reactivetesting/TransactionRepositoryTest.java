package com.kruger.reactivetesting;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import org.springframework.data.r2dbc.core.DatabaseClient;

@RunWith(SpringRunner.class)
@EnableR2dbcRepositories
@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    DatabaseClient database;

    @Test
    public void findAllByCardNumber() throws Exception{
        Flux<Transaction> flux = this.transactionRepository.deleteAll()
                .thenMany(Flux.just("54300000023456", "54300000021156", "54300000023456"))
                .flatMap(card -> this.transactionRepository.save(new Transaction((long) 15, 1144, 0, "123456",
                        "876543", "POS3", "John", "54300000021156", "234", "1221")))
                .thenMany(this.transactionRepository.findByCardNumber("54300000023456"));

        StepVerifier
                .create(flux)
                .expectNextCount(2)
                .verifyComplete();

    }
}
