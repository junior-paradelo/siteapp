package es.udc.siteapp.repository;

import java.util.List;

import es.udc.siteapp.model.Site;

public interface SiteRepositoryCustom {
	List<Site> findSiteByKeyword(String keyword);
}
