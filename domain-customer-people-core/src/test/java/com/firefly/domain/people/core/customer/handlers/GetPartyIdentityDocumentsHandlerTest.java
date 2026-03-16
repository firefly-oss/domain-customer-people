package com.firefly.domain.people.core.customer.handlers;

import com.firefly.core.customer.sdk.api.IdentityDocumentsApi;
import com.firefly.core.customer.sdk.model.FilterRequestIdentityDocumentDTO;
import com.firefly.core.customer.sdk.model.PaginationResponse;
import com.firefly.domain.people.core.customer.queries.PartyIdentityDocumentsQuery;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("GetPartyIdentityDocumentsHandler Tests")
class GetPartyIdentityDocumentsHandlerTest {

    @Mock
    private IdentityDocumentsApi identityDocumentsApi;

    private GetPartyIdentityDocumentsHandler handler;

    @BeforeEach
    void setUp() {
        handler = new GetPartyIdentityDocumentsHandler(identityDocumentsApi);
    }

    @Test
    @DisplayName("Should return identity documents for a given partyId")
    void shouldReturnIdentityDocuments() {
        UUID partyId = UUID.randomUUID();
        PaginationResponse expectedResponse = new PaginationResponse();

        when(identityDocumentsApi.filterIdentityDocuments(eq(partyId), any(FilterRequestIdentityDocumentDTO.class), any(String.class)))
                .thenReturn(Mono.just(expectedResponse));

        PartyIdentityDocumentsQuery query = PartyIdentityDocumentsQuery.builder().partyId(partyId).build();

        StepVerifier.create(handler.doHandle(query))
                .expectNext(expectedResponse)
                .verifyComplete();

        verify(identityDocumentsApi).filterIdentityDocuments(eq(partyId), any(FilterRequestIdentityDocumentDTO.class), any(String.class));
    }

    @Test
    @DisplayName("Should propagate error when API call fails")
    void shouldPropagateError() {
        UUID partyId = UUID.randomUUID();

        when(identityDocumentsApi.filterIdentityDocuments(eq(partyId), any(FilterRequestIdentityDocumentDTO.class), any(String.class)))
                .thenReturn(Mono.error(new RuntimeException("API error")));

        PartyIdentityDocumentsQuery query = PartyIdentityDocumentsQuery.builder().partyId(partyId).build();

        StepVerifier.create(handler.doHandle(query))
                .expectErrorMatches(e -> e instanceof RuntimeException && e.getMessage().equals("API error"))
                .verify();
    }
}
