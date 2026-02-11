package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.cqrs.command.Command;
import java.util.UUID;

public record RemovePhoneCommand(
        UUID partyId,
        UUID phoneId
) implements Command<Void> {}