package grepp.homework.order.presentation;

import grepp.homework.order.application.dto.OrderCommand;
import grepp.homework.order.application.dto.OrderInfo;
import grepp.homework.order.presentation.dto.OrderRequest;
import grepp.homework.order.application.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/orders")
@Tag(name = "주문 API")
public class OrderController {
    private final OrderService orderService;

    @Operation(summary = "주문 생성", description = "상품과 구매자 정보를 바탕으로 주문을 생성한다.")
    @PostMapping
    public ResponseEntity<OrderInfo> createOrder(@RequestBody OrderRequest request) {
        OrderInfo response = orderService.createOrder(OrderCommand.from(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "주문 목록 조회", description = "생성된 주문을 페이지 단위로 조회한다.")
    @GetMapping
    public ResponseEntity<List<OrderInfo>> getOrders(
            @PageableDefault(size = 20, sort = "updatedAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        List<OrderInfo> response = orderService.getOrders(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "결제 완료", description = "주문의 상태를 결제 완료로 변경합니다.")
    @PatchMapping("/{id}/paid")
    public ResponseEntity<OrderInfo> completeOrder(@PathVariable UUID id) {
        OrderInfo response = orderService.completeOrder(id);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "결제 취소", description = "주문의 상태를 결제 취소로 변경합니다.")
    @PatchMapping("/{id}/cancel")
    public ResponseEntity<OrderInfo> cancelOrder(@PathVariable UUID id) {
        OrderInfo response = orderService.cancelOrder(id);
        return ResponseEntity.ok(response);
    }
}





