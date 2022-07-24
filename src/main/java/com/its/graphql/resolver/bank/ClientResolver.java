package com.its.graphql.resolver.bank;

import com.its.graphql.domain.bank.BankAccount;
import com.its.graphql.domain.bank.Client;
import graphql.GraphQLException;
import graphql.execution.DataFetcherResult;
import graphql.kickstart.execution.error.GenericGraphQLError;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

@Slf4j
@Component
public class ClientResolver implements GraphQLResolver<BankAccount> {
    /**
     * Demonstrates how to use GraphQLResolver
     * Commented to understand capabilty of datafetcher
     * **/
    /*public Client client(BankAccount bankAccount) {
        log.info("Entering client with bank account Id : {} ", bankAccount.getId());

        log.info("Leaving client");
        *//**
         * demonstrates how GraphQLException is handled after enabling exception handling
         * *//*
        //throw new GraphQLException("Client Unavailable");

        *//**
         * demonstrates how RuntimeException is handled after enabling exception handling
         * *//*
        //throw new RuntimeException("Spring exception occurred");

        return Client
                .builder()
                .id(UUID.randomUUID())
                .firstName("Bhavin")
                .lastName("Shah")
                .build();
    }*/

    /**
     * Playlist video 10 - DataFetcherResult
     * When your resolver may need to retrieve data from multiple sources or from another GraphQL resource i.e.
     * 1. Call Multiple downstream services
     * 2. Call another graphql server
     * 3. Call service that returns partial response
     *
     * Such use cases can be handled by returning a graphql.execution.DataFetcherResult either directly or wrapped
     * in a CompletableFuture instance for asynchronous execution. One can also use Async graphql resolvers
     * */
    /*public DataFetcherResult<Client> client(BankAccount bankAccount) {
        log.info("Entering DataFetcherResult client with bank account Id : {} ", bankAccount.getId());

        log.info("Leaving DataFetcherResult client");
        return DataFetcherResult
                .<Client>newResult()
                .data(
                    Client
                        .builder()
                        .id(UUID.randomUUID())
                        .firstName("Bhavin")
                        .lastName("Shah")
                        .build()
                )
                .error(new GenericGraphQLError("Could not get sub client id"))
                .build();
    }*/


    /**
     * Playlist video 11 - Asynchronous Resolver
     *
     * */
    public CompletableFuture<Client> client(BankAccount bankAccount) {
        log.info("Entering CompletableFuture client with bank account Id : {} ", bankAccount.getId());

        log.info("Leaving CompletableFuture client");
        return CompletableFuture
                .supplyAsync(() -> {
                    log.info("Requesting client data from bank account id : {} ", bankAccount.getId());
                    return Client
                            .builder()
                            .id(UUID.randomUUID())
                            .firstName("Bhavin")
                            .lastName("Shah")
                            .build();
                    },
                    executorService
                );
    }

    private final ExecutorService executorService = Executors
                                                        .newFixedThreadPool(Runtime
                                                            .getRuntime()
                                                            .availableProcessors()
                                                        );
}
