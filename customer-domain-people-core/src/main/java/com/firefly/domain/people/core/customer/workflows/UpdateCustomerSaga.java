package com.firefly.domain.people.core.customer.workflows;

import com.firefly.common.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.UpdateBusinessCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.transactional.saga.annotations.Saga;
import com.firefly.transactional.saga.annotations.SagaStep;
import com.firefly.transactional.saga.annotations.StepEvent;
import com.firefly.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;


/**
 * Saga orchestrator for customer registration processes.
 * 
 * This orchestrator manages the distributed transaction for registering customers,
 * coordinating multiple steps including party creation, person details registration,
 * contact information setup, and relationship establishment. Each step is designed
 * to be compensatable to ensure data consistency in case of failures.
 * 
 * The orchestrator handles both natural persons and legal entities, with conditional
 * logic to process only relevant information based on the customer type.
 */
@Saga(name = SAGA_UPDATE_CUSTOMER_SAGA)
@Service
public class UpdateCustomerSaga {

    private final CommandBus commandBus;

    @Autowired
    public UpdateCustomerSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_UPDATE_CUSTOMER)
    @StepEvent(type = EVENT_CUSTOMER_CHANGED)
    public Mono<UUID> updateBusiness(UpdateBusinessCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }


}
