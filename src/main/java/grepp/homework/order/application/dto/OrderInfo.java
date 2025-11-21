package grepp.homework.order.application.dto;

import grepp.homework.order.domain.PurchaseOrder;
import grepp.homework.order.domain.PurchaseOrderStatus;

import java.math.BigDecimal;
import java.util.UUID;

public record OrderInfo(
        UUID id,
        BigDecimal amount,
        PurchaseOrderStatus status
) {
    public static OrderInfo from(PurchaseOrder order) {
        return new OrderInfo(
                order.getId(),
                order.getAmount(),
                order.getStatus()
        );
    }
}
