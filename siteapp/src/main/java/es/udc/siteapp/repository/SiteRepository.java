package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.siteapp.model.Site;

public interface SiteRepository extends JpaRepository<Site, Long> {

//	Site findSiteById(Long id);
//	Site findSiteByName(String name);
}
