package es.udc.siteapp.repository.impl;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.repository.SiteRepositoryCustom;

public class SiteRepositoryCustomImpl implements SiteRepositoryCustom {

	@Autowired
	EntityManager entityManager;

	@Override
	public List<Site> findSiteByKeywordAndCategory(String keyword, ArrayList<Integer> categories, Integer page) {
		Query query = entityManager
				.createQuery("from Site where lower(name) like lower(:keyword) and category_id in (:categories)");
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("categories", categories);
		int pageSize = 8;
		return query.setFirstResult(page * pageSize).setMaxResults(pageSize).getResultList();
	}

	@Override
	public Long countSitesByKeywordAndCategory(String keyword, ArrayList<Integer> categories) {
		Query query = entityManager.createQuery(
				"select count(*) from Site where lower(name) like lower(:keyword) and category_id in (:categories)");
		query.setParameter("keyword", "%" + keyword + "%");
		query.setParameter("categories", categories);
		Long count = (Long) query.getSingleResult();
		return count;
	}

	@Override
	public List<Site> findSiteByCategory(Integer categoryId) {
		Query query = entityManager.createQuery("from Site where category_id = :category order by created_at desc");
		query.setParameter("category", categoryId);
		return query.getResultList();
	}

	@Override
	public Long countSitesByCategory(Integer categoryId) {
		Query query = entityManager.createQuery("select count(*) from Site where category_id = :category");
		query.setParameter("category", categoryId);
		return (Long) query.getSingleResult();
	}

}
