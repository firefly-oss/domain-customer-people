package com.firefly.domain.people.core.compliance.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyEconomicActivitiesApi;
import com.firefly.domain.people.core.compliance.commands.RegisterEconomicActivityLinkCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterEconomicActivityHandler extends CommandHandler<RegisterEconomicActivityLinkCommand, UUID> {

    private final PartyEconomicActivitiesApi partyEconomicActivitiesApi;

    public RegisterEconomicActivityHandler(PartyEconomicActivitiesApi partyEconomicActivitiesApi) {
        this.partyEconomicActivitiesApi = partyEconomicActivitiesApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterEconomicActivityLinkCommand cmd) {
        return partyEconomicActivitiesApi
                .createPartyEconomicActivity(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(economicActivityDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(economicActivityDTO).getEconomicActivityId()));
    }
}