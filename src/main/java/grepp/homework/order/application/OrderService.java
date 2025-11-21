package grepp.homework.order.application;

import grepp.homework.order.application.dto.OrderCommand;
import grepp.homework.order.application.dto.OrderInfo;
import grepp.homework.order.domain.OrderRepository;
import grepp.homework.order.domain.PurchaseOrder;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepository orderRepository;

    public List<OrderInfo> getOrders(Pageable pageable) {
        return orderRepository.findAll(pageable)
                .stream()
                .map(OrderInfo::from)
                .toList();
    }

    public OrderInfo createOrder(OrderCommand command) {
        PurchaseOrder order = PurchaseOrder.from(command);
        return OrderInfo.from(orderRepository.save(order));
    }

    public OrderInfo completeOrder(UUID id) {
        PurchaseOrder order = getOrder(id);
        order.markPaid();
        return OrderInfo.from(orderRepository.save(order));
    }

    public OrderInfo cancelOrder(UUID id) {
        PurchaseOrder order = getOrder(id);
        order.markCanceled();
        return OrderInfo.from(orderRepository.save(order));

    }

    private PurchaseOrder getOrder(UUID id) {
        return orderRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("No order found by: " + id));
    }
}
