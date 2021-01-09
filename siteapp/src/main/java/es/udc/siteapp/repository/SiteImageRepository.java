package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.siteapp.model.SiteImage;

public interface SiteImageRepository extends JpaRepository<SiteImage, Long> {
	public SiteImage findSiteImageByImageName(String imageName);
}
