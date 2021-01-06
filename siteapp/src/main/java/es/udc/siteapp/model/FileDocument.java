package es.udc.siteapp.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.SequenceGenerator;

import lombok.Data;

@Entity
@Data
public class FileDocument {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "file_seq_gen")
	@SequenceGenerator(name = "file_seq_gen", sequenceName = "file_id_seq", allocationSize = 1)
	private Long id;

	@Column(length = 100)
	private String fileName;

	@Lob
	private byte[] docFile;
}
