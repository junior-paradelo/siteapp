package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "sites")
public class Site extends AuditModel {

	@Id
	@Column(name = "id")
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "site_seq_gen")
	@SequenceGenerator(name = "site_seq_gen", sequenceName = "site_id_seq")
	private Long siteId;
	private String name;
	private String province;
	private String townHall;
//	private Point coordenates;

	public Site(String name, String province, String townHall) {
		super();
		this.name = name;
		this.province = province;
		this.townHall = townHall;
	}
}
