package es.udc.siteapp.service;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import es.udc.siteapp.model.Site;
import es.udc.siteapp.repository.SiteRepository;
import es.udc.siteapp.service.dto.SiteDTO;

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
		SiteDTO siteDto = null;
		Optional<Site> optionalSite = siteDAO.findById(id);
		if (optionalSite.isPresent()) {
			Site site = optionalSite.get();
			siteDto = new SiteDTO(site);
		}
		return siteDto;
	}

	public Site getCompleteSiteById(Long id) {
		return siteDAO.findById(id).orElse(null);
	}

	public SiteDTO getSiteByName(String name) {
		Site site = siteDAO.findSiteByName(name);
		return new SiteDTO(site);
	}

	public void registerSite(Site site) {
		siteDAO.save(site);
	}

	public void deleteSiteById(Long id) {
		siteDAO.deleteById(id);
	}

	public SiteDTO updateSite(SiteDTO siteDto) {
		SiteDTO siteDtoResponse = null;
		Optional<Site> optionalSite = siteDAO.findById(siteDto.getId());
		if (optionalSite.isPresent()) {
			Site site = optionalSite.get();
			site.setName(siteDto.getName());
			site.setProvince(siteDto.getProvince());
			site.setTownHall(siteDto.getTownHall());
			Site siteSave = siteDAO.save(site);
			siteDtoResponse = new SiteDTO(siteSave);
		}
		return siteDtoResponse;
	}
}
