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
import org.fireflyframework.orchestration.saga.engine.SagaResult;
import org.fireflyframework.orchestration.saga.engine.SagaEngine;
import org.fireflyframework.orchestration.saga.engine.StepInputs;
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
                .forStepId("registerAddress", addressCommand.withPartyId(partyId))
                .build();

        return engine.execute("AddAddressSaga", inputs);
    }

    @Override
    public Mono<Void> updateAddress(UUID partyId, UUID addressId, UpdateAddressCommand addressData) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updateAddress", addressData.withAddressId(addressId).withPartyId(partyId))
                .build();

        return engine.execute("UpdateAddressSaga", inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removeAddress(UUID partyId, UUID addressId) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("removeAddress", new RemoveAddressCommand(partyId, addressId))
                .build();

        return engine.execute("RemoveAddressSaga", inputs);
    }

    @Override
    public Mono<SagaResult> addEmail(UUID partyId, RegisterEmailCommand emailCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerEmail", emailCommand.withPartyId(partyId))
                .build();

        return engine.execute("AddEmailSaga", inputs);
    }

    @Override
    public Mono<Void> updateEmail(UUID partyId, UUID emailId, UpdateEmailCommand emailData) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updateEmail", emailData.withEmailContactId(emailId).withPartyId(partyId))
                .build();

        return engine.execute("UpdateEmailSaga", inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removeEmail(UUID partyId, UUID emailId) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("removeEmail", new RemoveEmailCommand(partyId, emailId))
                .build();

        return engine.execute("RemoveEmailSaga", inputs);
    }

    @Override
    public Mono<SagaResult> addPhone(UUID partyId, RegisterPhoneCommand phoneCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerPhone", phoneCommand.withPartyId(partyId))
                .build();

        return engine.execute("AddPhoneSaga", inputs);
    }

    @Override
    public Mono<Void> updatePhone(UUID partyId, UUID phoneId, UpdatePhoneCommand phoneData) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updatePhone", phoneData.withPhoneContactId(phoneId).withPartyId(partyId))
                .build();

        return engine.execute("UpdatePhoneSaga", inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> removePhone(UUID partyId, UUID phoneId) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("removePhone", new RemovePhoneCommand(partyId, phoneId))
                .build();

        return engine.execute("RemovePhoneSaga", inputs);
    }

    @Override
    public Mono<Void> setPreferredChannel(UUID partyId, UpdatePreferredChannelCommand channelData) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("updateChannel", channelData.withPartyId(partyId))
                .build();

        return engine.execute("SetPreferredChannelSaga", inputs)
                .then();
    }

    @Override
    public Mono<SagaResult> addIdentityDocument(UUID partyId, RegisterIdentityDocumentCommand identityDocumentCommand) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("registerIdentityDocument", identityDocumentCommand.withPartyId(partyId))
                .build();

        return engine.execute("AddIdentityDocumentSaga", inputs);
    }

    @Override
    public Mono<SagaResult> removeIdentityDocument(UUID partyId, UUID identityDocumentId) {
        StepInputs inputs = StepInputs.builder()
                .forStepId("removeIdentityDocument", new RemoveIdentityDocumentCommand(partyId, identityDocumentId))
                .build();

        return engine.execute("RemoveIdentityDocumentSaga", inputs);
    }
}