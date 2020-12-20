package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class UserSite {

	@Id
    @Column(name = "id")
	private Long userSiteId;
//	private Site site;
//	private User user;
	private Integer rate;
//	private UserSiteState state;
}
