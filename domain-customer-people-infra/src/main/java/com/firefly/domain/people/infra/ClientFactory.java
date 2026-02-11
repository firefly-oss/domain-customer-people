package com.firefly.domain.people.infra;

import com.firefly.core.customer.sdk.api.*;
import com.firefly.core.customer.sdk.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * Default implementation of the ClientFactory interface.
 * Creates client service instances using the appropriate API clients and dependencies.
 */
@Component
public class ClientFactory {

    private final ApiClient apiClient;

    @Autowired
    public ClientFactory(
            CustomerMgmtProperties customerMgmtProperties) {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath(customerMgmtProperties.getBasePath());
    }

    @Bean
    public PartiesApi partiesApi() {
        return new PartiesApi(apiClient);
    }

    @Bean
    public NaturalPersonsApi naturalPersonsApi() {
        return new NaturalPersonsApi(apiClient);
    }

    @Bean
    public LegalEntitiesApi legalEntitiesApi() {
        return new LegalEntitiesApi(apiClient);
    }

    @Bean
    public PartyStatusesApi partyStatusesApi() {
        return new PartyStatusesApi(apiClient);
    }

    @Bean
    public PoliticallyExposedPersonsApi politicallyExposedPersonsApi() {
        return new PoliticallyExposedPersonsApi(apiClient);
    }

    @Bean
    public IdentityDocumentsApi identityDocumentsApi() {
        return new IdentityDocumentsApi(apiClient);
    }

    @Bean
    public AddressesApi addressesApi() {
        return new AddressesApi(apiClient);
    }

    @Bean
    public EmailContactsApi emailContactsApi() {
        return new EmailContactsApi(apiClient);
    }

    @Bean
    public PhoneContactsApi phoneContactsApi() {
        return new PhoneContactsApi(apiClient);
    }

    @Bean
    public PartyEconomicActivitiesApi partyEconomicActivitiesApi() {
        return new PartyEconomicActivitiesApi(apiClient);
    }

    @Bean
    public ConsentsApi consentsApi() {
        return new ConsentsApi(apiClient);
    }

    @Bean
    public PartyProvidersApi partyProvidersApi() {
        return new PartyProvidersApi(apiClient);
    }

    @Bean
    public PartyRelationshipsApi partyRelationshipsApi() {
        return new PartyRelationshipsApi(apiClient);
    }

    @Bean
    public PartyGroupMembershipsApi partyGroupMembershipsApi() {
        return new PartyGroupMembershipsApi(apiClient);
    }

}
