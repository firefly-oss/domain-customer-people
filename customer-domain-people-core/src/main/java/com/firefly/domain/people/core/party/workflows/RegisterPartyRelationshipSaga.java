package com.firefly.domain.people.core.party.workflows;

import com.firefly.common.cqrs.command.CommandBus;
import com.firefly.domain.people.core.business.commands.RegisterLegalEntityCommand;
import com.firefly.domain.people.core.business.commands.RemoveLegalEntityCommand;
import com.firefly.domain.people.core.compliance.commands.*;
import com.firefly.domain.people.core.contact.commands.*;
import com.firefly.domain.people.core.customer.commands.RegisterNaturalPersonCommand;
import com.firefly.domain.people.core.customer.commands.RemoveNaturalPersonCommand;
import com.firefly.domain.people.core.party.commands.*;
import com.firefly.domain.people.core.status.commands.RegisterPartyStatusEntryCommand;
import com.firefly.domain.people.core.status.commands.RemovePartyStatusEntryCommand;
import com.firefly.transactional.annotations.Saga;
import com.firefly.transactional.annotations.SagaStep;
import com.firefly.transactional.annotations.StepEvent;
import com.firefly.transactional.core.SagaContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

import static com.firefly.domain.people.core.utils.constants.GlobalConstants.CTX_PARTY_ID;
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
@Saga(name = SAGA_REGISTER_PARTY_RELATIONSHIP)
@Service
public class RegisterPartyRelationshipSaga {

    private final CommandBus commandBus;

    @Autowired
    public RegisterPartyRelationshipSaga(CommandBus commandBus) {
        this.commandBus = commandBus;
    }

    @SagaStep(id = STEP_REGISTER_PARTY_RELATIONSHIP)
    @StepEvent(type = EVENT_PARTY_RELATIONSHIP_REGISTERED)
    public Mono<UUID> registerPartyRelationship(RegisterPartyRelationshipCommand cmd, SagaContext ctx) {
        return commandBus.send(cmd);
    }
}
