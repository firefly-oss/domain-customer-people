package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.IdentityDocumentsApi;
import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterIdentityDocumentHandler extends CommandHandler<RegisterIdentityDocumentCommand, UUID> {

    private final IdentityDocumentsApi identityDocumentsApi;

    public RegisterIdentityDocumentHandler(IdentityDocumentsApi identityDocumentsApi) {
        this.identityDocumentsApi = identityDocumentsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterIdentityDocumentCommand cmd) {
                
        return identityDocumentsApi
                .createIdentityDocument(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(identityDocumentDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(identityDocumentDTO).getIdentityDocumentId()));
    }
}