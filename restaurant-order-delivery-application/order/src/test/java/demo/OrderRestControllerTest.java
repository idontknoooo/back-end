//package demo;
//
//import demo.domain.Address;
//import demo.domain.Dish;
//import demo.domain.Order;
//import demo.domain.OrderRepository;
//import demo.rest.OrderRestController;
//import demo.service.OrderService;
//import org.junit.Before;
//import org.junit.Test;
//import org.springframework.messaging.MessageChannel;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import static org.assertj.core.api.Assertions.assertThat;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class OrderRestControllerTest {
//    private OrderService orderService;
//    private OrderRestController orderRestController;
//    private Order order;
//
//    @Before
//    public void setupMock(){
//
//        order = mock(Order.class);
//        orderService = mock(OrderService.class);
//        orderRestController = new OrderRestController();
//
//        order.setOrderId("1");
//        order.setPaid(false);
//
//    }
//
//    @Test
//    public void whenSaveOrder_expectSuccess(){
//
//        order = fakeRestaurant("0","2");
//        when(orderService.preCheckOrder(order)).thenReturn(true);
//        when(orderService.saveOrder(order)).thenReturn(order);
////        System.out.print(order.toString());
//        System.out.print(orderRestController.saveOrder(order).toString());
//
////        assertThat(orderRestController.saveOrder(order)).isEqualTo(null);
//    }
//
//
//    private Order fakeRestaurant(String id, String rid) {
//        Address address = new Address();
//        List<Dish> menu = new ArrayList<>();
//        Order order = new Order(id, rid, menu, "", address);
//
//        return order;
//    }
//}
