package com.firefly.domain.people.core.party.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartiesApi;
import com.firefly.domain.people.core.party.commands.RemovePartyCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemovePartyHandler extends CommandHandler<RemovePartyCommand, Void> {

    private final PartiesApi partiesApi;

    public RemovePartyHandler(PartiesApi partiesApi) {
        this.partiesApi = partiesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePartyCommand cmd) {
        return partiesApi.deleteParty(cmd.partyId(), UUID.randomUUID().toString()).then();
    }
}
