package es.udc.siteapp.repository;

import java.util.ArrayList;
import java.util.List;

import es.udc.siteapp.model.Site;

public interface SiteRepositoryCustom {

	List<Site> findSiteByKeywordAndCategory(String keyword, ArrayList<Integer> categories, Integer page);

	List<Site> findSiteByCategory(Integer categoryId);

	Long countSitesByKeywordAndCategory(String keyword, ArrayList<Integer> categories);

	Long countSitesByCategory(Integer categoryId);
}
