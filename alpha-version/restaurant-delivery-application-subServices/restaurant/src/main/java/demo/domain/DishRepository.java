package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "dish")
public interface DishRepository extends PagingAndSortingRepository<Dish, String>{

    Page<Dish> findByDishName(@Param("dishName") String dishName, Pageable pageable);

    Dish findByDishId(@Param("dishId") String dishId);
}
