package demo.service;

import demo.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> findAll(Pageable pageable);
    Page<Order> findByRestaurantId(String restaurantId, Pageable pageable);
    Order findByOrderId(String orderId);

    List<Order> saveOrder(List<Order> orders);
    Order saveOrder(Order order);

    void deleteAll();
    void deleteByOrderId(String orderId);

    boolean preCheckOrder(Order order);

}
