package demo.service;

import demo.domain.Dish;
import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface RestaurantService {

    Page<Restaurant> findAll(Pageable pageable);
    Restaurant findByRestaurantId(String restaurantId);
    Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable);

    List<Restaurant> saveRestaurant(List<Restaurant> restaurants);
    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteAll();
    void deleteByRestaurantId(String restaurantId);

}
