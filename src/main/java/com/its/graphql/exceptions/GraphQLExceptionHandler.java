package com.its.graphql.exceptions;

import graphql.GraphQLException;
import graphql.kickstart.spring.error.ThrowableGraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * DefaultGraphQLErrorHandler is the base class which gets called when exception occurs.
 * 'filterGraphQLErrors' method that is invoked from processError, will invoke ExceptionHandlers
 * (e.g. 2 are implemented below)
 * */
@Slf4j
@Component
public class GraphQLExceptionHandler {

    @ExceptionHandler(GraphQLException.class)
    public ThrowableGraphQLError handle(GraphQLException e) {
        log.error("GraphQLException occurred -  e : {}, message : {} ", e, e.getMessage());
        return new ThrowableGraphQLError(e);
    }

    @ExceptionHandler(RuntimeException.class)
    public ThrowableGraphQLError handle(RuntimeException e) {
        log.error("RuntimeException occurred -  e : {}, message : {} ", e, e.getMessage());
        return new ThrowableGraphQLError(e,"Internal Server Error");
    }
}
