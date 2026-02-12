package com.firefly.domain.people.core.contact.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.RemovePhoneCommand;
import org.fireflyframework.transactional.saga.annotations.Saga;
import org.fireflyframework.transactional.saga.annotations.SagaStep;
import org.fireflyframework.transactional.saga.annotations.StepEvent;
import org.fireflyframework.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for removing phones from existing customers.
 * 
 * This orchestrator manages the distributed transaction for removing customer phones,
 * ensuring data consistency by coordinating the phone removal step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_REMOVE_PHONE_NAME)
@Service
public class RemovePhoneSaga {

    private final CommandBus commandBus;

    @Autowired
    public RemovePhoneSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REMOVE_PHONE)
    @StepEvent(type = EVENT_PHONE_REMOVED)
    public Mono<Void> removePhone(RemovePhoneCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}