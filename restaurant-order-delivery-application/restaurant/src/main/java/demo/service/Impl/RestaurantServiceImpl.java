package demo.service.Impl;

import demo.domain.Dish;
import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RestaurantServiceImpl implements RestaurantService{

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository){
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Page<Restaurant> findAll(Pageable pageable){
        return this.restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant findByRestaurantId(String restaurantId){
        return this.restaurantRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable){
        return this.restaurantRepository.findByRestaurantName(restaurantName, pageable);
    }

    @Override
    public List<Restaurant> saveRestaurant(List<Restaurant> restaurants){
        return (List<Restaurant>) this.restaurantRepository.save(restaurants);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant){
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteAll(){
        this.restaurantRepository.deleteAll();
    }

    @Override
    public void deleteByRestaurantId(String restaurantId){
        this.restaurantRepository.delete(restaurantId);
    }


}
