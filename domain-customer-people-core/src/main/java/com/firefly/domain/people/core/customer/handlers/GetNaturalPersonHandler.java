package com.firefly.domain.people.core.customer.handlers;

import com.firefly.common.cqrs.annotations.QueryHandlerComponent;
import com.firefly.common.cqrs.query.QueryHandler;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import com.firefly.domain.people.core.customer.queries.NaturalPersonQuery;
import reactor.core.publisher.Mono;

@QueryHandlerComponent
public class GetNaturalPersonHandler extends QueryHandler<NaturalPersonQuery, NaturalPersonDTO> {

    private final NaturalPersonsApi naturalPersonsApi;

    public GetNaturalPersonHandler(NaturalPersonsApi naturalPersonsApi) {
        this.naturalPersonsApi = naturalPersonsApi;
    }

    @Override
    protected Mono<NaturalPersonDTO> doHandle(NaturalPersonQuery cmd) {
        return naturalPersonsApi.getNaturalPersonByPartyId(cmd.getPartyId());
    }
}