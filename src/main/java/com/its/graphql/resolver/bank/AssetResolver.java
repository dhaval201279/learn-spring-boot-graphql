package com.its.graphql.resolver.bank;

import com.its.graphql.domain.bank.Asset;
import com.its.graphql.domain.bank.BankAccount;
import com.its.graphql.domain.bank.Client;
import graphql.kickstart.tools.GraphQLResolver;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Slf4j
@Component
public class AssetResolver implements GraphQLResolver<BankAccount> {
    private final ExecutorService executorService = Executors
            .newFixedThreadPool(Runtime
                    .getRuntime()
                    .availableProcessors()
            );

    public CompletableFuture<List<Asset>> assets(BankAccount bankAccount) {
        log.info("Entering assets");
        log.info("Leaving assets");
        return CompletableFuture
            .supplyAsync(() -> {
                    log.info("Getting assets for bank account id : {} ", bankAccount.getId());
                    List <Asset> assets = new ArrayList<Asset>();
                    assets.add(Asset.builder().id(UUID.randomUUID()).build());
                    assets.add(Asset.builder().id(UUID.randomUUID()).build());
                    return assets;
                },
                    executorService
                );
    }
}
