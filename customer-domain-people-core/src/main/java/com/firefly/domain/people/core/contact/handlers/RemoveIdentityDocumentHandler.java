package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.IdentityDocumentsApi;
import com.firefly.domain.people.core.contact.commands.RemoveIdentityDocumentCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveIdentityDocumentHandler extends CommandHandler<RemoveIdentityDocumentCommand, Void> {

    private final IdentityDocumentsApi identityDocumentsApi;

    public RemoveIdentityDocumentHandler(IdentityDocumentsApi identityDocumentsApi) {
        this.identityDocumentsApi = identityDocumentsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveIdentityDocumentCommand cmd) {
        return identityDocumentsApi
                .deleteIdentityDocument(cmd.partyId(), cmd.identityDocumentId())
                .then();
    }
}