package es.udc.siteapp.service.dto;

import es.udc.siteapp.model.UserSite;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSiteDTO {
	private Long userSiteId;
	private Long userId;
	private SiteDTO site;
	private Integer rate;
	private String state;

	public UserSiteDTO(UserSite userSite) {
		this.userSiteId = userSite.getUserSiteId();
		this.userId = userSite.getUser().getUserId();
		this.site = new SiteDTO(userSite.getSite());
		this.rate = userSite.getRate();
		this.state = userSite.getState().name();
	}
}
