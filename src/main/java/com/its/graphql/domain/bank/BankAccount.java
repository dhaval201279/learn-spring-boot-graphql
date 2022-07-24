package com.its.graphql.domain.bank;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;
/**
 * String name as BankAccount's property sounds too ambiguous. Also if its client's name then
 * it can have fName, lName, mName etc. Hence it is advisable to manage such properties via
 * wrapper object i.e. Client. This in a way helps to maintain backward compatabililty
 * */
@Value
@Builder
public class BankAccount {
    UUID id;
    //String name;
    Client client;
    Currency currency;
}
