package demo;


import demo.domain.Address;
import demo.domain.Dish;
import demo.domain.Restaurant;
import demo.rest.RestaurantRestController;
import demo.service.DishService;
import demo.service.RestaurantService;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import static org.assertj.core.api.Assertions.assertThat;

public class RestaurantRestControllerTest {

    private RestaurantService restaurantService;
    private DishService dishService;

    private RestaurantRestController restaurantRestController;
    private List<Restaurant> inputList;

    @Before
    public void setupMock(){

        restaurantService = mock(RestaurantService.class);
        dishService = mock(DishService.class);

        restaurantRestController = new RestaurantRestController(restaurantService, dishService);
    }

    // TEST: saveRestaurant in RestaurantRestController
    @Test
    public void whenSaveRestaurant_expectSuccess(){
        List<Restaurant> expectedListAfterSaving = new ArrayList<>();
        expectedListAfterSaving.add(fakeRestaurant("0", "Chinese No_1"));
        expectedListAfterSaving.add(fakeRestaurant("1", "Chinese No_2"));
        expectedListAfterSaving.add(fakeRestaurant("2", "Chinese No_3"));

        when(restaurantService.saveRestaurant(inputList)).thenReturn(expectedListAfterSaving);

        assertThat(restaurantRestController.saveRestaurant(inputList).size()).isEqualTo(3);
        assertThat(restaurantRestController.saveRestaurant(inputList).get(0).getRestaurantName()).isEqualTo("Chinese No_1");
        assertThat(restaurantRestController.saveRestaurant(inputList).get(0).getMenu()).isEqualTo(new ArrayList<>());
    }

    private Restaurant fakeRestaurant(String id, String name) {
        Address address = new Address();
        List<Dish> menu = new ArrayList<>();
        Restaurant restaurant = new Restaurant(id, name, address, menu);
        return restaurant;
    }
}
