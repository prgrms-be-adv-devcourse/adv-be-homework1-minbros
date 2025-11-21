package grepp.homework.payment.application.dto;

import grepp.homework.payment.presentation.dto.PaymentFailureRequest;

public record PaymentFailureCommand(
        String orderId,
        String paymentKey,
        String errorCode,
        String errorMessage,
        Long amount,
        String rawPayload
) {
    public static PaymentFailureCommand from(PaymentFailureRequest request) {
        return new PaymentFailureCommand(
                request.orderId(),
                request.paymentKey(),
                request.errorCode(),
                request.errorMessage(),
                request.amount(),
                request.rawPayload()
        );
    }
}
