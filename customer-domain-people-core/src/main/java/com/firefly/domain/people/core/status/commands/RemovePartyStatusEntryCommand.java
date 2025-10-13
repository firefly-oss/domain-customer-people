package com.firefly.domain.people.core.status.commands;

import com.firefly.common.cqrs.command.Command;

import java.util.UUID;

public record RemovePartyStatusEntryCommand(
        UUID partyId,
        UUID partyStatusId
) implements Command<Void> {}