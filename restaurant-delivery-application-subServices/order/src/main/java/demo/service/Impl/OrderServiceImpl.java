package demo.service.Impl;

import demo.domain.Order;
import demo.domain.OrderRepository;
import demo.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

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
    public Order findByOrderId(String orderId){
        return this.orderRepository.findByOrderId(orderId);
    }

    @Override
    public List<Order> saveOrder(List<Order> orderList){
        return (List<Order>) this.orderRepository.save(orderList);
    }
//
//    @Override
//    public Order saveOrder(Order order){
//        return this.orderRepository.save(order);
//    }

    @Override
    public void deleteAll(){
        this.orderRepository.deleteAll();
    }

    @Override
    public void deleteByOrderId(String orderId){
        this.orderRepository.delete(orderId);
    }

    @Override
    public void setIsPaidByOrderId(String orderId, boolean paid){
        this.orderRepository.findByOrderId(orderId).setPaid(paid);
    }

    @Override
    public void setIsCancelledByOrderId(String orderId, boolean cancelled){
        this.orderRepository.findByOrderId(orderId).setCancelled(cancelled);
    }
}
