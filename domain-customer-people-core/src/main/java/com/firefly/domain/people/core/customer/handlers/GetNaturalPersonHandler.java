package com.firefly.domain.people.core.customer.handlers;

import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import com.firefly.domain.people.core.customer.queries.NaturalPersonQuery;
import reactor.core.publisher.Mono;

import java.util.UUID;

@QueryHandlerComponent
public class GetNaturalPersonHandler extends QueryHandler<NaturalPersonQuery, NaturalPersonDTO> {

    private final NaturalPersonsApi naturalPersonsApi;

    public GetNaturalPersonHandler(NaturalPersonsApi naturalPersonsApi) {
        this.naturalPersonsApi = naturalPersonsApi;
    }

    @Override
    protected Mono<NaturalPersonDTO> doHandle(NaturalPersonQuery cmd) {
        return naturalPersonsApi.getNaturalPersonByPartyId(cmd.getPartyId(), UUID.randomUUID().toString());
    }
}