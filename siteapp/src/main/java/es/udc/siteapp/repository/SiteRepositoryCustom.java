package es.udc.siteapp.repository;

import java.util.ArrayList;
import java.util.List;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;

public interface SiteRepositoryCustom {

	List<Site> findSiteByKeywordAndCategory(String keyword, ArrayList<Integer> categories);

	List<Site> findSiteByCategory(Category category);
}
