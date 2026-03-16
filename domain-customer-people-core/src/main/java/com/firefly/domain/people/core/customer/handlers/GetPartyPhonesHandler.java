package com.firefly.domain.people.core.customer.handlers;

import com.firefly.core.customer.sdk.api.PhoneContactsApi;
import com.firefly.core.customer.sdk.model.FilterRequestPhoneContactDTO;
import com.firefly.core.customer.sdk.model.PaginationResponsePhoneContactDTO;
import com.firefly.domain.people.core.customer.queries.PartyPhonesQuery;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.fireflyframework.cqrs.annotations.QueryHandlerComponent;
import org.fireflyframework.cqrs.query.QueryHandler;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@QueryHandlerComponent
public class GetPartyPhonesHandler extends QueryHandler<PartyPhonesQuery, PaginationResponsePhoneContactDTO> {

    private final PhoneContactsApi phoneContactsApi;

    @Override
    protected Mono<PaginationResponsePhoneContactDTO> doHandle(PartyPhonesQuery query) {
        log.debug("Fetching phone contacts for partyId={}", query.getPartyId());
        return phoneContactsApi.filterPhoneContacts(query.getPartyId(), new FilterRequestPhoneContactDTO(), UUID.randomUUID().toString());
    }
}
