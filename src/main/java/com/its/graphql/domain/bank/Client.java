package com.its.graphql.domain.bank;

import lombok.Builder;
import lombok.Setter;

import java.util.List;
import java.util.UUID;
/**
 * GraphQL supports recursion i.e. Client can have client within itself
 * */
@Setter
@Builder
public class Client {
    UUID id;
    String firstName;
    List<String> middleNames;
    String lastName;
    Client client;
}
