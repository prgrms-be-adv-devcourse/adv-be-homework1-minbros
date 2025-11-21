package grepp.homework.order.infrastructure;

import grepp.homework.order.domain.OrderRepository;
import grepp.homework.order.domain.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
@RequiredArgsConstructor
public class OrderRepositoryAdapter implements OrderRepository {
    private final OrderJpaRepository orderJpaRepository;

    @Override
    public Page<PurchaseOrder> findAll(Pageable pageable) {
        return orderJpaRepository.findAll(pageable);
    }

    @Override
    public Optional<PurchaseOrder> findById(UUID uuid) {
        return orderJpaRepository.findById(uuid);
    }

    @Override
    public PurchaseOrder save(PurchaseOrder order) {
        return orderJpaRepository.save(order);
    }
}
