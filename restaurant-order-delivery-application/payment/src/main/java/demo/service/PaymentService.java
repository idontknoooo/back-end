package demo.service;

import demo.domain.Payment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


public interface PaymentService {

    Payment findByPaymentId(String paymentId);
    Page<Payment> findAll(Pageable pageable);

    Payment savePayment(Payment payment);
    List<Payment> savePayment(List<Payment> paymentList);

    void deleteAll();

    boolean checkPayment(Payment payment);
}
