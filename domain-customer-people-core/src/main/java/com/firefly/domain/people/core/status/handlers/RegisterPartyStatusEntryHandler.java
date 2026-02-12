package com.firefly.domain.people.core.status.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyStatusesApi;
import com.firefly.domain.people.core.status.commands.RegisterPartyStatusEntryCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyStatusEntryHandler extends CommandHandler<RegisterPartyStatusEntryCommand, UUID> {

    private final PartyStatusesApi partyStatusesApi;

    public RegisterPartyStatusEntryHandler(PartyStatusesApi partyStatusesApi) {
        this.partyStatusesApi = partyStatusesApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyStatusEntryCommand cmd) {
        return partyStatusesApi
                .createPartyStatus(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyStatusDTO ->
                        Objects.requireNonNull(partyStatusDTO.getPartyStatusId()));
    }
}