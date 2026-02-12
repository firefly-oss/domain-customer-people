package com.firefly.domain.people.core.status.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.status.commands.UpdateStatusCommand;
import org.fireflyframework.transactional.saga.annotations.Saga;
import org.fireflyframework.transactional.saga.annotations.SagaStep;
import org.fireflyframework.transactional.saga.annotations.StepEvent;
import org.fireflyframework.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for updating customer email processes.
 * 
 * This orchestrator manages the distributed transaction for updating customer emails,
 * coordinating the email update step. The step is designed
 * to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_UPDATE_EMAIL_NAME)
@Service
public class UpdateStatusSaga {

    private final CommandBus commandBus;

    @Autowired
    public UpdateStatusSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_STATUS)
    @StepEvent(type = EVENT_STATUS_UPDATED)
    public Mono<UUID> updateStatus(UpdateStatusCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}