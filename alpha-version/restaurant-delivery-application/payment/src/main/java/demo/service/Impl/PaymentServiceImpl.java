package demo.service.Impl;

import demo.domain.Payment;
import demo.domain.PaymentRepository;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentServiceImpl implements PaymentService{

    private PaymentRepository paymentRepository;

    @Autowired
    public PaymentServiceImpl(PaymentRepository paymentRepository){
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Page<Payment> findAll(Pageable pageable){
        return this.paymentRepository.findAll(pageable);
    }

    @Override
    public Payment findByPaymentId(String paymentId){
        return this.paymentRepository.findByPaymentId(paymentId);
    }

    @Override
    public List<Payment> savePayment(List<Payment> paymentList){
        return (List<Payment>) this.paymentRepository.save(paymentList);
    }

    @Override
    public Payment savePayment(Payment payment){
        return this.paymentRepository.save(payment);
    }

    @Override
    public void deleteAll(){
        this.paymentRepository.deleteAll();
    }

    @Override
    public void deleteByPaymentId(String paymentId){
        this.paymentRepository.delete(paymentId);
    }
}
