package es.udc.siteapp.model;

import java.sql.Timestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
	private String name;
	private String province;
	private String townHall;
	private Category category;
	@Column(nullable = false)
	private boolean active;
//	private Point coordenates;

	public Site(String name, String province, String townHall, Category category) {
		super();
		this.name = name;
		this.province = province;
		this.townHall = townHall;
		this.category = category;
		this.active = true;
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
	}

}
