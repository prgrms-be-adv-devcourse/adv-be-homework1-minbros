package grepp.homework.entity;

import grepp.homework.dto.OrderCommand;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.UUID;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@EntityListeners(AuditingEntityListener.class)
@Table(name = "purchase_order")
public class PurchaseOrder {
    private static final int DEFAULT_AMOUNT = 1_000;

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false)
    private UUID id;

    @Column(name = "product_id", nullable = false)
    private UUID productId;

    @Column(name = "seller_id", nullable = false)
    private UUID sellerId;

    @Column(name = "member_id", nullable = false)
    private UUID memberId;

    @Column(name = "amount", nullable = false, precision = 15, scale = 2)
    private BigDecimal amount;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 20)
    private PurchaseOrderStatus status;

    @CreatedDate
    @Column(name = "created_at", nullable = false)
    private Instant createdAt;

    @LastModifiedDate
    @Column(name = "updated_at", nullable = false)
    private Instant updatedAt;

    public static PurchaseOrder from(OrderCommand command) {
        return PurchaseOrder.builder()
                .productId(command.productId())
                .sellerId(command.memberId())
                .memberId(command.memberId())
                .amount(BigDecimal.valueOf(DEFAULT_AMOUNT))
                .status(PurchaseOrderStatus.CREATED)
                .build();
    }

    public void markPaid() {
        this.status = PurchaseOrderStatus.PAID;
    }
}
