package com.firefly.domain.people.core.party.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartiesApi;
import com.firefly.domain.people.core.party.commands.RegisterPartyCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyHandler extends CommandHandler<RegisterPartyCommand, UUID> {

    private final PartiesApi partiesApi;

    public RegisterPartyHandler(PartiesApi partiesApi) {
        this.partiesApi = partiesApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyCommand cmd) {

        return partiesApi
                .createParty(cmd, UUID.randomUUID().toString())
                .mapNotNull(partyDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyDTO).getPartyId()));
    }
}
