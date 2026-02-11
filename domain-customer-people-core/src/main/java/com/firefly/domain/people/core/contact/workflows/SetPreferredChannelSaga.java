package com.firefly.domain.people.core.contact.workflows;

import com.firefly.common.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.UpdatePreferredChannelCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import com.firefly.transactional.saga.annotations.Saga;
import com.firefly.transactional.saga.annotations.SagaStep;
import com.firefly.transactional.saga.annotations.StepEvent;
import com.firefly.transactional.saga.core.SagaContext;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for updating customer address processes.
 * 
 * This orchestrator manages the distributed transaction for updating customer addresses,
 * coordinating the address update step. The step is designed
 * to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_SET_PREFERRED_CHANNEL_NAME)
@Service
public class SetPreferredChannelSaga {

    private final CommandBus commandBus;

    @Autowired
    public SetPreferredChannelSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_CHANNEL)
    @StepEvent(type = EVENT_PREFERRED_CHANNEL_UPDATED)
    public Mono<Void> updateChannel(UpdatePreferredChannelCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd).then();
    }

}