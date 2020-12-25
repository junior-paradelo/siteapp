package es.udc.siteapp.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.service.SiteService;
import es.udc.siteapp.service.dto.SiteDTO;

@RestController
@RequestMapping("/api/v1/")
public class SiteController {

	@Autowired
	SiteService siteService;

	@GetMapping("sites")
	public List<SiteDTO> findAllSites() {
		return siteService.findAll();
	}

	@GetMapping("sites/{id}")
	public SiteDTO getSiteById(@PathVariable(value = "id") Long id) {
		return siteService.getSiteById(id);
	}

	@GetMapping("sites/name={name}")
	public SiteDTO getSiteByName(@PathVariable(value = "name") String name) {
		return siteService.getSiteByName(name);
	}

	@PostMapping("sites")
	public SiteDTO createSite(@RequestBody SiteDTO site) {
		Site registerSite = siteService.registerSite(site.getName(), site.getProvince(), site.getTownHall());
		return new SiteDTO(registerSite);
	}

	@PutMapping("sites/{id}")
	public SiteDTO updateSite(@PathVariable(value = "id") Long id, @RequestBody SiteDTO siteDto) {
		return siteService.updateSite(siteDto);
	}

	@DeleteMapping("sites/{id}")
	public Map<String, Boolean> deleteSite(@PathVariable(value = "id") Long id) {
		siteService.deleteSiteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

}
