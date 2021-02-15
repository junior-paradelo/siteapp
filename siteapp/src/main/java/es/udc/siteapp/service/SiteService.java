package es.udc.siteapp.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import es.udc.siteapp.exception.ResourceNotFoundException;
import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Comment;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.SiteDetails;
import es.udc.siteapp.model.User;
import es.udc.siteapp.repository.CategoryRepository;
import es.udc.siteapp.repository.CommentRepository;
import es.udc.siteapp.repository.SiteDetailsRepository;
import es.udc.siteapp.repository.SiteRepository;
import es.udc.siteapp.repository.UserRepository;
import es.udc.siteapp.repository.UserSiteRepository;
import es.udc.siteapp.service.dto.CommentDTO;
import es.udc.siteapp.service.dto.SiteDTO;

@Service
public class SiteService {

	@Autowired
	private SiteRepository siteRepository;

	@Autowired
	private CategoryRepository categoryRepository;

	@Autowired
	private UserSiteRepository userSiteRepository;

	@Autowired
	private SiteDetailsRepository siteDetailsRepository;

	@Autowired
	private CommentRepository commentRepository;

	@Autowired
	private UserRepository userRepository;

	public List<SiteDTO> findAll() {
		List<Site> findAll = siteRepository.findAll(Sort.by(Sort.Direction.DESC, "createdAt"));
//		List<Site> findAll = siteRepository.findAllByOrderByCreatedAtDesc();
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (Site site : findAll) {
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public List<SiteDTO> findSitesByName(String name) {
		List<Site> list = siteRepository.findByNameContainingIgnoreCase(name);
		if (list.isEmpty()) {
			throw new ResourceNotFoundException("Site not found with name: " + name);
		}
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < list.size(); i++) {
			Site site = list.get(i);
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public List<SiteDTO> findLastSites(Long categoryId) {
		List<Site> results = null;
		if (categoryId != null) {
			Category category = categoryRepository.getOne(categoryId);
			results = siteRepository.findTop20ByCategoryOrderByCreatedAtDesc(category);
		} else {
			results = siteRepository.findTop20ByOrderByCreatedAtDesc();
		}

		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (Site site : results) {
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public List<SiteDTO> findSiteByKeywordAndCategory(String keyword, ArrayList<Integer> categories) {
		List<Site> findSiteByKeyword = null;
		if (categories == null) {
			findSiteByKeyword = siteRepository.findSiteByKeyword(keyword);
		} else {
			findSiteByKeyword = siteRepository.findSiteByKeywordAndCategory(keyword, categories);
		}
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < findSiteByKeyword.size(); i++) {
			Site site = findSiteByKeyword.get(i);
			SiteDTO siteDTO = new SiteDTO(site);
			siteDtoList.add(siteDTO);
		}
		return siteDtoList;
	}

	public List<SiteDTO> findSiteByCategory(Integer categoryId) {
		List<Site> findSiteByCategory = siteRepository.findByCategory(categoryId.longValue());
		List<SiteDTO> siteDtoList = new LinkedList<>();
		for (int i = 0; i < findSiteByCategory.size(); i++) {
			Site site = findSiteByCategory.get(i);
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

	public List<CommentDTO> getCommentsById(Long siteId) {
		Site site = siteRepository.getOne(siteId);
		SiteDetails siteDetails = site.getSiteDetails();
		List<Comment> commentList = commentRepository.findBySiteDetails(siteDetails.getId());
		List<CommentDTO> resultList = new LinkedList<>();
		for (Comment comm : commentList) {
			CommentDTO commentDTO = new CommentDTO(comm);
			resultList.add(commentDTO);
		}
		return resultList;
	}

	public void insertComment(Long siteId, CommentDTO commentDto) {
		Site site = siteRepository.getOne(siteId);
		User user = userRepository.findByUserId(commentDto.getAutorId()).get(0);
		String realName = user.getFirstname() + " " + user.getLastname();
		Comment comment = new Comment(realName, commentDto.getAutorId(), commentDto.getText(), site.getSiteDetails());
		commentRepository.save(comment);
	}

	public Site registerSite(String name, String province, String townHall, Long categoryId, Double latitude,
			Double longitude, Double latitudePark, Double longitudePark, String description, SiteDetails siteDetails) {
		GeometryFactory gf = new GeometryFactory();
		if (latitudePark == null) {
			latitudePark = latitude;
		}
		if (longitudePark == null) {
			longitudePark = longitude;
		}
		SiteDetails sDetails = new SiteDetails(siteDetails.getHeader(), siteDetails.getResume(),
				siteDetails.getConstraints(), siteDetails.getAccessType(), siteDetails.getGoCar(),
				siteDetails.getGoChildren());
		SiteDetails sdSaved = siteDetailsRepository.save(sDetails);
		Category category = categoryRepository.getOne(categoryId);
		Site site = new Site(name, province, townHall, category, description, sdSaved,
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
			Category category = categoryRepository.getOne(siteDto.getCategory());
			site.setCategory(category);
		}
		if (siteDto.getLatitude() != null && siteDto.getLongitude() != null) {
			GeometryFactory gf = new GeometryFactory();
			Point coordinates = gf.createPoint(new Coordinate(siteDto.getLatitude(), siteDto.getLongitude()));
			site.setCoordinates(coordinates);
		}
		if (siteDto.getLatitudePark() != null && siteDto.getLongitudePark() != null) {
			GeometryFactory gf = new GeometryFactory();
			Point coordinates = gf.createPoint(new Coordinate(siteDto.getLatitudePark(), siteDto.getLongitudePark()));
			site.setCoordinatesPark(coordinates);
		}
		if (siteDto.getDescription() != null) {
			site.setDescription(siteDto.getDescription());
		}
		SiteDetails siteDetailsDto = siteDto.getSiteDetails();
		if (siteDetailsDto != null) {
			if (siteDetailsDto.getHeader() != null) {
				site.getSiteDetails().setHeader(siteDetailsDto.getHeader());
			}
			if (siteDetailsDto.getResume() != null) {
				site.getSiteDetails().setResume(siteDetailsDto.getResume());
			}
			if (siteDetailsDto.getConstraints() != null) {
				site.getSiteDetails().setConstraints(siteDetailsDto.getConstraints());
			}
			if (siteDetailsDto.getGoCar() != null) {
				site.getSiteDetails().setGoCar(siteDetailsDto.getGoCar());
			}
			if (siteDetailsDto.getGoChildren() != null) {
				site.getSiteDetails().setGoChildren(siteDetailsDto.getGoChildren());
			}
			if (siteDetailsDto.getAccessType() != null) {
				site.getSiteDetails().setAccessType(siteDetailsDto.getAccessType());
			}
		}

		Site siteSave = siteRepository.save(site);
		return new SiteDTO(siteSave);
	}

	@Transactional
	public void deleteSiteById(Long id) {
		userSiteRepository.deleteBySite(id);
		siteRepository.deleteById(id);
	}

	public List<Category> findAllCategories() {
		return categoryRepository.findAll();
	}

}
