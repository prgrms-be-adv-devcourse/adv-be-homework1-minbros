package grepp.homework.payment.infrastructure;

import grepp.homework.payment.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

interface PaymentJpaRepository extends JpaRepository<Payment, UUID> {
}
