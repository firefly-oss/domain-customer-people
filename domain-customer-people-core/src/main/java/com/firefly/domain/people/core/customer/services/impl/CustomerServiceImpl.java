package com.firefly.domain.people.core.customer.services.impl;

import org.fireflyframework.cqrs.query.QueryBus;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.customer.queries.NaturalPersonQuery;
import com.firefly.domain.people.core.customer.services.CustomerService;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import com.firefly.domain.people.core.customer.workflows.UpdateCustomerSaga;
import org.fireflyframework.orchestration.saga.engine.SagaResult;
import org.fireflyframework.orchestration.saga.engine.ExpandEach;
import org.fireflyframework.orchestration.saga.engine.SagaEngine;
import org.fireflyframework.orchestration.saga.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final SagaEngine engine;
    private final QueryBus queryBus;

    @Autowired
    public CustomerServiceImpl(SagaEngine engine, QueryBus queryBus) {
        this.engine = engine;
        this.queryBus = queryBus;
    }


    @Override
    public Mono<SagaResult> registerCustomer(RegisterCustomerCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerParty", command.party())
                .forStepId("registerNaturalPerson", command.naturalPerson())
                .forStepId("registerStatusEntry", ExpandEach.of(command.statusHistory()))
                .forStepId("registerPep", command.pep())
                .forStepId("registerIdentityDocument", ExpandEach.of(command.identityDocuments()))
                .forStepId("registerAddress", ExpandEach.of(command.addresses()))
                .forStepId("registerEmail", ExpandEach.of(command.emails()))
                .forStepId("registerPhone", ExpandEach.of(command.phones()))
                .forStepId("registerEconomicActivityLink", ExpandEach.of(command.economicActivities()))
                .forStepId("registerConsent", ExpandEach.of(command.consents()))
                .forStepId("registerPartyProvider", ExpandEach.of(command.providers()))
                .forStepId("registerPartyRelationship", ExpandEach.of(command.relationships()))
                .forStepId("registerPartyGroupMembership", ExpandEach.of(command.groupMemberships()))
                .build();

        return engine.execute("RegisterCustomerSaga", inputs);
    }

    @Override
    public Mono<SagaResult> updateCustomer(UpdateCustomerCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updateCustomer", command)
                .build();

        return engine.execute("UpdateCustomerSaga", inputs);
    }

    @Override
    public Mono<NaturalPersonDTO> getCustomerInfo(UUID customerId) {
        return queryBus.query(NaturalPersonQuery.builder().partyId(customerId).build());
    }
}
