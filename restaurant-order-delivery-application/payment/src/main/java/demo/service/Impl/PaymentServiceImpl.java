package demo.service.Impl;

import demo.domain.Payment;
import demo.domain.PaymentRepository;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment findByPaymentId(String paymentId){
        return this.paymentRepository.findByPaymentId(paymentId);
    }

    @Override
    public Page<Payment> findAll(Pageable pageable){
        return this.paymentRepository.findAll(pageable);
    }

    @Override
    public Payment savePayment(Payment payment){
        return this.paymentRepository.save(payment);
    }

    @Override
    public List<Payment> savePayment(List<Payment> paymentList){
        return (List<Payment>) this.paymentRepository.save(paymentList);
    }

    @Override
    public void deleteAll(){
        this.paymentRepository.deleteAll();
    }

    // Check whether payment is valid
    @Override
    public boolean checkPayment(Payment payment){
        // Follow is a condition I made up
        if(payment.getCardNumber().length()<16){
            log.error("Failed during payment check. Please check your payment.");
            return false;
        }
        return true;
    }
}
