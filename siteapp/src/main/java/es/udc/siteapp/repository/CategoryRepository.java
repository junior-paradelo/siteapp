package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.siteapp.model.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

}
