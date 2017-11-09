package demo.rest;

import demo.domain.Order;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class AllRestController {

    private OrderService orderService;

    @Autowired
    public AllRestController(OrderService orderService){
        this.orderService = orderService;
    }

    @RequestMapping(value = "order/{orderId}", method = RequestMethod.GET)
    public Order findByOrderId(@PathVariable("orderId") String orderId){
        return this.orderService.findByOrderId(orderId);
    }

    @RequestMapping(value = "order", method = RequestMethod.GET)
    public Page<Order> findAll(Pageable pageable){
        return this.orderService.findAll(pageable);
    }

    @RequestMapping(value = "order", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Order> saveOrder(@RequestBody List<Order> orderList){
        return this.orderService.saveOrder(orderList);
    }

    @RequestMapping(value = "order", method = RequestMethod.DELETE)
    public void deleteAllOrder(){
        this.orderService.deleteAll();
    }

    @RequestMapping(value = "order/{orderId}", method = RequestMethod.DELETE)
    public void deleteByOrderId(@PathVariable("orderId") String orderId){
        this.orderService.deleteByOrderId(orderId);
    }

    // Use POST
//    @RequestMapping(value = "order/isPaid/{orderId}", method = RequestMethod.PATCH)
//    public void setPaidByOrderId(@PathVariable("orderId") String orderId, @RequestBody boolean paid){
//        this.orderService.setIsPaidByOrderId(orderId, paid);
//    }
//
//    @RequestMapping(value = "order/isPaid/{orderId}", method = RequestMethod.GET)
//    public boolean getPaidByOrderId(@PathVariable("orderId") String orderId){
//        return this.orderService.findByOrderId(orderId).isPaid();
//    }
//
    @RequestMapping(value = "order/isCancelled/{orderId}", method = RequestMethod.PUT)
    public void setCancelledByOrderId(@PathVariable("orderId") String orderId, @RequestBody boolean cancelled){
        this.orderService.setIsCancelledByOrderId(orderId, cancelled);
    }

    @RequestMapping(value = "order/isCancelled/{orderId}", method = RequestMethod.GET)
    public boolean getCancelledByOrderId(@PathVariable("orderId") String orderId){
        return this.orderService.findByOrderId(orderId).isCancelled();
    }
}
