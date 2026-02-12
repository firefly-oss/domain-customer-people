package com.firefly.domain.people.core.contact.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.UpdatePhoneCommand;
import org.fireflyframework.transactional.saga.annotations.Saga;
import org.fireflyframework.transactional.saga.annotations.SagaStep;
import org.fireflyframework.transactional.saga.annotations.StepEvent;
import org.fireflyframework.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for updating customer phone processes.
 * 
 * This orchestrator manages the distributed transaction for updating customer phones,
 * coordinating the phone update step. The step is designed
 * to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_UPDATE_PHONE_NAME)
@Service
public class UpdatePhoneSaga {

    private final CommandBus commandBus;

    @Autowired
    public UpdatePhoneSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_PHONE)
    @StepEvent(type = EVENT_PHONE_UPDATED)
    public Mono<Void> updatePhone(UpdatePhoneCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd).then();
    }

}