package com.firefly.domain.people.core.compliance.commands;

import com.firefly.common.cqrs.command.Command;
import java.util.UUID;

public record RemoveEconomicActivityLinkCommand(
    UUID partyId,
    UUID economicActivityLinkId
) implements Command<Void> {}