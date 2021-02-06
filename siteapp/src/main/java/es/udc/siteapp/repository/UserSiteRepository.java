package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.siteapp.model.UserSite;
import es.udc.siteapp.model.UserSiteState;

public interface UserSiteRepository extends JpaRepository<UserSite, Long> {

	@Modifying
	@Query("delete from UserSite where site_id = :siteId")
	Long deleteBySite(@Param("siteId") Long siteId);

	@Modifying
	@Query("delete from UserSite where user_id = :userId")
	Long deleteByUser(@Param("userId") Long userId);

	@Query("from UserSite where user_id = :userId and site_id = :siteId and state = :state")
	UserSite findByUserAndSiteId(@Param("userId") Long userId, @Param("siteId") Long siteId,
			@Param("state") UserSiteState state);

	@Modifying
	@Query("from UserSite where site_id = :siteId")
	List<UserSite> findBySite(@Param("siteId") Long siteId);

	@Modifying
	@Query("from UserSite where user_id = :userId and state = :state order by rate desc nulls last")
	List<UserSite> findByState(@Param("userId") Long userId, @Param("state") UserSiteState state);

	@Query("select avg(rate) from UserSite where site_id = :siteId")
	Double getAVGRate(@Param("siteId") Long siteId);
}
