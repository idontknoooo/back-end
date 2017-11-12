package demo.service;

import demo.domain.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    Page<Order> findAll(Pageable pageable);
    Order findByOrderId(String orderId);

    List<Order> saveOrder(List<Order> orderList);
//    Order saveOrder(Order order);

    void deleteAll();
    void deleteByOrderId(String orderId);

    void setIsPaidByOrderId(String orderId, boolean paid);
    void setIsCancelledByOrderId(String orderId, boolean cancelled);

}
