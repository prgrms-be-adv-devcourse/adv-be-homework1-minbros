package grepp.homework.service;

import grepp.homework.dto.OrderCommand;
import grepp.homework.dto.OrderInfo;
import grepp.homework.entity.PurchaseOrder;
import grepp.homework.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

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
        PurchaseOrder savedOrder = orderRepository.save(order);
        return OrderInfo.from(savedOrder);
    }
}
