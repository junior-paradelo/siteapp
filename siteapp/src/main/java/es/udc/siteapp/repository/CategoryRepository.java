package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Theme;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

	List<Category> findByTheme(Theme theme);

}
