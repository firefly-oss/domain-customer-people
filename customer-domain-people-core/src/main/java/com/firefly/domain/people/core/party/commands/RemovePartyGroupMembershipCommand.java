package com.firefly.domain.people.core.party.commands;

import com.firefly.common.cqrs.command.Command;
import java.util.UUID;

public record RemovePartyGroupMembershipCommand(
    UUID partyId,
    UUID partyGroupMembershipId
) implements Command<Void> {}