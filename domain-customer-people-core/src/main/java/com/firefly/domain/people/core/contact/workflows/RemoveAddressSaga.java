package com.firefly.domain.people.core.contact.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.RemoveAddressCommand;
import org.fireflyframework.transactional.saga.annotations.Saga;
import org.fireflyframework.transactional.saga.annotations.SagaStep;
import org.fireflyframework.transactional.saga.annotations.StepEvent;
import org.fireflyframework.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for removing addresses from existing customers.
 * 
 * This orchestrator manages the distributed transaction for removing customer addresses,
 * ensuring data consistency by coordinating the address removal step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_REMOVE_ADDRESS_NAME)
@Service
public class RemoveAddressSaga {

    private final CommandBus commandBus;

    @Autowired
    public RemoveAddressSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REMOVE_ADDRESS)
    @StepEvent(type = EVENT_ADDRESS_REMOVED)
    public Mono<Void> removeAddress(RemoveAddressCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}