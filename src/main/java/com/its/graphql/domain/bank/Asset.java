package com.its.graphql.domain.bank;

import lombok.Builder;
import lombok.Value;

import java.util.UUID;

@Builder
@Value
public class Asset {

    UUID id;
}
