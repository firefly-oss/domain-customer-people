package com.firefly.domain.people.core.customer.handlers;

import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import com.firefly.core.customer.sdk.api.LegalEntitiesApi;
import com.firefly.core.customer.sdk.model.LegalEntityDTO;
import com.firefly.domain.people.core.customer.queries.LegalEntityQuery;
import reactor.core.publisher.Mono;

import java.util.UUID;

@QueryHandlerComponent
public class GetLegalEntityHandler extends QueryHandler<LegalEntityQuery, LegalEntityDTO> {

    private final LegalEntitiesApi legalEntitiesApi;

    public GetLegalEntityHandler(LegalEntitiesApi legalEntitiesApi) {
        this.legalEntitiesApi = legalEntitiesApi;
    }

    @Override
    protected Mono<LegalEntityDTO> doHandle(LegalEntityQuery cmd) {
        return legalEntitiesApi.getLegalEntityByPartyId(cmd.getPartyId(), UUID.randomUUID().toString());
    }
}