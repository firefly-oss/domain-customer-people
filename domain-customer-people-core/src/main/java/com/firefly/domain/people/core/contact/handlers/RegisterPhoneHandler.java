package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.PhoneContactsApi;
import com.firefly.core.customer.sdk.model.PhoneContactDTO;
import com.firefly.domain.people.core.contact.commands.RegisterPhoneCommand;
import reactor.core.publisher.Mono;

import java.util.Objects;
import java.util.UUID;

@CommandHandlerComponent
public class RegisterPhoneHandler extends CommandHandler<RegisterPhoneCommand, UUID> {

    private final PhoneContactsApi phoneContactsApi;

    public RegisterPhoneHandler(PhoneContactsApi phoneContactsApi) {
        this.phoneContactsApi = phoneContactsApi;
    }

    @Override
    protected Mono<UUID> doHandle(RegisterPhoneCommand cmd) {
        return phoneContactsApi
                .createPhoneContact(cmd.getPartyId(), cmd, UUID.randomUUID().toString())
                .mapNotNull(phoneContactDTO ->
                        Objects.requireNonNull(Objects.requireNonNull(phoneContactDTO).getPhoneContactId()));
    }
}