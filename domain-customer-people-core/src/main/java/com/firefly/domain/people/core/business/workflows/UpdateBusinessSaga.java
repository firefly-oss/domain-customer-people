package com.firefly.domain.people.core.business.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.UpdateBusinessCommand;
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
 * Saga orchestrator for business update processes.
 * 
 * This orchestrator manages the distributed transaction for updating business information,
 * coordinating the update of business name and other legal entity details.
 * Each step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_UPDATE_BUSINESS_SAGA)
@Service
public class UpdateBusinessSaga {

    private final CommandBus commandBus;

    @Autowired
    public UpdateBusinessSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_BUSINESS)
    @StepEvent(type = EVENT_BUSINESS_CHANGED)
    public Mono<UUID> updateBusiness(UpdateBusinessCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}