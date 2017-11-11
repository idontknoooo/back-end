package demo.service;

import demo.domain.Dish;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface DishService {

    Page<Dish> findAll(Pageable pageable);
    Dish findByDishId(String dishId);
    Page<Dish> findByDishName(String dishName, Pageable pageable);

    List<Dish> saveDish(List<Dish> dish);
    Dish saveDish(Dish dish);

    void deleteAll();
    void deleteByDishId(String dishId);
}
