package grepp.homework.dto;

import java.util.UUID;

public record OrderRequest(
        UUID productId,
        UUID memberId
) {
}
