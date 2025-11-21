package grepp.homework.dto;

import grepp.homework.entity.PurchaseOrder;
import grepp.homework.entity.PurchaseOrderStatus;

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
