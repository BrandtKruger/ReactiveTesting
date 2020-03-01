package com.kruger.reactivetesting;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.spi.ConnectionFactory;
import org.junit.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import java.util.function.Predicate;

@RunWith(SpringRunner.class)
@EnableR2dbcRepositories
@ExtendWith(MockitoExtension.class)
public class TestTransactionRecord {

    @MockBean
    private TransactionRepository transactionRepository;

    //@Test
    public void testTransactionRecord(){

        Flux<Transaction> savedRecord = Flux.just(new Transaction((long) 15, 1144, 0, "123456",
                "876543", "POS3", "John", "54300000021156", "234", "1221")).flatMap( r -> this.transactionRepository.save(r));

        Flux<Transaction> transactionRecordsFromDB = this.transactionRepository.deleteAll()
                .thenMany(savedRecord)
                .thenMany(this.transactionRepository.findAll());


        Predicate<Transaction> tx = new Predicate<Transaction>() {
            @Override
            public boolean test(Transaction transaction) {
                return transaction.getPosId().equals("POS3");
            }
        };

        StepVerifier
                .create(transactionRecordsFromDB)
                .expectNextMatches(tx)
                .verifyComplete();

    }
}
