package grepp.homework.dto;

import java.util.UUID;

public record OrderCommand(
    UUID productId,
    UUID memberId
) {
    public static OrderCommand from(OrderRequest request) {
        return new OrderCommand(
                request.productId(),
                request.memberId()
        );
    }
}
