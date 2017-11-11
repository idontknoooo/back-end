package demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@Slf4j
// TODO: Seems like These 2 annotations are not suitable in RestController
@EnableBinding(Sink.class)
@MessageEndpoint
//@Data
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

//    @Autowired
//    public PaymentRestController(PaymentService paymentService){
//        this.paymentService = paymentService;
//    }

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    @ServiceActivator(inputChannel = Sink.INPUT)
    @RequestMapping(value = "payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Payment savePayment(@RequestBody String paymentInfo) throws IOException{
        Payment payment = mapper.readValue(paymentInfo, Payment.class);
        System.out.println("Payment Controller 1");
//        Payment payment = paymentInfo;

        System.out.println("Payment Controller 2");
        if(this.paymentService.checkPayment(payment)){
            Payment tmpPayment = this.paymentService.savePayment(payment);

            System.out.println("Payment Controller 3");
            String orderService = "http://order";
            restTemplate.put(orderService+"/api/isPaid", payment.getOrderId());

            System.out.println("Payment Controller 4");
            log.info("Successfully saved payment.");
            return tmpPayment;
        }
        log.error("Cannot save payment.");
        return null;
    }
}
