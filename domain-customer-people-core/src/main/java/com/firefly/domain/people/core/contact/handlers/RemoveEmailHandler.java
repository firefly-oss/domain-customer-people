package com.firefly.domain.people.core.contact.handlers;

import org.fireflyframework.cqrs.annotations.CommandHandlerComponent;
import org.fireflyframework.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.domain.people.core.contact.commands.RemoveEmailCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemoveEmailHandler extends CommandHandler<RemoveEmailCommand, Void> {

    private final EmailContactsApi emailContactsApi;

    public RemoveEmailHandler(EmailContactsApi emailContactsApi) {
        this.emailContactsApi = emailContactsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveEmailCommand cmd) {
        return emailContactsApi
                .deleteEmailContact(cmd.partyId(), cmd.emailId(), UUID.randomUUID().toString())
                .then();
    }
}