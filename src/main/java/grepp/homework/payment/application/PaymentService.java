package grepp.homework.payment.application;

import grepp.homework.payment.application.dto.PaymentCommand;
import grepp.homework.payment.application.dto.PaymentFailureCommand;
import grepp.homework.payment.application.dto.PaymentFailureInfo;
import grepp.homework.payment.application.dto.PaymentInfo;
import grepp.homework.payment.client.TossPaymentClient;
import grepp.homework.payment.client.dto.TossPaymentResponse;
import grepp.homework.payment.domain.Payment;
import grepp.homework.payment.domain.PaymentFailure;
import grepp.homework.payment.domain.PaymentFailureRepository;
import grepp.homework.payment.domain.PaymentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class PaymentService {
    private final PaymentRepository paymentRepository;
    private final PaymentFailureRepository paymentFailureRepository;
    private final TossPaymentClient tossPaymentClient;

    public List<PaymentInfo> getPayments(Pageable pageable) {
        return paymentRepository.findAll(pageable).stream()
                .map(PaymentInfo::from)
                .toList();
    }

    public PaymentInfo confirm(PaymentCommand command) {
        TossPaymentResponse tossPaymentResponse = tossPaymentClient.confirm(command);
        Payment payment = Payment.from(command);

        Instant approvedAt = tossPaymentResponse.approvedAt().toInstant();
        Instant requestedAt = tossPaymentResponse.requestedAt().toInstant();
        payment.markConfirmed(tossPaymentResponse.method(), approvedAt, requestedAt);

        return PaymentInfo.from(paymentRepository.save(payment));
    }

    public PaymentFailureInfo fail(PaymentFailureCommand command) {
        log.info(String.valueOf(command));
        PaymentFailure paymentFailure = PaymentFailure.from(command);
        PaymentFailure savedFailure = paymentFailureRepository.save(paymentFailure);
        return PaymentFailureInfo.from(savedFailure);
    }
}
