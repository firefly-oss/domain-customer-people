package com.firefly.domain.people.core.party.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyRelationshipsApi;
import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPartyRelationshipHandler extends CommandHandler<RegisterPartyRelationshipCommand, UUID> {

    private final PartyRelationshipsApi partyRelationshipsApi;

    public RegisterPartyRelationshipHandler(PartyRelationshipsApi partyRelationshipsApi) {
        this.partyRelationshipsApi = partyRelationshipsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPartyRelationshipCommand cmd) {
        return partyRelationshipsApi
                .createPartyRelationship(cmd, UUID.randomUUID().toString())
                .mapNotNull(partyRelationshipDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(partyRelationshipDTO).getPartyRelationshipId()));
    }
}