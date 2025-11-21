package grepp.homework.order.infrastructure;

import grepp.homework.order.domain.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface OrderJpaRepository extends JpaRepository<PurchaseOrder, UUID> {
}
