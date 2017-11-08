package demo.service.impl;

import demo.domain.Restaurant;
import demo.domain.RestaurantRepository;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

// Using [Repo entends from PagingAndSortingRepository] when implements RestaurantService
@Service
public class RestaurantServiceImpl implements RestaurantService {

    private RestaurantRepository restaurantRepository;

    @Autowired
    public RestaurantServiceImpl(RestaurantRepository restaurantRepository)
    {
        this.restaurantRepository = restaurantRepository;
    }

    @Override
    public Page<Restaurant> findAll(Pageable pageable)
    {
        return this.restaurantRepository.findAll(pageable);
    }

    @Override
    public Restaurant findByRestaurantId(String restaurantId)
    {
        return this.restaurantRepository.findByRestaurantId(restaurantId);
    }

    @Override
    public Page<Restaurant> findByRestaurantName(String restaurantName, Pageable pageable)
    {
        return this.restaurantRepository.findByRestaurantName(restaurantName, pageable);
    }

//    @Override
//    public List<Restaurant> findByRestaurantName(String restaurantName)
//    {
//        return this.restaurantRepository.findByRestaurantName(restaurantName);
//    }

    @Override
    public List<Restaurant> saveRestaurant(List<Restaurant> restaurant)
    {
        return (List<Restaurant>) this.restaurantRepository.save(restaurant);
    }

    @Override
    public Restaurant saveRestaurant(Restaurant restaurant)
    {
        return this.restaurantRepository.save(restaurant);
    }

    @Override
    public void deleteAll()
    {
        this.restaurantRepository.deleteAll();
    }

    @Override
    public void deleteByRestaurantId(String restaurantId)
    {
        this.restaurantRepository.delete(restaurantId);
    }
}
