package com.firefly.domain.people.core.status.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyStatusesApi;
import com.firefly.domain.people.core.status.commands.RemovePartyStatusEntryCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemovePartyStatusEntryHandler extends CommandHandler<RemovePartyStatusEntryCommand, Void> {

    private final PartyStatusesApi partyStatusesApi;

    public RemovePartyStatusEntryHandler(PartyStatusesApi partyStatusesApi) {
        this.partyStatusesApi = partyStatusesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePartyStatusEntryCommand cmd) {
        return partyStatusesApi.deletePartyStatus(cmd.partyId(), cmd.partyStatusId()).then();
    }
}