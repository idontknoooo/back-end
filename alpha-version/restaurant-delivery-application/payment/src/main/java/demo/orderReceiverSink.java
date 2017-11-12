package demo;

import com.fasterxml.jackson.databind.ObjectMapper;
import demo.domain.Payment;
import demo.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.integration.annotation.MessageEndpoint;
import org.springframework.integration.annotation.ServiceActivator;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Random;


@EnableBinding(Sink.class)
@Slf4j
@MessageEndpoint
public class orderReceiverSink {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private PaymentService paymentService;

    @ServiceActivator(inputChannel = Sink.INPUT)
    public void confirmPayment(String savedOrder) throws IOException{
        Payment payment = objectMapper.readValue(savedOrder, Payment.class);
        if (!isPaymentValid(payment)) {
            log.error("[Payment Failed!] Invalid Card! Use another card please.");
            return;
        }

        Payment savedPayment = paymentService.savePayment(payment);

        Random rand = new Random();
        int waitTime = 5 + rand.nextInt(56);  // generate an integer [5, 60]
        log.info(String.format("[Payment Successful!] " +
                        "Payment ID is %d. " +
                        "Your credit card is charged %.2f dollars at %s. " +
                        "Order ID is %s. " +
                        "Your order will be delivered in %d minutes",
                savedPayment.getPaymentId(),
                payment.getTotalBalance(),
                payment.getPaymentTimestamp(),
                payment.getOrderId(),
                waitTime)
        );
    }

    private boolean isPaymentValid(Payment payment) {
        String cardNumber = payment.getCardNumber();

        /* card number format: aaaa-bbbb-cccc-dddd
         * Rule: if aaaa <= 1000, it is a invalid card.
         */
        int first4Digits = Integer.valueOf(cardNumber.split("-")[0]);

        return first4Digits > 1000;
    }

}
