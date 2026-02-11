package com.firefly.domain.people.core.compliance.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PoliticallyExposedPersonsApi;
import com.firefly.domain.people.core.compliance.commands.RemovePepCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemovePepHandler extends CommandHandler<RemovePepCommand, Void> {

    private final PoliticallyExposedPersonsApi politicallyExposedPersonsApi;

    public RemovePepHandler(PoliticallyExposedPersonsApi politicallyExposedPersonsApi) {
        this.politicallyExposedPersonsApi = politicallyExposedPersonsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePepCommand cmd) {
        return politicallyExposedPersonsApi
                .deletePoliticallyExposedPerson(cmd.partyId(), cmd.pepId())
                .then();
    }
}