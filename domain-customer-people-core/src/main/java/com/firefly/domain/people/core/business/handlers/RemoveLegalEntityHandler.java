package com.firefly.domain.people.core.business.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.LegalEntitiesApi;
import com.firefly.core.customer.sdk.api.NaturalPersonsApi;
import com.firefly.domain.people.core.business.commands.RemoveLegalEntityCommand;
import com.firefly.domain.people.core.customer.commands.RemoveNaturalPersonCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveLegalEntityHandler extends CommandHandler<RemoveLegalEntityCommand, Void> {

    private final LegalEntitiesApi legalEntitiesApi;

    public RemoveLegalEntityHandler(LegalEntitiesApi legalEntitiesApi) {
        this.legalEntitiesApi = legalEntitiesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveLegalEntityCommand cmd) {
        return legalEntitiesApi.deleteLegalEntity(cmd.partyId(), cmd.legalEntityId()).then();
    }
}