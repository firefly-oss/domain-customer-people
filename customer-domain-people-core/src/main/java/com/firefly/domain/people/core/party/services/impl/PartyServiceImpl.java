package com.firefly.domain.people.core.party.services.impl;

import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.RemovePartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.UpdatePartyRelationshipCommand;
import com.firefly.domain.people.core.party.services.PartyService;
import com.firefly.domain.people.core.party.workflows.RegisterPartyRelationshipSaga;
import com.firefly.domain.people.core.party.workflows.UpdatePartyRelationshipSaga;
import com.firefly.domain.people.core.party.workflows.RemovePartyRelationshipSaga;
import com.firefly.transactional.saga.core.SagaResult;
import com.firefly.transactional.saga.engine.SagaEngine;
import com.firefly.transactional.saga.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class PartyServiceImpl implements PartyService {

    private final SagaEngine engine;

    @Autowired
    public PartyServiceImpl(SagaEngine engine) {
        this.engine = engine;
    }


    @Override
    public Mono<SagaResult> registerPartyRelationship(RegisterPartyRelationshipCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RegisterPartyRelationshipSaga::registerPartyRelationship, command)
                .build();

        return engine.execute(RegisterPartyRelationshipSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> updatePartyRelationship(UpdatePartyRelationshipCommand command, UUID relationId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdatePartyRelationshipSaga::updatePartyRelationship, command.withPartyRelationshipId(relationId))
                .build();

        return engine.execute(UpdatePartyRelationshipSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> removePartyRelationship(UUID relationId) {
        RemovePartyRelationshipCommand command = new RemovePartyRelationshipCommand(relationId);
        StepInputs inputs = StepInputs.builder()
                .forStep(RemovePartyRelationshipSaga::removePartyRelationship, command)
                .build();

        return engine.execute(RemovePartyRelationshipSaga.class, inputs);
    }

}
