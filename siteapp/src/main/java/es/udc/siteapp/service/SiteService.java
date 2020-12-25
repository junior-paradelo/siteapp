package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.repository.SiteRepository;
import es.udc.siteapp.service.dto.SiteDTO;

@Service
public class SiteService {

	@Autowired
	SiteRepository siteDAO;

	public List<SiteDTO> findAll() {
		List<Site> findAll = siteDAO.findAll();
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < findAll.size(); i++) {
			Site site = findAll.get(i);
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public SiteDTO getSiteById(Long id) {
		Site site = siteDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + id));
		return new SiteDTO(site);
	}

	public Site getCompleteSiteById(Long id) {
		return siteDAO.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("Complete site not found with id: " + id));
	}

	public SiteDTO getSiteByName(String name) {
		Site site = siteDAO.findSiteByName(name);
		if (site == null) {
			throw new ResourceNotFoundException("Site not found with name: " + name);
		}
		return new SiteDTO(site);
	}

	public Site registerSite(String name, String province, String townHall, Category category) {
		Site site = new Site(name, province, townHall, category);
		return siteDAO.save(site);
	}

	public SiteDTO updateSite(SiteDTO siteDto) {
		Site site = siteDAO.findById(siteDto.getId())
				.orElseThrow(() -> new ResourceNotFoundException("Site not found with id: " + siteDto.getId()));
		site.setName(siteDto.getName());
		site.setProvince(siteDto.getProvince());
		site.setTownHall(siteDto.getTownHall());
		Site siteSave = siteDAO.save(site);
		return new SiteDTO(siteSave);
	}

	public void deleteSiteById(Long id) {
		siteDAO.deleteById(id);
	}

}
