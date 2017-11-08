package demo.domain;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;

@RepositoryRestResource(path = "menu")
public interface MenuRepository extends PagingAndSortingRepository<Menu, String>{

//    List<Menu> findByMenuName(@Param("menuName") String menuName);

    Menu findByMenuId(@Param("menuId") String menuId);
}
