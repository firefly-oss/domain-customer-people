package com.firefly.domain.people.core.contact.workflows;

import org.fireflyframework.cqrs.command.CommandBus;
import com.firefly.domain.people.core.contact.commands.RemoveIdentityDocumentCommand;
import org.fireflyframework.transactional.saga.annotations.Saga;
import org.fireflyframework.transactional.saga.annotations.SagaStep;
import org.fireflyframework.transactional.saga.annotations.StepEvent;
import org.fireflyframework.transactional.saga.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static com.firefly.domain.people.core.utils.constants.RegisterCustomerConstants.*;

/**
 * Saga orchestrator for removing identity documents from existing customers.
 * 
 * This orchestrator manages the distributed transaction for removing customer identity documents,
 * ensuring data consistency by coordinating the identity document removal step.
 * The step is designed to be compensatable to ensure data consistency in case of failures.
 */
@Saga(name = SAGA_REMOVE_IDENTITY_DOCUMENT_NAME)
@Service
public class RemoveIdentityDocumentSaga {

    private final CommandBus commandBus;

    @Autowired
    public RemoveIdentityDocumentSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REMOVE_IDENTITY_DOCUMENT)
    @StepEvent(type = EVENT_IDENTITY_DOCUMENT_REMOVED)
    public Mono<Void> removeIdentityDocument(RemoveIdentityDocumentCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }

}