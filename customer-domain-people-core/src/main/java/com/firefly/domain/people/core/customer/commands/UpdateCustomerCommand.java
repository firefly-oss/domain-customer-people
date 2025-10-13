package com.firefly.domain.people.core.customer.commands;

import com.firefly.common.cqrs.command.Command;
import com.firefly.core.customer.sdk.model.NaturalPersonDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class UpdateCustomerCommand extends NaturalPersonDTO implements Command<UUID> {}