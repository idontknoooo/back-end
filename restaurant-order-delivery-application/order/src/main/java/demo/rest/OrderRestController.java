package demo.rest;
import demo.domain.Dish;
import demo.domain.Order;
import demo.service.OrderService;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.integration.support.MessageBuilder;
import org.springframework.messaging.MessageChannel;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Random;

@Slf4j
@RestController
@EnableBinding(Source.class)
//@Data
public class OrderRestController {

    @Autowired
    private OrderService orderService;

//    @Autowired
//    public OrderRestController(OrderService orderService){
//        this.orderService = orderService;
//    }

    @Autowired
    private MessageChannel output;

    // MessageQueue Source
    // Setup Order and Check whether it is valid
    // Then send to messageQueue
    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public Order saveOrder(@RequestBody Order order){

        // Calculate totalPrice
        double totalPrice = 0;
        for(Dish dish : order.getOrderedFood()){
            totalPrice += dish.getDishPrice();
        }
        order.setTotalPrice(totalPrice);
        Order tmpOrder = this.orderService.saveOrder(order);

        // Setup orderTime
        order.setOrderTime(new Date());

        if(this.orderService.preCheckOrder(order)){
            this.output.send(MessageBuilder.withPayload(order).build());
            log.info("Order is pre-checked. Please pay your order.");
        }
        return tmpOrder;
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public Page<Order> findAllOrder(Pageable pageable){
        return this.orderService.findAll(pageable);
    }

    @RequestMapping(value = "order", method = RequestMethod.DELETE)
    public void deleteAllOrder(){
        this.orderService.deleteAll();
    }

    // RestTemplate Call Receiver, call is from Payment POST method
    @RequestMapping(value = "api/isPaid", method = RequestMethod.PUT)
    public void isPaid(@RequestBody String orderId){
        Order order = this.orderService.findByOrderId(orderId);
        order.setPaid(true);

        // Set Random delivery time
        Random random = new Random();
        order.setEstimatedDeliveryTime(Integer.toString(5+random.nextInt(56)) + " minutes.");
        // Save order
        this.orderService.saveOrder(order);
    }
}
