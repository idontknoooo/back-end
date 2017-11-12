package demo.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@RestController
@Slf4j
@EnableBinding(Sink.class)
@MessageEndpoint
public class PaymentRestController {

    @Autowired
    private PaymentService paymentService;

//    @Autowired
//    private SimpMessagingTemplate template;

    @Autowired
    private ObjectMapper mapper;

    @Autowired
    private RestTemplate restTemplate;

    // MessageQueue Sink
    // Receive unpaid order and process payment
    // Once payment is processed, mark order as paid using RestTemplate
    @ServiceActivator(inputChannel = Sink.INPUT)
    @RequestMapping(value = "payment", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Payment savePayment(@RequestBody String paymentInfo) throws IOException{

        // Read and convert value from queue
        Payment payment = mapper.readValue(paymentInfo, Payment.class);
        // Check payment using condition I made up
        if(this.paymentService.checkPayment(payment)){
            // Once passed save payment
            Payment tmpPayment = this.paymentService.savePayment(payment);
            // RestTemplate Call
            String orderService = "http://order";
            restTemplate.put(orderService+"/api/isPaid", payment.getOrderId());
            log.info("Successfully saved payment.");
//            this.template.convertAndSend("/topic/order",payment);
            return tmpPayment;
        }
        log.error("Cannot save payment.");
        return null;
    }

    @RequestMapping(value = "payment", method = RequestMethod.GET)
    public Page<Payment> findAllPayment(Pageable pageable){
        return this.paymentService.findAll(pageable);
    }

    @RequestMapping(value = "payment", method = RequestMethod.DELETE)
    public void deleteAllPayment(){
        this.paymentService.deleteAll();
    }
}
