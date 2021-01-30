package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.User;
import es.udc.siteapp.model.UserSite;
import es.udc.siteapp.model.UserSiteState;
import es.udc.siteapp.repository.SiteRepository;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.repository.UserSiteRepository;
import es.udc.siteapp.service.dto.UserSiteDTO;

@Service
public class UserSiteService {

	@Autowired
	private UserSiteRepository userSiteRepository;
	@Autowired
	private UserRepository userRepository;
	@Autowired
	private SiteRepository siteRepository;

	public List<UserSiteDTO> getSitesByUserId(Long userId, String state) {
		UserSiteState userSiteState = UserSiteState.valueOf(state);
		User user = userRepository.getOne(userId);
		List<UserSite> list = userSiteRepository.findSitesByState(user, userSiteState);
		List<UserSiteDTO> userSiteDtoList = new LinkedList<>();
		for (UserSite us : list) {
			UserSiteDTO userSiteDTO = new UserSiteDTO(us);
			userSiteDtoList.add(userSiteDTO);
		}
		return userSiteDtoList;
	}

	public void saveState(Long userId, Long siteId, String state) {
		User user = userRepository.getOne(userId);
		Site site = siteRepository.getOne(siteId);
		UserSiteState userSiteState = UserSiteState.valueOf(state);
		UserSite userSite = userSiteRepository.findByUserAndSiteId(user, site, userSiteState);
		if (userSite == null) {
			userSite = new UserSite(site, user, userSiteState);
			userSiteRepository.save(userSite);
		}
	}

	public UserSiteDTO findByUserAndSiteId(Long userId, Long siteId, String state) {
		User user = userRepository.getOne(userId);
		Site site = siteRepository.getOne(siteId);
		UserSiteState userSiteState = UserSiteState.valueOf(state);
		UserSite userSite = userSiteRepository.findByUserAndSiteId(user, site, userSiteState);
		UserSiteDTO userSiteDTO = null;
		if (userSite != null) {
			userSiteDTO = new UserSiteDTO(userSite);
			return userSiteDTO;
		}
		return userSiteDTO;
	}

	public void deleteElement(Long userId, Long siteId, String state) {
		User user = userRepository.getOne(userId);
		Site site = siteRepository.getOne(siteId);
		UserSiteState userSiteState = UserSiteState.valueOf(state);
		UserSite userSite = userSiteRepository.findByUserAndSiteId(user, site, userSiteState);
		userSiteRepository.delete(userSite);
	}
}
