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
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.data.r2dbc.core.DatabaseClient;
import org.springframework.test.context.junit4.SpringRunner;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;

import javax.sql.DataSource;

@RunWith(SpringRunner.class)
//@R2dbcTest
@AutoConfigureTestDatabase(connection = EmbeddedDatabaseConnection.H2)
@ExtendWith(MockitoExtension.class)
public class TransactionRepositoryTest {

    @Mock
    private TransactionRepository transactionRepository;

    @Autowired
    DataSource dataSource;

    @Autowired
    private ConnectionFactory connectionFactory;

    @Test
    public void findAllByCardNumber() throws Exception{

        DatabaseClient databaseClient = DatabaseClient.create(connectionFactory);

        Flux<Transaction> flux = this.transactionRepository.deleteAll()
                .thenMany(Flux.just("54300000023456", "54300000021156", "54300000023456"))
                .flatMap(card -> this.transactionRepository.save(new Transaction((long) 15, 1144, 0, "123456",
                        "876543", "POS3", "John", "54300000021156", "234", "1221")))
                .thenMany(this.transactionRepository.getTransactionsByCardNumber("54300000023456"));

        StepVerifier
                .create(flux)
                .expectNextCount(2)
                .verifyComplete();

    }
}
