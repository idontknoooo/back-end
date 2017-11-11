package demo.service.Impl;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.sun.org.apache.xpath.internal.operations.Or;
import demo.domain.Order;
import demo.domain.OrderRepository;
import demo.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService{

    private OrderRepository orderRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository){
        this.orderRepository = orderRepository;
    }

    @Override
    public Page<Order> findAll(Pageable pageable){
        return this.orderRepository.findAll(pageable);
    }

    @Override
    public Page<Order> findByRestaurantId(String restaurantId, Pageable pageable){
        return this.orderRepository.findByRestaurantId(restaurantId, pageable);
    }

    @Override
    public Order findByOrderId(String orderId){
        return this.orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> saveOrder(List<Order> orders){
        return (List<Order>) this.orderRepository.save(orders);
    }

    @Override
    public Order saveOrder(Order order){
        return this.orderRepository.save(order);
    }

    @Override
    public void deleteAll(){
        this.orderRepository.deleteAll();
    }

    @Override
    public void deleteByOrderId(String orderId){
        this.orderRepository.delete(orderId);
    }


    // CircuitBreaker for Order Check
    @HystrixCommand(fallbackMethod = "preCheckOrderFallback")
    @Override
    public boolean preCheckOrder(Order order){
        // If order is valid
        if(order.getTotalPrice() > 0 && !order.isPaid() && !order.isCancelled()){
            return true;
        }
        log.error("Failed during order pre-check. Please check your order.");
        return false;
    }

    public boolean preCheckOrderFallback(Order order){
        log.error("Unable to finish pre-check.");
        return false;
    }
}
