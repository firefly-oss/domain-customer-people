package com.firefly.domain.people.core.contact.services.impl;

import com.firefly.domain.people.core.contact.commands.RegisterAddressCommand;
import com.firefly.domain.people.core.contact.commands.RegisterEmailCommand;
import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import com.firefly.domain.people.core.contact.commands.RegisterPhoneCommand;
import com.firefly.domain.people.core.contact.commands.RemoveAddressCommand;
import com.firefly.domain.people.core.contact.commands.RemoveEmailCommand;
import com.firefly.domain.people.core.contact.commands.RemoveIdentityDocumentCommand;
import com.firefly.domain.people.core.contact.commands.RemovePhoneCommand;
import com.firefly.domain.people.core.contact.commands.UpdateAddressCommand;
import com.firefly.domain.people.core.contact.commands.UpdateEmailCommand;
import com.firefly.domain.people.core.contact.commands.UpdatePhoneCommand;
import com.firefly.domain.people.core.contact.commands.UpdatePreferredChannelCommand;
import com.firefly.domain.people.core.contact.services.ContactService;
import com.firefly.domain.people.core.contact.workflows.AddAddressSaga;
import com.firefly.domain.people.core.contact.workflows.AddEmailSaga;
import com.firefly.domain.people.core.contact.workflows.AddIdentityDocumentSaga;
import com.firefly.domain.people.core.contact.workflows.AddPhoneSaga;
import com.firefly.domain.people.core.contact.workflows.RemoveAddressSaga;
import com.firefly.domain.people.core.contact.workflows.RemoveEmailSaga;
import com.firefly.domain.people.core.contact.workflows.RemoveIdentityDocumentSaga;
import com.firefly.domain.people.core.contact.workflows.RemovePhoneSaga;
import com.firefly.domain.people.core.contact.workflows.SetPreferredChannelSaga;
import com.firefly.domain.people.core.contact.workflows.UpdateAddressSaga;
import com.firefly.domain.people.core.contact.workflows.UpdateEmailSaga;
import com.firefly.domain.people.core.contact.workflows.UpdatePhoneSaga;
import org.fireflyframework.transactional.saga.core.SagaResult;
import org.fireflyframework.transactional.saga.engine.SagaEngine;
import org.fireflyframework.transactional.saga.engine.StepInputs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Service
public class ContactServiceImpl implements ContactService {

    private final SagaEngine engine;

    @Autowired
    public ContactServiceImpl(SagaEngine engine) {
        this.engine = engine;
    }

    @Override
    public Mono<SagaResult> addAddress(UUID partyId, RegisterAddressCommand addressCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(AddAddressSaga::registerAddress, addressCommand.withPartyId(partyId))
                .build();

        return engine.execute(AddAddressSaga.class, inputs);
    }

    @Override
    public Mono<Void> updateAddress(UUID partyId, UUID addressId, UpdateAddressCommand addressData) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateAddressSaga::updateAddress, addressData.withAddressId(addressId).withPartyId(partyId))
                .build();

        return engine.execute(UpdateAddressSaga.class, inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removeAddress(UUID partyId, UUID addressId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RemoveAddressSaga::removeAddress, new RemoveAddressCommand(partyId, addressId))
                .build();

        return engine.execute(RemoveAddressSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> addEmail(UUID partyId, RegisterEmailCommand emailCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(AddEmailSaga::registerEmail, emailCommand.withPartyId(partyId))
                .build();

        return engine.execute(AddEmailSaga.class, inputs);
    }

    @Override
    public Mono<Void> updateEmail(UUID partyId, UUID emailId, UpdateEmailCommand emailData) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdateEmailSaga::updateEmail, emailData.withEmailContactId(emailId).withPartyId(partyId))
                .build();

        return engine.execute(UpdateEmailSaga.class, inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removeEmail(UUID partyId, UUID emailId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RemoveEmailSaga::removeEmail, new RemoveEmailCommand(partyId, emailId))
                .build();

        return engine.execute(RemoveEmailSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> addPhone(UUID partyId, RegisterPhoneCommand phoneCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(AddPhoneSaga::registerPhone, phoneCommand.withPartyId(partyId))
                .build();

        return engine.execute(AddPhoneSaga.class, inputs);
    }

    @Override
    public Mono<Void> updatePhone(UUID partyId, UUID phoneId, UpdatePhoneCommand phoneData) {
        StepInputs inputs = StepInputs.builder()
                .forStep(UpdatePhoneSaga::updatePhone, phoneData.withPhoneContactId(phoneId).withPartyId(partyId))
                .build();

        return engine.execute(UpdatePhoneSaga.class, inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removePhone(UUID partyId, UUID phoneId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RemovePhoneSaga::removePhone, new RemovePhoneCommand(partyId, phoneId))
                .build();

        return engine.execute(RemovePhoneSaga.class, inputs);
    }

    @Override
    public Mono<Void> setPreferredChannel(UUID partyId, UpdatePreferredChannelCommand channelData) {
        StepInputs inputs = StepInputs.builder()
                .forStep(SetPreferredChannelSaga::updateChannel, channelData.withPartyId(partyId))
                .build();

        return engine.execute(SetPreferredChannelSaga.class, inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> addIdentityDocument(UUID partyId, RegisterIdentityDocumentCommand identityDocumentCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStep(AddIdentityDocumentSaga::registerIdentityDocument, identityDocumentCommand.withPartyId(partyId))
                .build();

        return engine.execute(AddIdentityDocumentSaga.class, inputs);
    }

    @Override
    public Mono<SagaResult> removeIdentityDocument(UUID partyId, UUID identityDocumentId) {
        StepInputs inputs = StepInputs.builder()
                .forStep(RemoveIdentityDocumentSaga::removeIdentityDocument, new RemoveIdentityDocumentCommand(partyId, identityDocumentId))
                .build();

        return engine.execute(RemoveIdentityDocumentSaga.class, inputs);
    }
}