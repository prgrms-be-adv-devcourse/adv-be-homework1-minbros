package grepp.homework.payment.application.dto;

import grepp.homework.payment.presentation.dto.PaymentRequest;

public record PaymentCommand(
        String paymentKey,
        String orderId,
        Long amount
) {
    public static PaymentCommand from(PaymentRequest request) {
        return new PaymentCommand(request.paymentKey(), request.orderId(), request.amount());
    }
}
