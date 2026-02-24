package com.firefly.domain.people.core.business.services.impl;

import org.fireflyframework.cqrs.query.QueryBus;
import com.firefly.core.customer.sdk.model.LegalEntityDTO;
import com.firefly.domain.people.core.business.commands.RegisterBusinessCommand;
import com.firefly.domain.people.core.business.commands.UpdateBusinessCommand;
import com.firefly.domain.people.core.business.services.BusinessService;
import com.firefly.domain.people.core.business.workflows.UpdateBusinessSaga;
import com.firefly.domain.people.core.customer.queries.LegalEntityQuery;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import org.fireflyframework.orchestration.saga.engine.SagaResult;
import org.fireflyframework.orchestration.saga.engine.ExpandEach;
import org.fireflyframework.orchestration.saga.engine.SagaEngine;
import org.fireflyframework.orchestration.saga.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class BusinessServiceImpl implements BusinessService {

    private final SagaEngine engine;
    private final QueryBus queryBus;

    @Autowired
    public BusinessServiceImpl(SagaEngine engine, QueryBus queryBus) {
        this.engine = engine;
        this.queryBus = queryBus;
    }


    @Override
    public Mono<SagaResult> registerBusiness(RegisterBusinessCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerParty", command.party())
                .forStepId("registerLegalEntity", command.legalEntity())
                .forStepId("registerStatusEntry", ExpandEach.of(command.statusHistory()))
                .forStepId("registerIdentityDocument", ExpandEach.of(command.identityDocuments()))
                .forStepId("registerAddress", ExpandEach.of(command.addresses()))
                .forStepId("registerEmail", ExpandEach.of(command.emails()))
                .forStepId("registerPhone", ExpandEach.of(command.phones()))
                .forStepId("registerEconomicActivityLink", ExpandEach.of(command.economicActivities()))
                .forStepId("registerPartyProvider", ExpandEach.of(command.providers()))
                .forStepId("registerPartyRelationship", ExpandEach.of(command.relationships()))
                .forStepId("registerPartyGroupMembership", ExpandEach.of(command.groupMemberships()))
                .build();

        return engine.execute("RegisterCustomerSaga", inputs);
    }

    @Override
    public Mono<SagaResult> updateBusiness(UpdateBusinessCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updateBusiness", command)
                .build();

        return engine.execute("UpdateBusinessSaga", inputs);
    }

    @Override
    public Mono<LegalEntityDTO> getBusinessInfo(UUID businessId) {
        return queryBus.query(LegalEntityQuery.builder().partyId(businessId).build());
    }
}
