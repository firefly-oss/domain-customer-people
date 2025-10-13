package com.firefly.domain.people.core.compliance.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.ConsentsApi;
import com.firefly.core.customer.sdk.model.ConsentDTO;
import com.firefly.domain.people.core.compliance.commands.RegisterConsentCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterConsentHandler extends CommandHandler<RegisterConsentCommand, UUID> {

    private final ConsentsApi consentsApi;

    public RegisterConsentHandler(ConsentsApi consentsApi) {
        this.consentsApi = consentsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterConsentCommand cmd) {
        return consentsApi
                .createConsent(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(consentDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(consentDTO).getConsentId()));
    }
}