package com.firefly.domain.people.infra;

import com.firefly.core.lending.servicing.sdk.api.LoanServicingCaseApi;
import com.firefly.core.lending.servicing.sdk.invoker.ApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class LoanServicingClientFactory {

    private final ApiClient apiClient;

    @Autowired
    public LoanServicingClientFactory(LoanServicingProperties properties) {
        this.apiClient = new ApiClient();
        this.apiClient.setBasePath(properties.getBasePath());
    }

    @Bean
    public LoanServicingCaseApi loanServicingCaseApi() {
        return new LoanServicingCaseApi(apiClient);
    }
}
