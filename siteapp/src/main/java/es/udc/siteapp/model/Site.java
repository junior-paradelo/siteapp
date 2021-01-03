package es.udc.siteapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

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

	private float latitude;
	private float longitude;

	@Column(length = 250)
	private String description;

	public Site(String name, String province, String townHall, Category category, float latitude, float longitude,
			String description) {
		super();
		this.name = name;
		this.province = province;
		this.townHall = townHall;
		this.category = category;
		this.enabled = true;
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		this.latitude = latitude;
		this.longitude = longitude;
		this.description = description;
	}

}
