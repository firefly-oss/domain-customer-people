package com.firefly.domain.people.core.party.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyRelationshipsApi;
import com.firefly.domain.people.core.party.commands.RemovePartyRelationshipCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemovePartyRelationshipHandler extends CommandHandler<RemovePartyRelationshipCommand, Void> {

    private final PartyRelationshipsApi partyRelationshipsApi;

    public RemovePartyRelationshipHandler(PartyRelationshipsApi partyRelationshipsApi) {
        this.partyRelationshipsApi = partyRelationshipsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePartyRelationshipCommand cmd) {
        return partyRelationshipsApi
                .deletePartyRelationship(cmd.partyRelationshipId(), UUID.randomUUID().toString());
    }
}