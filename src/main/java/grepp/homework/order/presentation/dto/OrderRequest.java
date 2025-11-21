package grepp.homework.order.presentation.dto;

import java.util.UUID;

public record OrderRequest(
        UUID productId,
        UUID memberId
) {
}
