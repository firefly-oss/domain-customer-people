package com.firefly.domain.people.core.business.commands;

import com.firefly.common.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.LegalEntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UpdateBusinessCommand extends LegalEntityDTO implements Command<UUID> {}