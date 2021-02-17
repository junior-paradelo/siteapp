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
		List<UserSite> list = userSiteRepository.findByState(userId, UserSiteState.valueOf(state));
		List<UserSiteDTO> userSiteDtoList = new LinkedList<>();
		for (UserSite us : list) {
			UserSiteDTO userSiteDTO = new UserSiteDTO(us);
			userSiteDtoList.add(userSiteDTO);
		}
		return userSiteDtoList;
	}

	public void saveState(Long userId, Long siteId, String state, Integer rate) {
		UserSiteState userSiteState = UserSiteState.valueOf(state);
		UserSite userSite = userSiteRepository.findByUserAndSiteId(userId, siteId, UserSiteState.valueOf(state));
		if (userSite == null) {
			User user = userRepository.getOne(userId);
			Site site = siteRepository.getOne(siteId);
			userSite = new UserSite(site, user, rate, userSiteState);
		} else {
			userSite.setRate(rate);
		}
		userSiteRepository.save(userSite);
	}

	public UserSiteDTO findByUserAndSiteId(Long userId, Long siteId, String state) {
		UserSite userSite = userSiteRepository.findByUserAndSiteId(userId, siteId, UserSiteState.valueOf(state));
		UserSiteDTO userSiteDTO = null;
		if (userSite != null) {
			userSiteDTO = new UserSiteDTO(userSite);
			return userSiteDTO;
		}
		return userSiteDTO;
	}

	public void deleteElement(Long userId, Long siteId, String state) {
		UserSite userSite = userSiteRepository.findByUserAndSiteId(userId, siteId, UserSiteState.valueOf(state));
		userSiteRepository.delete(userSite);
	}

	public Double getAVGRate(Long siteId) {
		return userSiteRepository.getAVGRate(siteId);
	}
}
