package demo.domain;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface OrderRepository extends PagingAndSortingRepository<Order, String>{

    Order findByOrderId(@Param("orderId") String orderId);
}
