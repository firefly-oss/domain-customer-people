package com.firefly.domain.people.core.customer.services.impl;

import com.firefly.common.cqrs.query.QueryBus;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import com.firefly.domain.people.core.customer.commands.RegisterCustomerCommand;
import com.firefly.domain.people.core.customer.commands.UpdateCustomerCommand;
import com.firefly.domain.people.core.customer.queries.NaturalPersonQuery;
import com.firefly.domain.people.core.customer.services.CustomerService;
import com.firefly.domain.people.core.customer.workflows.RegisterCustomerSaga;
import com.firefly.domain.people.core.customer.workflows.UpdateCustomerSaga;
import com.firefly.transactional.core.SagaResult;
import com.firefly.transactional.engine.ExpandEach;
import com.firefly.transactional.engine.SagaEngine;
import com.firefly.transactional.engine.StepInputs;
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
                .forStep(RegisterCustomerSaga::registerParty, command.party())
                .forStep(RegisterCustomerSaga::registerNaturalPerson, command.naturalPerson())
                .forStep(RegisterCustomerSaga::registerStatusEntry, ExpandEach.of(command.statusHistory()))
                .forStep(RegisterCustomerSaga::registerPep, command.pep())
                .forStep(RegisterCustomerSaga::registerIdentityDocument, ExpandEach.of(command.identityDocuments()))
                .forStep(RegisterCustomerSaga::registerAddress, ExpandEach.of(command.addresses()))
                .forStep(RegisterCustomerSaga::registerEmail, ExpandEach.of(command.emails()))
                .forStep(RegisterCustomerSaga::registerPhone, ExpandEach.of(command.phones()))
                .forStep(RegisterCustomerSaga::registerEconomicActivityLink, ExpandEach.of(command.economicActivities()))
                .forStep(RegisterCustomerSaga::registerConsent, ExpandEach.of(command.consents()))
                .forStep(RegisterCustomerSaga::registerPartyProvider, ExpandEach.of(command.providers()))
                .forStep(RegisterCustomerSaga::registerPartyRelationship, ExpandEach.of(command.relationships()))
                .forStep(RegisterCustomerSaga::registerPartyGroupMembership, ExpandEach.of(command.groupMemberships()))
                .build();

        return engine.execute(RegisterCustomerSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> updateCustomer(UpdateCustomerCommand command) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateCustomerSaga::updateBusiness, command)
                .build();

        return engine.execute(UpdateCustomerSaga.class, inputs);
    }

    @Override
    public Mono<NaturalPersonDTO> getCustomerInfo(UUID customerId) {
        return queryBus.query(NaturalPersonQuery.builder().partyId(customerId).build());
    }
}
