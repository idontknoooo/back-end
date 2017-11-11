package demo.rest;

import demo.domain.Dish;
import demo.domain.Restaurant;
import demo.service.DishService;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RestaurantRestController {

    private RestaurantService restaurantService;
    private DishService dishService;

    @Autowired
    public RestaurantRestController(RestaurantService restaurantService, DishService dishService){
        this.restaurantService = restaurantService;
        this.dishService = dishService;
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Restaurant> saveRestaurant(@RequestBody List<Restaurant> restaurants){
        return this.restaurantService.saveRestaurant(restaurants);
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.GET)
    public Page<Restaurant> findAllRestaurants(Pageable pageable){
        return this.restaurantService.findAll(pageable);
    }

    @RequestMapping(value = "restaurant/id/{restaurantId}", method = RequestMethod.GET)
    public Restaurant findByRestaurantId(@PathVariable(value = "restaurantId") String restaurantId){
        return this.restaurantService.findByRestaurantId(restaurantId);

    }

    @RequestMapping(value = "restaurant/name/{restaurantName}", method = RequestMethod.GET)
    public Page<Restaurant> findByRestaurantName(@PathVariable(value = "restaurantName") String restaurantName, Pageable pageable){
        return this.restaurantService.findByRestaurantName(restaurantName, pageable);
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.DELETE)
    public void deleteAllRestaurants(){
        this.restaurantService.deleteAll();
    }

//    @RequestMapping(value = "dish", method = RequestMethod.GET)
//    public Page<Dish> findAllDishes(Pageable pageable){
//        return this.dishService.findAll(pageable);
//    }
//
//    @RequestMapping(value = "dish", method = RequestMethod.DELETE)
//    public void deleteAllDishes(){
//        this.dishService.deleteAll();
//    }
}
