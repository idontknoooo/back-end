package demo.service.Impl;

import demo.domain.Dish;
import demo.domain.DishRepository;
import demo.service.DishService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DishServiceImpl implements DishService {
    private DishRepository dishRepository;

    @Autowired
    public DishServiceImpl(DishRepository dishRepository)
    {
        this.dishRepository = dishRepository;
    }

    @Override
    public Page<Dish> findAll(Pageable pageable)
    {
        return this.dishRepository.findAll(pageable);
    }

    @Override
    public Dish findByDishId(String dishId)
    {
        return this.dishRepository.findByDishId(dishId);
    }

    @Override
    public Page<Dish> findByDishName(String dishName, Pageable pageable) {
        return this.dishRepository.findByDishName(dishName, pageable);
    }

    @Override
    public List<Dish> saveDish(List<Dish> dish)
    {
        return (List<Dish>) this.dishRepository.save(dish);
    }

    @Override
    public Dish saveDish(Dish dish)
    {
        return this.dishRepository.save(dish);
    }

    @Override
    public void deleteAll()
    {
        this.dishRepository.deleteAll();
    }

    @Override
    public void deleteByDishId(String dishId)
    {
        this.dishRepository.delete(dishId);
    }
}
