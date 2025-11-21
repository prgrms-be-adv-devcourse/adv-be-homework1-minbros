package grepp.homework.payment.presentation;

import grepp.homework.payment.application.PaymentService;
import grepp.homework.payment.application.dto.PaymentCommand;
import grepp.homework.payment.application.dto.PaymentFailureCommand;
import grepp.homework.payment.application.dto.PaymentFailureInfo;
import grepp.homework.payment.application.dto.PaymentInfo;
import grepp.homework.payment.presentation.dto.PaymentFailureRequest;
import grepp.homework.payment.presentation.dto.PaymentRequest;
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

@RestController
@RequiredArgsConstructor
@RequestMapping("${api.v1}/payments")
@Tag(name = "결제 API")
public class PaymentController {
    private final PaymentService paymentService;

    @Operation(summary = "결제 목록 조회", description = "등록된 결제 내역을 조회합니다.")
    @GetMapping
    public ResponseEntity<List<PaymentInfo>> getPayments(
            @PageableDefault(size = 20, sort = "createdAt", direction = Sort.Direction.DESC)
            Pageable pageable
    ) {
        List<PaymentInfo> response = paymentService.getPayments(pageable);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "토스 결제 승인", description = "결제 완료 후 결제를 승인합니다.")
    @PostMapping("/confirm")
    public ResponseEntity<PaymentInfo> confirm(@RequestBody PaymentRequest request) {
        PaymentInfo response = paymentService.confirm(PaymentCommand.from(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(response.id())
                .toUri();
        return ResponseEntity.created(location).body(response);
    }

    @Operation(summary = "결제 실패 기록", description = "결제 실패 기록을 저장합니다.")
    @PostMapping("/fail")
    public ResponseEntity<PaymentFailureInfo> fail(@RequestBody PaymentFailureRequest request) {
        PaymentFailureInfo response = paymentService.fail(PaymentFailureCommand.from(request));
        return ResponseEntity.ok(response);
    }
}
