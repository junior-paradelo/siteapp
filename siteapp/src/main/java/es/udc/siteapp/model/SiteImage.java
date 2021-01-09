package es.udc.siteapp.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class SiteImage {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "siteimage_seq_gen")
	@SequenceGenerator(name = "siteimage_seq_gen", sequenceName = "siteimage_id_seq", allocationSize = 1)
	private Long id;

	@Column(length = 150)
	private String imageName;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SITEID", referencedColumnName = "ID")
	private Site site;

	public SiteImage(String imageName, Site site) {
		super();
		this.imageName = imageName;
		this.site = site;
	}

}
