package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.SiteImage;

public interface SiteImageRepository extends JpaRepository<SiteImage, Long> {
	SiteImage findSiteImageByImageName(String imageName);

	List<SiteImage> findBySite(Site site);
}
