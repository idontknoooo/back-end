package demo.service;

import demo.domain.Menu;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface MenuService {

    Page<Menu> findAll(Pageable pageable);
    Menu findByMenuId(String menuId);
    List<Menu> saveMenu(List<Menu> menu);

    Menu saveMenu(Menu menu);
    void deleteByMenuId(String menuId);
    void deleteAll();

}
