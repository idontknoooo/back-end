package demo.service.impl;

import demo.domain.MenuRepository;
import demo.domain.Menu;
import demo.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MenuServiceImpl implements MenuService {

    private MenuRepository menuRepository;

    @Autowired
    public MenuServiceImpl(MenuRepository menuRepository)
    {
        this.menuRepository = menuRepository;
    }

    @Override
    public Page<Menu> findAll(Pageable pageable)
    {
        return this.menuRepository.findAll(pageable);
    }

    @Override
    public Menu findByMenuId(String menuId)
    {
        return this.menuRepository.findByMenuId(menuId);
    }

    @Override
    public List<Menu> saveMenu(List<Menu> menu)
    {
        return (List<Menu>) this.menuRepository.save(menu);
    }

    @Override
    public Menu saveMenu(Menu menu)
    {
        return this.menuRepository.save(menu);
    }

    @Override
    public void deleteAll()
    {
        this.menuRepository.deleteAll();
    }

    @Override
    public void deleteByMenuId(String menuId)
    {
        this.menuRepository.delete(menuId);
    }
}
