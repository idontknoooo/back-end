package demo.service;

import demo.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface PaymentService {

    Page<Payment> findAll(Pageable pageable);
    Payment findByPaymentId(String paymentId);

    List<Payment> savePayment(List<Payment> paymentList);
    Payment savePayment(Payment payment);

    void deleteAll();
    void deleteByPaymentId(String paymentId);
}
