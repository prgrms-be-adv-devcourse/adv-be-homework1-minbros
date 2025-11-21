package grepp.homework.repository;

import grepp.homework.entity.PurchaseOrder;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderRepository extends JpaRepository<PurchaseOrder, UUID> {
}
