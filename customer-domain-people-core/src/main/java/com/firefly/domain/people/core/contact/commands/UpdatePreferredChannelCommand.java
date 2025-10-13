package com.firefly.domain.people.core.contact.commands;

import com.firefly.common.cqrs.command.Command;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.UUID;

@Data
public class UpdatePreferredChannelCommand implements Command<Void>{

    UpdateEmailCommand emailCommand;
    UpdatePhoneCommand phoneCommand;
    private UUID partyId;

    public UpdatePreferredChannelCommand withPartyId(UUID partyId) {
        this.setPartyId(partyId);
        return this;
    }

}
