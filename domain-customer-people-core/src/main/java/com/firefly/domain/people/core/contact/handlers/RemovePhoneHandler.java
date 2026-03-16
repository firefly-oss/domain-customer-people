package com.firefly.domain.people.core.contact.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PhoneContactsApi;
import com.firefly.domain.people.core.contact.commands.RemovePhoneCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemovePhoneHandler extends CommandHandler<RemovePhoneCommand, Void> {

    private final PhoneContactsApi phoneContactsApi;

    public RemovePhoneHandler(PhoneContactsApi phoneContactsApi) {
        this.phoneContactsApi = phoneContactsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemovePhoneCommand cmd) {
        return phoneContactsApi
                .deletePhoneContact(cmd.partyId(), cmd.phoneId(), UUID.randomUUID().toString())
                .then();
    }
}