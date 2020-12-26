package es.udc.siteapp.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class Authority {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "authority_seq_gen")
	@SequenceGenerator(name = "authority_seq_gen", sequenceName = "authority_id_seq", allocationSize = 1)
	private Long id;

	@Column(length = 50, nullable = false)
	@Enumerated(EnumType.STRING)
	private AuthorityName name;

	@ManyToMany(mappedBy = "authorities", fetch = FetchType.LAZY)
	private List<User> users;
}
