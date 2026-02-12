package com.firefly.domain.people.core.contact.commands;

import org.fireflyframework.cqrs.command.Command;
import java.util.UUID;

public record RemovePhoneCommand(
        UUID partyId,
        UUID phoneId
) implements Command<Void> {}