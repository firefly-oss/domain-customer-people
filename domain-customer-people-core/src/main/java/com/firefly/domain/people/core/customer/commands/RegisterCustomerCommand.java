package com.firefly.domain.people.core.customer.commands;

import com.firefly.domain.people.core.party.commands.RegisterPartyCommand;
import com.firefly.domain.people.core.status.commands.RegisterPartyStatusEntryCommand;
import com.firefly.domain.people.core.compliance.commands.RegisterPepCommand;
import com.firefly.domain.people.core.contact.commands.RegisterIdentityDocumentCommand;
import com.firefly.domain.people.core.contact.commands.RegisterAddressCommand;
import com.firefly.domain.people.core.contact.commands.RegisterEmailCommand;
import com.firefly.domain.people.core.contact.commands.RegisterPhoneCommand;
import com.firefly.domain.people.core.compliance.commands.RegisterEconomicActivityLinkCommand;
import com.firefly.domain.people.core.compliance.commands.RegisterConsentCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyProviderCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyRelationshipCommand;
import com.firefly.domain.people.core.party.commands.RegisterPartyGroupMembershipCommand;

import java.util.List;

/**
 * Command record for customer registration operations.
 * 
 * This record encapsulates all the necessary information required to register a customer,
 * supporting both natural persons and legal entities. The structure includes party information,
 * personal details, contact information, relationships, and various customer-specific data.
 * 
 * Some fields are specific to natural persons (pep, consents) and should be null when 
 * registering legal entities. The registration process is orchestrated as a saga to ensure
 * data consistency across multiple related entities.
 */
public record RegisterCustomerCommand(
        RegisterPartyCommand party,
        RegisterNaturalPersonCommand naturalPerson,
        List<RegisterPartyStatusEntryCommand> statusHistory,
        RegisterPepCommand pep, //Natural person only
        List<RegisterIdentityDocumentCommand> identityDocuments,
        List<RegisterAddressCommand> addresses,
        List<RegisterEmailCommand> emails,
        List<RegisterPhoneCommand> phones,
        List<RegisterEconomicActivityLinkCommand> economicActivities,
        List<RegisterConsentCommand> consents, //Natural person only
        List<RegisterPartyProviderCommand> providers,
        List<RegisterPartyRelationshipCommand> relationships,
        List<RegisterPartyGroupMembershipCommand> groupMemberships
) {}
