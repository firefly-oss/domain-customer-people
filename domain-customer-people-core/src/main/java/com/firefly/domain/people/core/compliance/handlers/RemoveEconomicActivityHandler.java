package com.firefly.domain.people.core.compliance.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PartyEconomicActivitiesApi;
import com.firefly.domain.people.core.compliance.commands.RemoveEconomicActivityLinkCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemoveEconomicActivityHandler extends CommandHandler<RemoveEconomicActivityLinkCommand, Void> {

    private final PartyEconomicActivitiesApi partyEconomicActivitiesApi;

    public RemoveEconomicActivityHandler(PartyEconomicActivitiesApi partyEconomicActivitiesApi) {
        this.partyEconomicActivitiesApi = partyEconomicActivitiesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveEconomicActivityLinkCommand cmd) {
        return partyEconomicActivitiesApi
                .deletePartyEconomicActivity(cmd.partyId(), cmd.economicActivityLinkId(), UUID.randomUUID().toString())
                .then();
    }
}