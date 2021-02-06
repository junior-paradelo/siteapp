package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.siteapp.model.SiteImage;

public interface SiteImageRepository extends JpaRepository<SiteImage, Long> {

	@Modifying
	@Query("from SiteImage where site_id = :siteId")
	List<SiteImage> findBySite(@Param("siteId") Long siteId);
}
