package es.udc.siteapp.repository.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.repository.SiteRepositoryCustom;

public class SiteRepositoryCustomImpl implements SiteRepositoryCustom {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Site> findSiteByKeyword(String keyword) {
		Query query = entityManager.createQuery("from Site where lower(name) like lower(:keyword)");
		query.setParameter("keyword", "%" + keyword + "%");
		return query.getResultList();
	}

	@Override
	public List<Site> findSiteByCategory(Category category) {
		Query query = entityManager.createQuery("from Site where category_id = :category");
		query.setParameter("category", category.getId());
		return query.getResultList();
	}

}
