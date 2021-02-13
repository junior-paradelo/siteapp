package es.udc.siteapp.repository;

import java.util.ArrayList;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

	List<Site> findTop20ByCategoryOrderByCreatedAtDesc(Category category);

	List<Site> findTop20ByOrderByCreatedAtDesc();

	@Modifying
	@Query("from Site where category_id = :categoryId order by created_at desc")
	List<Site> findByCategory(@Param("categoryId") Long categoryId);

	@Query("from Site where lower(name) like lower(concat('%',:keyword,'%')) and category_id in (:categories) order by name asc")
	List<Site> findSiteByKeywordAndCategory(@Param("keyword") String keyword,
			@Param("categories") ArrayList<Integer> categories);

	@Query("from Site where lower(name) like lower(concat('%',:keyword,'%')) order by name asc")
	List<Site> findSiteByKeyword(@Param("keyword") String keyword);

	List<Site> findByNameContainingIgnoreCase(String siteName);
}
