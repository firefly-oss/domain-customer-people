package com.firefly.domain.people.core.compliance.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PoliticallyExposedPersonsApi;
import com.firefly.core.customer.sdk.model.PoliticallyExposedPersonDTO;
import com.firefly.domain.people.core.compliance.commands.RegisterPepCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPepHandler extends CommandHandler<RegisterPepCommand, UUID> {

    private final PoliticallyExposedPersonsApi politicallyExposedPersonsApi;

    public RegisterPepHandler(PoliticallyExposedPersonsApi politicallyExposedPersonsApi) {
        this.politicallyExposedPersonsApi = politicallyExposedPersonsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPepCommand cmd) {
        return politicallyExposedPersonsApi
                .createPoliticallyExposedPerson(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(pepDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(pepDTO).getPepId()));
    }
}