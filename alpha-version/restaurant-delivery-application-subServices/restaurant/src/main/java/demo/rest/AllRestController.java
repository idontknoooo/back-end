package demo.rest;

import demo.domain.Dish;
import demo.domain.Menu;
import demo.domain.Restaurant;
import demo.service.DishService;
import demo.service.MenuService;
import demo.service.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

// Call methods in RestaurantService to implement AllRestController
@RestController
public class AllRestController {

    /*
        RESTCONTROLLER FOR RESTAURANT SERVICE
     */
//    private RestaurantRepository restaurantRepository;
    private RestaurantService restaurantService;
    private MenuService menuService;
    private DishService dishService;

    @Autowired
    public AllRestController(RestaurantService restaurantService, MenuService menuService, DishService dishService) //, RestaurantRepository restaurantRepository)
    {
//        this.restaurantRepository = restaurantRepository;
        this.restaurantService = restaurantService;
        this.menuService = menuService;
        this.dishService = dishService;
    }


    @SuppressWarnings("unused")
    @RequestMapping(value = "restaurant/{restaurantName}", method = RequestMethod.GET)
    public Page<Restaurant> findByRestaurantName(@PathVariable(value = "restaurantName") String restaurantName,
                                             @RequestParam(value = "page") int pageNumber,
                                             @RequestParam(value = "size", required = false) Integer size){
        if(restaurantName != null){
            return this.restaurantService.findByRestaurantName(restaurantName, new PageRequest(pageNumber, size == null ? 3 : size));
        }
        else{
            return this.restaurantService.findAll(new PageRequest(pageNumber, size == null ? 3 : size));
        }
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.GET)
    public Page<Restaurant> findAllRestaurant(Pageable pageable){
        return this.restaurantService.findAll(pageable);
    }

    @RequestMapping(value = "restaurant/rid/{restaurantId}", method = RequestMethod.GET)
    public Restaurant findByRestaurantId(@PathVariable(value = "restaurantId") String restaurantId){
        return this.restaurantService.findByRestaurantId(restaurantId);
    }

    @RequestMapping(value = "restaurant", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Restaurant> saveRestaurant(@RequestBody List<Restaurant> restaurants){
        return this.restaurantService.saveRestaurant(restaurants);
    }

//    @RequestMapping(value = "restaurant", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    Restaurant saveRestaurant(@RequestBody Restaurant restaurants){
//        return this.restaurantService.saveRestaurant(restaurants);
//    }

    @RequestMapping(value = "restaurant", method = RequestMethod.DELETE)
    public void deleteAllRestaurant(){
        this.restaurantService.deleteAll();
    }

    @RequestMapping(value = "restaurant/{restaurantId}", method = RequestMethod.DELETE)
    public void deleteByRestaurantId(@PathVariable(value = "restaurantId") String restaurantId){
        this.restaurantService.deleteByRestaurantId(restaurantId);
    }

    /*
        RESTCONTROLLER FOR MENU SERVICE
     */
    @RequestMapping(value = "menu", method = RequestMethod.GET)
    public Page<Menu> findAllMenu(Pageable pageable)
    {
        return this.menuService.findAll(pageable);
    }

    @RequestMapping(value = "menu/{menuId}", method = RequestMethod.GET)
    public Menu findByMenuId(@PathVariable("menuId") String menuId)
    {
        return this.menuService.findByMenuId(menuId);
    }

    @RequestMapping(value = "menu", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Menu> saveMenu(@RequestBody List<Menu> menus){
        return this.menuService.saveMenu(menus);
    }

//    @RequestMapping(value = "menu", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    Menu saveMenu(@RequestBody Menu menu){
//        return this.menuService.saveMenu(menu);
//    }

    @RequestMapping(value = "menu", method = RequestMethod.DELETE)
    public void deleteAllMenu(){
        this.menuService.deleteAll();
    }

    @RequestMapping(value = "menu/{menuId}", method = RequestMethod.DELETE)
    public void deleteByMenuId(@PathVariable("menuId") String menuId){
        this.menuService.deleteByMenuId(menuId);
    }

    /*
        RESTCONTROLLER FOR DISH SERVICE
     */
    @RequestMapping(value = "dish/{dishName}", method = RequestMethod.GET)
    public Page<Dish> findByDishName(@PathVariable("dishName") String dishName,
                              @RequestParam(value = "page") int pageNumber,
                              @RequestParam(value = "size", required = false) Integer size){
        if(dishName != null){
            return this.dishService.findByDishName(dishName, new PageRequest(pageNumber, size == null ? 3 : size));
        }
        else{
            return this.dishService.findAll(new PageRequest(pageNumber, size == null ? 3 : size));
        }
    }

    @RequestMapping(value = "dish", method = RequestMethod.GET)
    public Page<Dish> findAllDish(Pageable pageable){
        return this.dishService.findAll(pageable);
    }

    @RequestMapping(value = "dish/did/{dishId}", method = RequestMethod.GET)
    public Dish findByDishId(@PathVariable("dishId") String dishId){
        return this.dishService.findByDishId(dishId);
    }

    @RequestMapping(value = "dish", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public List<Dish> saveDish(@RequestBody List<Dish> dishes){
        return this.dishService.saveDish(dishes);
    }

//    @RequestMapping(value = "dish", method = RequestMethod.POST)
//    @ResponseStatus(HttpStatus.CREATED)
//    Dish saveDish(@RequestBody Dish dishes){
//        return this.dishService.saveDish(dishes);
//    }

    @RequestMapping(value = "dish", method = RequestMethod.DELETE)
    public void deleteAllDish(){
        this.dishService.deleteAll();
    }

    @RequestMapping(value = "dish/{dishId}", method = RequestMethod.DELETE)
    public void deleteByDishId(@PathVariable("dishId") String dishId){
        this.dishService.deleteByDishId(dishId);
    }
}
