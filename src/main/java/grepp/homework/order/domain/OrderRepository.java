package grepp.homework.order.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;
import java.util.UUID;

public interface OrderRepository {
    Page<PurchaseOrder> findAll(Pageable pageable);

    PurchaseOrder save(PurchaseOrder order);

    Optional<PurchaseOrder> findById(UUID uuid);
}
