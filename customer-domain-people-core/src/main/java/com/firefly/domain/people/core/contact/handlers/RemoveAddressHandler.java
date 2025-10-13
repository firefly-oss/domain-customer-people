package com.firefly.domain.people.core.contact.handlers;

import com.firefly.common.cqrs.annotations.CommandHandlerComponent;
import com.firefly.common.cqrs.command.CommandHandler;
import com.firefly.core.customer.sdk.api.AddressesApi;
import com.firefly.domain.people.core.contact.commands.RemoveAddressCommand;
import reactor.core.publisher.Mono;

import java.util.UUID;

@CommandHandlerComponent
public class RemoveAddressHandler extends CommandHandler<RemoveAddressCommand, Void> {

    private final AddressesApi addressesApi;

    public RemoveAddressHandler(AddressesApi addressesApi) {
        this.addressesApi = addressesApi;
    }

    @Override
    protected Mono<Void> doHandle(RemoveAddressCommand cmd) {
        return addressesApi
                .deleteAddress(cmd.partyId(), cmd.addressId())
                .then();
    }
}