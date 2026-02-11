package com.firefly.domain.people.core.party.commands;

import com.firefly.common.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyDTO;

import java.util.UUID;

public record RemovePartyCommand(
        UUID partyId
) implements Command<Void>{}
