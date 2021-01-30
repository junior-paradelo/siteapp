package es.udc.siteapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import es.udc.siteapp.service.UserSiteService;
import es.udc.siteapp.service.dto.UserSiteDTO;

@RestController
@RequestMapping("/api/")
public class UserSiteController {

	@Autowired
	UserSiteService userSiteService;

	@GetMapping("/userSite/getSitesByUserId")
	public List<UserSiteDTO> getSitesByUserId(@RequestParam("userId") Long userId,
			@RequestParam("state") String state) {
		return userSiteService.getSitesByUserId(userId, state);
	}

	@PostMapping("/userSite/saveState")
	public void saveState(@RequestParam("userId") Long userId, @RequestParam("siteId") Long siteId,
			@RequestParam("state") String state, @RequestParam(value = "rate", required = false) Integer rate) {
		userSiteService.saveState(userId, siteId, state, rate);
	}

	@DeleteMapping("/userSite/delete")
	public Map<String, Boolean> deleteFavorite(@RequestParam("userId") Long userId, @RequestParam("siteId") Long siteId,
			@RequestParam("state") String state) {
		userSiteService.deleteElement(userId, siteId, state);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("/userSite/findByUserAndSiteId")
	public UserSiteDTO findByUserAndSiteId(@RequestParam("userId") Long userId, @RequestParam("siteId") Long siteId,
			@RequestParam("state") String state) {
		return userSiteService.findByUserAndSiteId(userId, siteId, state);
	}

	@GetMapping("/userSite/getAVGRate")
	public Double getAVGRate(@RequestParam("siteId") Long siteId) {
		return userSiteService.getAVGRate(siteId);
	}
}
