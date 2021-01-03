package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.siteapp.model.Authority;
import es.udc.siteapp.model.AuthorityName;

@Repository
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
	Authority findAuthorityByName(AuthorityName name);
}
