package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;


// Repo extends from PagingAndSortingRepository contains method like:
// https://docs.spring.io/spring-data/commons/docs/current/api/org/springframework/data/repository/PagingAndSortingRepository.html
// count, delete(entity), deleteAll(entities), deleteAll(), deleteById(Id), existsById(id), findAll(),
// findAllById(id), findById(id), save(entity), saveAll(entities)
// findAll()
// delete()
// deleteAll()
// save()
// Thus, it is necessary to implement other method like:
// findByRestaurantName(String, Pageable)
// findByRestaurantId(String)
@RepositoryRestResource(path = "restaurant")
public interface RestaurantRepository extends PagingAndSortingRepository<Restaurant, String>{

    Page<Restaurant> findByRestaurantName(@Param("restaurantName") String restaurantName, Pageable pageable);
//    List<Restaurant> findByRestaurantName(@Param("restaurantName") String restaurantName);

    Restaurant findByRestaurantId(@Param("restaurantId") String restaurantId);

}
