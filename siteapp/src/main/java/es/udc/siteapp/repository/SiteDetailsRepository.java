package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.siteapp.model.SiteDetails;

public interface SiteDetailsRepository extends JpaRepository<SiteDetails, Long> {

}
