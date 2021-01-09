package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class SiteDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sitedetails_seq_gen")
	@SequenceGenerator(name = "sitedetails_seq_gen", sequenceName = "sitedetails_id_seq", allocationSize = 1)
	private Long id;

	@Column(columnDefinition = "TEXT")
	private String header;

	@Column(columnDefinition = "TEXT")
	private String resume;

	@Column(columnDefinition = "TEXT")
	private String comment;

}
