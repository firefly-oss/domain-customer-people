package com.firefly.domain.people.core.customer.handlers;

import com.firefly.common.domain.cqrs.annotations.QueryHandlerComponent;
import com.firefly.common.domain.cqrs.query.QueryHandler;
import com.firefly.core.customer.sdk.api.LegalEntitiesApi;
import com.firefly.core.customer.sdk.model.LegalEntityDTO;
import com.firefly.domain.people.core.customer.queries.LegalEntityQuery;
import reactor.core.publisher.Mono;

@QueryHandlerComponent
public class GetLegalEntityHandler extends QueryHandler<LegalEntityQuery, LegalEntityDTO> {

    private final LegalEntitiesApi legalEntitiesApi;

    public GetLegalEntityHandler(LegalEntitiesApi legalEntitiesApi) {
        this.legalEntitiesApi = legalEntitiesApi;
    }

    @Override
    protected Mono<LegalEntityDTO> doHandle(LegalEntityQuery cmd) {
        return legalEntitiesApi.getLegalEntityByPartyId(cmd.getPartyId());
    }
}