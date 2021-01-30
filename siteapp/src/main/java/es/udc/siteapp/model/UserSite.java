package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserSite {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "usersite_seq_gen")
	@SequenceGenerator(name = "usersite_seq_gen", sequenceName = "usersite_id_seq", allocationSize = 1)
	private Long userSiteId;

	@ManyToOne
	@JoinColumn(name = "site_id", referencedColumnName = "id")
	private Site site;

	@ManyToOne
	@JoinColumn(name = "user_id", referencedColumnName = "id")
	private User user;

	private Integer rate;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private UserSiteState state;

	public UserSite(Site site, User user, Integer rate, UserSiteState state) {
		super();
		this.site = site;
		this.user = user;
		this.rate = rate;
		this.state = state;
	}

	public UserSite(Site site, User user, UserSiteState state) {
		super();
		this.site = site;
		this.user = user;
		this.state = state;
	}
}
