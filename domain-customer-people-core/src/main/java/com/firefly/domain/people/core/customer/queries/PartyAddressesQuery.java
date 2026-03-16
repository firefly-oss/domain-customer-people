package com.firefly.domain.people.core.customer.queries;

import com.firefly.core.customer.sdk.model.PaginationResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.fireflyframework.cqrs.query.Query;

import java.util.UUID;

@EqualsAndHashCode(callSuper = true)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PartyAddressesQuery extends PaginationResponse implements Query<PaginationResponse> {
    private UUID partyId;
}
