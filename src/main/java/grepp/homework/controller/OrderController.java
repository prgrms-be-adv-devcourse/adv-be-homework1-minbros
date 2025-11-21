package grepp.homework.controller;

import grepp.homework.dto.OrderCommand;
import grepp.homework.dto.OrderInfo;
import grepp.homework.dto.OrderRequest;
import grepp.homework.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/orders")
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
    public ResponseEntity<List<OrderInfo>> getOrders(Pageable pageable) {
        List<OrderInfo> response = orderService.getOrders(pageable);
        return ResponseEntity.ok(response);
    }
}





