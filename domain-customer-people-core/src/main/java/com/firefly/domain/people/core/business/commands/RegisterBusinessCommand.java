package com.firefly.domain.people.core.business.commands;

import com.firefly.domain.people.core.party.commands.RegisterPartyCommand;
import com.firefly.domain.people.core.status.commands.RegisterPartyStatusEntryCommand;
import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import com.firefly.domain.people.core.contact.commands.RegisterAddressCommand;
import com.firefly.domain.people.core.contact.commands.RegisterEmailCommand;
import com.firefly.domain.people.core.contact.commands.RegisterPhoneCommand;
import com.firefly.domain.people.core.compliance.commands.RegisterEconomicActivityLinkCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyProviderCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyGroupMembershipCommand;

import java.util.List;


public record RegisterBusinessCommand(
        RegisterPartyCommand party,
        RegisterLegalEntityCommand legalEntity,
        List<RegisterPartyStatusEntryCommand> statusHistory,
        List<RegisterIdentityDocumentCommand> identityDocuments,
        List<RegisterAddressCommand> addresses,
        List<RegisterEmailCommand> emails,
        List<RegisterPhoneCommand> phones,
        List<RegisterEconomicActivityLinkCommand> economicActivities,
        List<RegisterPartyProviderCommand> providers,
        List<RegisterPartyRelationshipCommand> relationships,
        List<RegisterPartyGroupMembershipCommand> groupMemberships
) {}
