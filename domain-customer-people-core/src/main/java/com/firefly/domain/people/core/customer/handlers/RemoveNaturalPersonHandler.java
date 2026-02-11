package com.firefly.domain.people.core.customer.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.domain.people.core.customer.commands.RemoveNaturalPersonCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveNaturalPersonHandler extends CommandHandler<RemoveNaturalPersonCommand, Void> {

    private final NaturalPersonsApi naturalPersonsApi;

    public RemoveNaturalPersonHandler(NaturalPersonsApi naturalPersonsApi) {
        this.naturalPersonsApi = naturalPersonsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveNaturalPersonCommand cmd) {
        return naturalPersonsApi.deleteNaturalPerson(cmd.partyId(), cmd.naturalPersonId()).then();
    }
}