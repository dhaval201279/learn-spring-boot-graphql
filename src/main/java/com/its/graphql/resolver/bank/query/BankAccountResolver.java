package com.its.graphql.resolver.bank.query;

import com.its.graphql.domain.bank.BankAccount;
import com.its.graphql.domain.bank.Client;
import com.its.graphql.domain.bank.Currency;
import graphql.kickstart.tools.GraphQLQueryResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.UUID;

/**
 * if its from root query, than query needs to be resolved than GraphQLQueryResolver
 * if we wanted nested elements within a query than GraphQLResolver
 * */
@Slf4j
@Component
public class BankAccountResolver implements GraphQLQueryResolver {
    public BankAccount bankAccount(UUID id) {
        log.info("Entering bankAccount with id : {}", id);
        Client clientA = Client
                .builder()
                .id(UUID.randomUUID())
                .firstName("Dhaval")
                .lastName("Shah")
                .build();

        Client clientB = Client
                .builder()
                .id(UUID.randomUUID())
                .firstName("Shruti")
                .lastName("Shah")
                .build();

        clientA.setClient(clientB);
        clientB.setClient(clientA);
        log.info("Leaving bankAccount");
        return BankAccount
                .builder()
                .id(id)
                /*.client(Client
                        .builder()
                        .id(UUID.randomUUID())
                        .firstName("Jigar")
                        .lastName(" Patel")
                        .build()
                ) --- Commented this after creating ClientResolver */
                .currency(Currency.USD)
                .build();
    }
}
