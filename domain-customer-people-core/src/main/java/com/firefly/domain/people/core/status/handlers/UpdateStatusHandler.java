package com.firefly.domain.people.core.status.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyStatusesApi;
import com.firefly.domain.people.core.status.commands.UpdateStatusCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdateStatusHandler extends CommandHandler<UpdateStatusCommand, UUID> {

    private final PartyStatusesApi partyStatusesApi;

    public UpdateStatusHandler(PartyStatusesApi partyStatusesApi) {
        this.partyStatusesApi = partyStatusesApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdateStatusCommand cmd) {
        return partyStatusesApi.updatePartyStatus(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyStatusDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyStatusDTO).getPartyStatusId()));
    }
}