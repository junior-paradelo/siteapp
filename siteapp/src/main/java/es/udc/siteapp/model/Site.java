package es.udc.siteapp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Site extends AuditModel {

	private Long siteId;
	private String name;
	private String province;
	private String townHall;
//	private siteType;
//	private Point coordenates;
	
}
