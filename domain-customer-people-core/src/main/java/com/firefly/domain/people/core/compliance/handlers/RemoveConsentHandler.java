package com.firefly.domain.people.core.compliance.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.ConsentsApi;
import com.firefly.domain.people.core.compliance.commands.RemoveConsentCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveConsentHandler extends CommandHandler<RemoveConsentCommand, Void> {

    private final ConsentsApi consentsApi;

    public RemoveConsentHandler(ConsentsApi consentsApi) {
        this.consentsApi = consentsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveConsentCommand cmd) {
        return consentsApi
                .deleteConsent(cmd.partyId(), cmd.consentId())
                .then();
    }
}