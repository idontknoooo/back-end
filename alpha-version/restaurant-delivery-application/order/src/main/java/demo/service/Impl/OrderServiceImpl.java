package demo.service.Impl;

import com.netflix.discovery.converters.Auto;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import demo.domain.Dish;
import demo.domain.Order;
import demo.domain.OrderRepository;
import demo.domain.Restaurant;
import demo.service.OrderService;
import demo.service.RestaurantService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


import java.text.SimpleDateFormat;
import java.util.*;

@Service
@Slf4j
public class OrderServiceImpl implements OrderService {

    @Autowired
    private RestTemplate restTemplate;

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

    @Override
    public void setIsPaidByOrderId(String orderId, boolean paid){
        this.orderRepository.findByOrderId(orderId).setPaid(paid);
    }

    @Override
    public void setIsCancelledByOrderId(String orderId, boolean cancelled){
        this.orderRepository.findByOrderId(orderId).setCancelled(cancelled);
    }

    @HystrixCommand(fallbackMethod = "formOrderFallback")
    @Override
    public Order formOrder(){
        String restaurantUrl = "http://restaurant";

        Restaurant restaurant = restTemplate.getForObject(restaurantUrl+"/api/randomRestaurant", Restaurant.class);
        System.out.println("Checkpoint1");
        Order newOrder = new Order();
        Random random = new Random();

        System.out.println("Checkpoint2");
//        newOrder.setEstimatedDeliveryTime(random.nextInt(56)+5);
        newOrder.setOrderId(Integer.toString(random.nextInt(10)));

        System.out.println("Checkpoint2.1");
        newOrder.setOrderNote("Blablabla");
        newOrder.setOrderTimestamp(new Date());

        System.out.println("Checkpoint2.2");
//        List<Dish> dishes;
        System.out.println(restaurant.toString());
        System.out.println("Checkpoint2.3");
//        dishes = restaurant.getMenu().getDishes();
        List<Dish> dishes = new ArrayList<>(restaurant.getMenu().getDishes());
        System.out.println("Checkpoint3");
        // Random Pick 3 Dishes
        List<Dish> newDishes = new ArrayList<>();
        for(int i = 0; i < 3; ++i){
            Dish tmpDish = dishes.get(random.nextInt(dishes.size()));
            newDishes.add(tmpDish);
        }
        newOrder.setOrderDetail(newDishes);
        double totalBalance = 0;
        for(Dish dish : newDishes){
            totalBalance += dish.getDishPrice();
        }

        System.out.println("Checkpoint4");
        newOrder.setTotalBalance(totalBalance);
        newOrder.setCancelled(false);
        newOrder.setPaid(false);
        newOrder.setPaymentId("p"+Integer.toString(random.nextInt(10)));
        return newOrder;
    }
    @SuppressWarnings("unused")
    public Order formOrderFallback(){
        log.error("Fail to form an order!");
        return null;
    }

    @HystrixCommand(fallbackMethod = "confirmOrderFallback")
    @Override
    public Order confirmOrder(Order order){
        System.out.println("Checkpoint1");
        String orderReceiver = "http://order-receiver";

        System.out.println("Checkpoint2");
        String savedOrder = saveOrder(order).toString();
        System.out.println("Checkpoint3");
        restTemplate.postForLocation(orderReceiver + "/api/orderMq", savedOrder);
        System.out.println("Checkpoint4");
        log.info(String.format("Order confirmed: %s", savedOrder));

        return order;
    }

    @SuppressWarnings("unused")
    public Order confirmOrderFallback(Order order){
        log.error("Fail to confirm this order!");
        return null;
    }
}
