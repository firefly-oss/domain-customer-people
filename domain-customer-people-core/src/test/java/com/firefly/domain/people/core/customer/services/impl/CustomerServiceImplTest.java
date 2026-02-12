package com.firefly.domain.people.core.customer.services.impl;

import org.fireflyframework.cqrs.query.QueryBus;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import com.firefly.domain.people.core.customer.workflows.UpdateCustomerSaga;
import org.fireflyframework.transactional.saga.core.SagaResult;
import org.fireflyframework.transactional.saga.engine.SagaEngine;
import org.fireflyframework.transactional.saga.engine.StepInputs;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("CustomerServiceImpl Tests")
class CustomerServiceImplTest {

    @Mock
    private SagaEngine sagaEngine;

    @Mock
    private QueryBus queryBus;

    @Mock
    private SagaResult sagaResult;

    private CustomerServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new CustomerServiceImpl(sagaEngine, queryBus);
    }

    @Test
    @DisplayName("Should register customer successfully")
    void testRegisterCustomer_ShouldExecuteSaga() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(RegisterCustomerSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Should update customer successfully")
    void testUpdateCustomer_ShouldExecuteSaga() {
        // Given
        UpdateCustomerCommand command = mock(UpdateCustomerCommand.class);
        when(sagaEngine.execute(eq(UpdateCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        // When
        Mono<SagaResult> result = service.updateCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();

        verify(sagaEngine).execute(eq(UpdateCustomerSaga.class), any(StepInputs.class));
    }


    @Test
    @DisplayName("Should handle saga execution errors")
    void testRegisterCustomer_ShouldHandleErrors() {
        // Given
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        RuntimeException error = new RuntimeException("Saga execution failed");
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.error(error));

        // When
        Mono<SagaResult> result = service.registerCustomer(command);

        // Then
        StepVerifier.create(result)
                .expectError(RuntimeException.class)
                .verify();

        verify(sagaEngine).execute(eq(RegisterCustomerSaga.class), any(StepInputs.class));
    }

    @Test
    @DisplayName("Constructor should set saga engine dependency")
    void testConstructor_ShouldSetSagaEngine() {
        // When
        CustomerServiceImpl newService = new CustomerServiceImpl(sagaEngine, queryBus);

        // Then
        assertNotNull(newService);
        // We can't directly access the private field, but we can verify it works by calling a method
        RegisterCustomerCommand command = mock(RegisterCustomerCommand.class);
        when(sagaEngine.execute(eq(RegisterCustomerSaga.class), any(StepInputs.class)))
                .thenReturn(Mono.just(sagaResult));

        Mono<SagaResult> result = newService.registerCustomer(command);

        StepVerifier.create(result)
                .expectNext(sagaResult)
                .verifyComplete();
    }
}