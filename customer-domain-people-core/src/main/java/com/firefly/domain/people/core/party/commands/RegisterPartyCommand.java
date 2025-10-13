package com.firefly.domain.people.core.party.commands;

import com.firefly.common.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.PartyDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class RegisterPartyCommand extends PartyDTO implements Command<UUID> {}
