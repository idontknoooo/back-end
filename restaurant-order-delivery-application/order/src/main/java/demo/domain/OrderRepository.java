package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "order")
public interface OrderRepository extends PagingAndSortingRepository<Order, String> {

    Order findByOrderId(@Param("orderId") String orderId);
    Page<Order> findByRestaurantId(@Param("restaurantId") String restaurantId, Pageable pageable);
}
