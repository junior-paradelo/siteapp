package es.udc.siteapp.service.dto;

import es.udc.siteapp.model.Category;
import es.udc.siteapp.model.Site;
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
	private float latitude;
	private float longitude;
	private String description;

	public SiteDTO(Site site) {
		id = site.getSiteId();
		name = site.getName();
		province = site.getProvince();
		townHall = site.getTownHall();
		category = site.getCategory();
		latitude = site.getLatitude();
		longitude = site.getLongitude();
		description = site.getDescription();
	}
}
