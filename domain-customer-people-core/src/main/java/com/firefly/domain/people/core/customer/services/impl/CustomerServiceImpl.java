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

import java.util.List;
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
        // Defensively default the optional collections so callers (notably the experience tier)
        // can post a minimal RegisterCustomerCommand with only party, naturalPerson and any
        // subset of contact channels, without tripping ExpandEach.of(...) on null. Each
        // collection is independent and optional for the saga's per-step expansion.
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerParty", command.party())
                .forStepId("registerNaturalPerson", command.naturalPerson())
                .forStepId("registerStatusEntry", ExpandEach.of(nullSafe(command.statusHistory())))
                .forStepId("registerPep", command.pep())
                .forStepId("registerIdentityDocument", ExpandEach.of(nullSafe(command.identityDocuments())))
                .forStepId("registerAddress", ExpandEach.of(nullSafe(command.addresses())))
                .forStepId("registerEmail", ExpandEach.of(nullSafe(command.emails())))
                .forStepId("registerPhone", ExpandEach.of(nullSafe(command.phones())))
                .forStepId("registerEconomicActivityLink", ExpandEach.of(nullSafe(command.economicActivities())))
                .forStepId("registerConsent", ExpandEach.of(nullSafe(command.consents())))
                .forStepId("registerPartyProvider", ExpandEach.of(nullSafe(command.providers())))
                .forStepId("registerPartyRelationship", ExpandEach.of(nullSafe(command.relationships())))
                .forStepId("registerPartyGroupMembership", ExpandEach.of(nullSafe(command.groupMemberships())))
                .build();

        return engine.execute("RegisterCustomerSaga", inputs);
    }

    private static <T> List<T> nullSafe(List<T> list) {
        return list == null ? List.of() : list;
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
