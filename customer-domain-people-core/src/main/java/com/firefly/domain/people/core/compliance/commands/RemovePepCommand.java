package com.firefly.domain.people.core.compliance.commands;

import com.firefly.common.cqrs.command.Command;

import java.util.UUID;

public record RemovePepCommand(
    UUID partyId,
    UUID pepId
) implements Command<Void> {}