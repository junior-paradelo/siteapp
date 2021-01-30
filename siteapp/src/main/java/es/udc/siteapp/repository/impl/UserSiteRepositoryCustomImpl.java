package es.udc.siteapp.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.User;
import es.udc.siteapp.model.UserSite;
import es.udc.siteapp.model.UserSiteState;
import es.udc.siteapp.repository.UserSiteRepositoryCustom;

public class UserSiteRepositoryCustomImpl implements UserSiteRepositoryCustom {

	@Autowired
	EntityManager entityManager;

	@Override
	public UserSite findByUserAndSiteId(User user, Site site, UserSiteState state) {
		Query query = entityManager.createQuery("from UserSite where user = :user and site = :site and state = :state");
		query.setParameter("user", user);
		query.setParameter("site", site);
		query.setParameter("state", state);
		return (UserSite) query.getSingleResult();
	}

	@Override
	public List<UserSite> findBySiteId(Long siteId) {
		Query query = entityManager.createQuery("from UserSite where site = :siteId");
		query.setParameter("siteId", siteId);
		return query.getResultList();
	}

	@Override
	public List<UserSite> findSitesByState(User user, UserSiteState state) {
		Query query = entityManager
				.createQuery("from UserSite where user = :userId and state = :state order by rate desc nulls last");
		query.setParameter("userId", user);
		query.setParameter("state", state);
		return query.getResultList();
	}

	@Override
	public Double getAVGRate(Long siteId) {
		Query query = entityManager.createQuery("select avg(rate) from Site where site = :siteId");
		query.setParameter("siteId", siteId);
		return (Double) query.getSingleResult();
	}

}
