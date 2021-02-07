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
import es.udc.siteapp.model.Comment;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.service.SiteService;
import es.udc.siteapp.service.dto.SiteDTO;

@RestController
@RequestMapping("/api/")
public class SiteController {

	@Autowired
	private SiteService siteService;

	@GetMapping("sites")
	public List<SiteDTO> findAllSites() {
		return siteService.findAll();
	}

	@GetMapping("sites/last")
	public List<SiteDTO> findLastSites(@RequestParam(value = "categoryId", required = false) Long categoryId) {
		return siteService.findLastSites(categoryId);
	}

	@GetMapping("sites/name")
	public List<SiteDTO> findSitesByName(@RequestParam(value = "name") String name) {
		return siteService.findSitesByName(name);
	}

	@GetMapping("sites/filter")
	public List<SiteDTO> findSiteByKeywordAndCategory(@RequestParam(value = "keyword") String keyword,
			@RequestParam(value = "categories") ArrayList<Integer> categories) {
		return siteService.findSiteByKeywordAndCategory(keyword, categories);
	}

	@GetMapping("sites/filter/category")
	public List<SiteDTO> findSiteByCategory(@RequestParam(value = "categoryId") Integer categoryId) {
		return siteService.findSiteByCategory(categoryId);
	}

	@GetMapping("sites/{id}")
	public SiteDTO getSiteById(@PathVariable(value = "id") Long id) {
		return siteService.getSiteById(id);
	}

	@PostMapping("sites/create")
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

	@GetMapping("sites/comments/{id}")
	public List<Comment> getCommentsById(@PathVariable(value = "id") Long siteId) {
		return siteService.getCommentsById(siteId);
	}

	@PostMapping("sites/comment/")
	public Map<String, Boolean> insertComment(@RequestParam(value = "id") Long siteId,
			@RequestParam(value = "autorId") Long autorId, @RequestParam(value = "comment") String text) {
		siteService.insertComment(siteId, autorId, text);
		Map<String, Boolean> response = new HashMap<>();
		response.put("insert", Boolean.TRUE);
		return response;
	}
}
