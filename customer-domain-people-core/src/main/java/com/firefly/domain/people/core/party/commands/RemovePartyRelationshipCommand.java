package com.firefly.domain.people.core.party.commands;

import com.firefly.common.cqrs.command.Command;
import java.util.UUID;

public record RemovePartyRelationshipCommand(
    UUID partyRelationshipId
) implements Command<Void> {}