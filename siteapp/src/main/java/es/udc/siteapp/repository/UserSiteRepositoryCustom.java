package es.udc.siteapp.repository;

import java.util.List;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.User;
import es.udc.siteapp.model.UserSite;
import es.udc.siteapp.model.UserSiteState;

public interface UserSiteRepositoryCustom {

	UserSite findByUserAndSiteId(User user, Site site, UserSiteState state);

	List<UserSite> findBySiteId(Long siteId);

	List<UserSite> findSitesByState(User user, UserSiteState state);

	Double getAVGRate(Long siteId);
}
