package com.kruger.reactivetesting.configuration;

import io.r2dbc.h2.H2ConnectionConfiguration;
import io.r2dbc.h2.H2ConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.r2dbc.config.AbstractR2dbcConfiguration;
import org.springframework.data.r2dbc.repository.config.EnableR2dbcRepositories;

@Configuration
@EnableR2dbcRepositories
public class DatabaseConfiguration extends AbstractR2dbcConfiguration {

   @Value("${spring.datasource.url}") private String host;
//    @Value("${spring.data.postgres.port}") private int port;
//    @Value("${spring.data.postgres.database}") private String database;
    @Value("${spring.datasource.username}") private String username;
//    @Value("${spring.data.postgres.password}") private String password;

    @Override
    @Bean
    public H2ConnectionFactory connectionFactory() {
        return new H2ConnectionFactory(
                H2ConnectionConfiguration.builder()
                        .url(host)
                        .username(username)
                        .build()
        );
    }
}
