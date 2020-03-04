package com.kruger.reactivetesting;

import com.kruger.reactivetesting.repository.TransactionRepository;
import io.r2dbc.postgresql.PostgresqlConnectionConfiguration;
import io.r2dbc.postgresql.PostgresqlConnectionFactory;
import io.r2dbc.spi.ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.function.DatabaseClient;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;
import org.springframework.data.r2dbc.repository.support.R2dbcRepositoryFactory;
import org.springframework.data.relational.core.mapping.RelationalMappingContext;

@Configuration
@EnableR2dbcRepositories(basePackages = "com.kruger.reactivetesting.repository")
public class PostgresConfig extends AbstractR2dbcConfiguration {

    @Value("${spring.r2dbc.host}")
    private String host;
    @Value("${spring.r2dbc.port}")
    private int port;
    @Value("${spring.r2dbc.database}")
    private String database;
    @Value("${spring.r2dbc.username}")
    private String username;
    @Value("${spring.r2dbc.password}")
    private String password;
    @Value("${spring.r2dbc.url}")
    private String url;

    @Bean
    //@Override
    public PostgresqlConnectionFactory connectionFactory() {
        return new PostgresqlConnectionFactory(PostgresqlConnectionConfiguration
                .builder()
                .host(host)
                .database(database)
                .username(username)
                .password(password)
                .port(port)
                .build());
    }

    @Bean
    DatabaseClient databaseClient(ConnectionFactory connectionFactory){
        return DatabaseClient.builder()
                .connectionFactory(connectionFactory)
                .build();
    }

//    @Bean
//    R2dbcRepositoryFactory r2dbcRepositoryFactory(DatabaseClient client){
//        RelationalMappingContext context = new RelationalMappingContext();
//        context.afterPropertiesSet();
//
//        return new R2dbcRepositoryFactory(client, context);
//    }

    @Bean
    TransactionRepository transactionRepository(R2dbcRepositoryFactory factory){
        return factory.getRepository(TransactionRepository.class);
    }
}
