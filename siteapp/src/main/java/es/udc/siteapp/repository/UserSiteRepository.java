package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import es.udc.siteapp.model.UserSite;

public interface UserSiteRepository extends JpaRepository<UserSite, Long>, UserSiteRepositoryCustom {

}
