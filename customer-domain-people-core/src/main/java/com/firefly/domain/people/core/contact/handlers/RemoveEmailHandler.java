package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.EmailContactsApi;
import com.firefly.domain.people.core.contact.commands.RemoveEmailCommand;
import reactor.core.publisher.Mono;

@CommandHandlerComponent
public class RemoveEmailHandler extends CommandHandler<RemoveEmailCommand, Void> {

    private final EmailContactsApi emailContactsApi;

    public RemoveEmailHandler(EmailContactsApi emailContactsApi) {
        this.emailContactsApi = emailContactsApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveEmailCommand cmd) {
        return emailContactsApi
                .deleteEmailContact(cmd.partyId(), cmd.emailId())
                .then();
    }
}