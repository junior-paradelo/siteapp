package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Data
@Entity
public class Category {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "category_seq_gen")
	@SequenceGenerator(name = "category_seq_gen", sequenceName = "category_id_seq", allocationSize = 1)
	private Long id;

	@Column(length = 50, nullable = false)
	private String name;

	@Column(length = 50, nullable = true)
	@Enumerated(EnumType.STRING)
	private Theme theme;
}
