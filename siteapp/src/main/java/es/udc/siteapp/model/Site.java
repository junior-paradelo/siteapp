package es.udc.siteapp.model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.locationtech.jts.geom.Point;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sites")
@EqualsAndHashCode(callSuper = false)
public class Site extends AuditModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_seq_gen")
	@SequenceGenerator(name = "site_seq_gen", sequenceName = "site_id_seq", allocationSize = 1)
	private Long siteId;

	@Column(length = 100)
	private String name;

	@Column(length = 100)
	private String province;

	@Column(length = 100)
	private String townHall;

	@Column(nullable = false)
	private boolean enabled;

	@ManyToOne
	@JoinColumn(name = "CATEGORY_ID", referencedColumnName = "ID")
	private Category category;

	@Column(length = 250)
	private String description;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "SITEDETAILS_ID", referencedColumnName = "ID")
	private SiteDetails siteDetails;

	// ST_asText(coordinates)
	@Column(columnDefinition = "geometry(Point,4326)")
	public Point coordinates;

	@Column(columnDefinition = "geometry(Point,4326)")
	public Point coordinatesPark;

	// Base64.getEncoder().encodeToString(file.getBytes())
	@Column(columnDefinition = "text")
	private String image;

	public Site(String name, String province, String townHall, Category category, String description,
			SiteDetails siteDetails, Point coordinates, Point coordinatesPark, String image) {
		super();
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		this.name = name;
		this.province = province;
		this.townHall = townHall;
		this.enabled = true;
		this.category = category;
		this.description = description;
		this.siteDetails = siteDetails;
		this.coordinates = coordinates;
		this.coordinatesPark = coordinatesPark;
		this.image = image;
	}

}
