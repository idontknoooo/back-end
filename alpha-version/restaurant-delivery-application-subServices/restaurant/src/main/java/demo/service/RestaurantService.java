package demo.service;

import demo.domain.Restaurant;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

// Header for Services
// Page can be showed in POSTMAN
public interface RestaurantService {

    Page<Restaurant> findAll(Pageable pageable);
    Restaurant findByRestaurantId(String restaurantId);
    Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable);

//    List<Restaurant> findByRestaurantName(String restaurantName);
    List<Restaurant> saveRestaurant(List<Restaurant> restaurant);
    Restaurant saveRestaurant(Restaurant restaurant);

    void deleteAll();
    void deleteByRestaurantId(String restaurantId);

}
