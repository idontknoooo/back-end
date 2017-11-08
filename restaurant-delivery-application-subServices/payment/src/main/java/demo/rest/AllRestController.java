package demo.rest;

import demo.domain.Payment;
import demo.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class AllRestController {

    private PaymentService paymentService;

    @Autowired
    public AllRestController(PaymentService paymentService){
        this.paymentService = paymentService;
    }

    @RequestMapping(value = "payment/{paymentId}", method = RequestMethod.GET)
    public Payment findByPaymentId(@PathVariable(value = "paymentId") String paymentId){
        return this.paymentService.findByPaymentId(paymentId);
    }

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    public Page<Payment> findAll(Pageable pageable){
        return this.paymentService.findAll(pageable);
    }

    @RequestMapping(value = "payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Payment> savePayment(@RequestBody List<Payment> payments){
        return this.paymentService.savePayment(payments);
    }

    @RequestMapping(value = "payment", method = RequestMethod.DELETE)
    public void deleteAllPayment(){
        this.paymentService.deleteAll();
    }

    @RequestMapping(value = "payment/{paymentId}", method = RequestMethod.DELETE)
    public void deleteByPaymentId(@PathVariable("paymentId") String paymentId){
        this.paymentService.deleteByPaymentId(paymentId);
    }
}
