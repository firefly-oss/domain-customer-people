package com.firefly.domain.people.core.customer.handlers;

import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.core.customer.sdk.model.FilterRequestEmailContactDTO;
import com.firefly.core.customer.sdk.model.PaginationResponseEmailContactDTO;
import com.firefly.domain.people.core.customer.queries.PartyEmailsQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@QueryHandlerComponent
public class GetPartyEmailsHandler extends QueryHandler<PartyEmailsQuery, PaginationResponseEmailContactDTO> {

    private final EmailContactsApi emailContactsApi;

    @Override
    protected Mono<PaginationResponseEmailContactDTO> doHandle(PartyEmailsQuery query) {
        log.debug("Fetching email contacts for partyId={}", query.getPartyId());
        return emailContactsApi.filterEmailContacts(query.getPartyId(), new FilterRequestEmailContactDTO(), UUID.randomUUID().toString());
    }
}
