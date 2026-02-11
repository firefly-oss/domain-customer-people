package com.firefly.domain.people.core.customer.queries;

import com.firefly.common.cqrs.query.Query;
import com.firefly.core.customer.sdk.model.LegalEntityDTO;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
public class LegalEntityQuery extends LegalEntityDTO implements Query<LegalEntityDTO> {
    private UUID partyId;

}