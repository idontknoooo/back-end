package demo;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@EnableBinding(Source.class)
@Slf4j
public class orderReceiverSource {

    @Autowired
    private MessageChannel output;

    @HystrixCommand(fallbackMethod = "orderSourceFallback")
    @RequestMapping(value = "/api/orderMq", method = RequestMethod.POST)
    public void orderSource(@RequestBody String savedOrder){
        System.out.println("CheckpointSource1");
        this.output.send(MessageBuilder.withPayload(savedOrder).build());
        log.info("Sent order. Proceed to payment.");
    }

    @SuppressWarnings("unused")
    public void orderSourceFallback(String savedOrder){
        log.error("Unable to send order!");
    }
}
