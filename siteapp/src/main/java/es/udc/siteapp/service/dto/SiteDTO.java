package es.udc.siteapp.service.dto;

import java.sql.Timestamp;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.SiteDetails;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SiteDTO {

	private Long id;
	private String name;
	private String province;
	private String townHall;
	private Category category;
	private String description;
	private Timestamp createdAt;
	private SiteDetails siteDetails;
	private String image;
	// POINT(x y)
	private Double latitude;
	private Double longitude;
	private Double latitudePark;
	private Double longitudePark;

	public SiteDTO(Site site) {
		id = site.getSiteId();
		name = site.getName();
		province = site.getProvince();
		townHall = site.getTownHall();
		category = site.getCategory();
		description = site.getDescription();
		createdAt = site.getCreatedAt();
		siteDetails = site.getSiteDetails();
		if (site.getCoordinates() != null) {
			latitude = site.getCoordinates().getX();
			longitude = site.getCoordinates().getY();
		}
		if (site.getCoordinatesPark() != null) {
			latitudePark = site.getCoordinatesPark().getX();
			longitudePark = site.getCoordinatesPark().getY();
		}
		image = site.getImage();
	}
}
