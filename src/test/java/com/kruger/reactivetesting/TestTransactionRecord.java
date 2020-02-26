package com.kruger.reactivetesting;

import com.kruger.reactivetesting.model.Transaction;
import com.kruger.reactivetesting.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Mock
    private TransactionRepository transactionRepository;
    @Autowired
    DatabaseClient database;

    @Test
    public void testTransactionRecord(){

        Flux<Transaction> savedRecord = Flux.just(new Transaction((long) 15, 1144, 0, "123456",
                "876543", "POS3", "John", "54300000021156", "234", "1221")).flatMap( r -> this.database.insert().into(Transaction.class));

        Flux<Transaction> transactionRecordsFromDB = this. reactiveMongoTemplate
                .dropCollection(Transaction.class)
                .thenMany(savedRecord)
                .thenMany(this.reactiveMongoTemplate.findAll(Transaction.class));


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
