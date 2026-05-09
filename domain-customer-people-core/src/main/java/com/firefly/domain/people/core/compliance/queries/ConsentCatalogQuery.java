package com.firefly.domain.people.core.compliance.queries;

import com.firefly.domain.people.core.compliance.queries.responses.ConsentCatalogResponse;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.fireflyframework.cqrs.query.Query;

import java.util.List;

/**
 * Query the active consent catalogue maintained by
 * {@code core-common-reference-master-data}.
 * <p>
 * When {@link #applicableProduct} is set, only catalogue entries that target
 * that product (or that have no product affinity at all) are returned. The
 * filter is intentionally permissive so global consents (terms of service,
 * privacy policy) are always surfaced alongside product-specific ones.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ConsentCatalogQuery implements Query<List<ConsentCatalogResponse>> {

    /** Optional product filter, e.g. {@code PERSONAL_LOAN} or {@code LEASING}. */
    private String applicableProduct;

}
