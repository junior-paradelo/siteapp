package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor
public class Site extends AuditModel {

	@Id
    @Column(name = "id")
	private Long siteId;
	private String name;
	private String province;
	private String townHall;
//	private siteType;
//	private Point coordenates;
	
}
