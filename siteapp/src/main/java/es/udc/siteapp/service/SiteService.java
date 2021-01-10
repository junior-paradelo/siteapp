package es.udc.siteapp.service;

import java.io.IOException;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.repository.CategoryRepository;
import es.udc.siteapp.repository.SiteRepository;
import es.udc.siteapp.service.dto.SiteDTO;

@Service
public class SiteService {

	@Autowired
	SiteRepository siteRepository;

	@Autowired
	CategoryRepository categoryRepository;

	public List<SiteDTO> findAll() {
		List<Site> findAll = siteRepository.findAll();
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			Site site = findAll.get(i);
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public List<SiteDTO> findSiteByKeyword(String keyword) {
		List<Site> findSiteByKeyword = siteRepository.findSiteByKeyword(keyword);
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < findSiteByKeyword.size(); i++) {
			Site site = findSiteByKeyword.get(i);
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public SiteDTO getSiteById(Long id) {
		Site site = siteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + id));
		return new SiteDTO(site);
	}

	public Site getCompleteSiteById(Long id) {
		return siteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complete site not found with id: " + id));
	}

	public SiteDTO getSiteByName(String name) {
		Site site = siteRepository.findSiteByName(name);
		if (site == null) {
			throw new ResourceNotFoundException("Site not found with name: " + name);
		}
		return new SiteDTO(site);
	}

	public Site registerSite(String name, String province, String townHall, Category category, Double latitude,
			Double longitude, Double latitudePark, Double longitudePark, String description) {
		GeometryFactory gf = new GeometryFactory();
		Site site = new Site(name, province, townHall, category, description, null,
				gf.createPoint(new Coordinate(latitude, longitude)),
				gf.createPoint(new Coordinate(latitudePark, longitudePark)), null);
		return siteRepository.save(site);
	}

	public void uploadImage(Long id, MultipartFile file) {
		Optional<Site> optionalSite = siteRepository.findById(id);
		if (optionalSite.isPresent()) {
			Site site = optionalSite.get();
			try {
				site.setImage(Base64.getEncoder().encodeToString(file.getBytes()));
			} catch (IOException e) {
				throw new ResourceNotFoundException("Error to insert image");
			}
			siteRepository.save(site);
		}
	}

	public String getImage(Long id) {
		Site site = siteRepository.getOne(id);
		return site.getImage();
	}

	public SiteDTO updateSite(Long id, SiteDTO siteDto) {
		Site site = siteRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + siteDto.getId()));
		if (siteDto.getName() != null) {
			site.setName(siteDto.getName());
		}
		if (siteDto.getProvince() != null) {
			site.setProvince(siteDto.getProvince());
		}
		if (siteDto.getTownHall() != null) {
			site.setTownHall(siteDto.getTownHall());
		}
		if (siteDto.getCategory() != null) {
			site.setCategory(siteDto.getCategory());
		}
		if (siteDto.getDescription() != null) {
			site.setDescription(siteDto.getDescription());
		}

		Site siteSave = siteRepository.save(site);
		return new SiteDTO(siteSave);
	}

	public void deleteSiteById(Long id) {
		siteRepository.deleteById(id);
	}

	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

}
