package com.firefly.domain.people.core.customer.handlers;

import com.firefly.core.customer.sdk.api.IdentityDocumentsApi;
import com.firefly.core.customer.sdk.model.FilterRequestIdentityDocumentDTO;
import com.firefly.core.customer.sdk.model.PaginationResponse;
import com.firefly.domain.people.core.customer.queries.PartyIdentityDocumentsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@QueryHandlerComponent
public class GetPartyIdentityDocumentsHandler extends QueryHandler<PartyIdentityDocumentsQuery, PaginationResponse> {

    private final IdentityDocumentsApi identityDocumentsApi;

    @Override
    protected Mono<PaginationResponse> doHandle(PartyIdentityDocumentsQuery query) {
        log.debug("Fetching identity documents for partyId={}", query.getPartyId());
        return identityDocumentsApi.filterIdentityDocuments(query.getPartyId(), new FilterRequestIdentityDocumentDTO(), UUID.randomUUID().toString());
    }
}
