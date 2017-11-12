package demo;

import demo.domain.Order;
import demo.domain.OrderRepository;
import demo.service.OrderService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

//@SpringApplicationConfiguration(classes = OrderApplication.class)
//@RunWith(SpringJUnit4ClassRunner.class)
public class OrderServiceTest {

//    @Autowired
//    private Order order;
//
//    @Autowired
//    private OrderService orderService;
//
//    @Before
//    public void setupMock(){
////        order = mock(Order.class);
//
//
//        order.setPaid(false);
//        order.setCancelled(false);
////        orderRepository = mock(OrderRepository.class);
////        when(orderClass.getTotalPrice()).thenReturn(1.0);
////        when(orderClass.isPaid()).thenReturn(false);
////        when(orderClass.isCancelled()).thenReturn(false);
//
//    }
//
//    @Test
//    public void whenPreCheckOrder_expectTrue(){
//
////        when(order.getTotalPrice()).thenReturn(2.0);
////        when(order.isPaid()).thenReturn(false);
////        when(order.isCancelled()).thenReturn(false);
//
////        assertThat(order.getTotalPrice()).isEqualTo(2.0);
//        assertThat(this.orderService.preCheckOrder(order)).isEqualTo(true);
//    }
}
