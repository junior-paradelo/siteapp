package es.udc.siteapp.controller;

import java.util.ArrayList;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.service.SiteService;
import es.udc.siteapp.service.dto.SiteDTO;

@RestController
@RequestMapping("/api/")
public class SiteController {

	@Autowired
	SiteService siteService;

	@GetMapping("sites")
	public List<SiteDTO> findAllSites() {
		return siteService.findAll();
	}

	@GetMapping("sites/count")
	public Integer count() {
		return siteService.countSites();
	}

	@GetMapping("sites/pagination")
	public List<SiteDTO> findLastSites(@RequestParam("page") Integer page) {
		return siteService.findLastSites(page);
	}

	@GetMapping("sites/filter")
	public List<SiteDTO> findSiteByKeywordAndCategory(@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "categories") ArrayList<Integer> categories,
			@RequestParam(value = "page") Integer page) {
		return siteService.findSiteByKeywordAndCategory(keyword, categories, page);
	}

	@GetMapping("sites/filter/pagination")
	public Long countSiteByKeywordAndCategory(@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "categories") ArrayList<Integer> categories) {
		return siteService.countSitesByKeywordAndCategory(keyword, categories);
	}

	@GetMapping("sites/filter/category")
	public List<SiteDTO> findSiteByCategory(@RequestParam(value = "categoryId") Integer categoryId) {
		return siteService.findSiteByCategory(categoryId);
	}

	@GetMapping("sites/filter/category/pagination")
	public Long countSitesByCategory(@RequestParam(value = "categoryId") Integer categoryId) {
		return siteService.countSitesByCategory(categoryId);
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
		Site registerSite = siteService.registerSite(site.getName(), site.getProvince(), site.getTownHall(),
				site.getCategory(), site.getLatitude(), site.getLongitude(), site.getLatitudePark(),
				site.getLongitudePark(), site.getDescription(), site.getSiteDetails());
		return new SiteDTO(registerSite);
	}

	@PutMapping("sites/upload/{id}")
	public void uploadImage(@PathVariable(value = "id") Long id, @RequestParam("image") MultipartFile file) {
		siteService.uploadImage(id, file);
	}

	@GetMapping("sites/image/{id}")
	public String getImage(@PathVariable(value = "id") Long id) {
		return siteService.getImage(id);
	}

	@PutMapping("sites/update/{id}")
	public SiteDTO updateSite(@PathVariable(value = "id") Long id, @RequestBody SiteDTO siteDto) {
		return siteService.updateSite(id, siteDto);
	}

	@DeleteMapping("sites/delete/{id}")
	public Map<String, Boolean> deleteSite(@PathVariable(value = "id") Long id) {
		siteService.deleteSiteById(id);
		Map<String, Boolean> response = new HashMap<>();
		response.put("deleted", Boolean.TRUE);
		return response;
	}

	@GetMapping("sites/categories")
	public List<Category> findAllCategories() {
		return siteService.findAllCategories();
	}

}
