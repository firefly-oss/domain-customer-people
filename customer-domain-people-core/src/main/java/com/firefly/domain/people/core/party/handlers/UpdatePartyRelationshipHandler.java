package com.firefly.domain.people.core.party.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyRelationshipsApi;
import com.firefly.domain.people.core.party.commands.UpdatePartyRelationshipCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class UpdatePartyRelationshipHandler extends CommandHandler<UpdatePartyRelationshipCommand, UUID> {

    private final PartyRelationshipsApi partyRelationshipsApi;

    public UpdatePartyRelationshipHandler(PartyRelationshipsApi partyRelationshipsApi) {
        this.partyRelationshipsApi = partyRelationshipsApi;
    }

    @Override
    protected Mono<UUID> doHandle(UpdatePartyRelationshipCommand cmd) {
        return partyRelationshipsApi
                .updatePartyRelationship(cmd.getPartyRelationshipId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(partyRelationshipDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyRelationshipDTO).getPartyRelationshipId()));
    }
}