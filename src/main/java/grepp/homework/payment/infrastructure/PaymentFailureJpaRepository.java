package grepp.homework.payment.infrastructure;

import grepp.homework.payment.domain.PaymentFailure;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PaymentFailureJpaRepository extends JpaRepository<PaymentFailure, UUID> {
}
