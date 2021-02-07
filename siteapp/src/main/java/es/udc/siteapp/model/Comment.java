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
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Comment {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "comment_seq_gen")
	@SequenceGenerator(name = "comment_seq_gen", sequenceName = "comment_id_seq", allocationSize = 1)
	private Long id;

	private String autor;

	private Long autorId;

	@Column(columnDefinition = "TEXT")
	private String text;

	private Timestamp createdAt;

	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "sitedetails_id", referencedColumnName = "id")
	private SiteDetails siteDetails;

	public Comment(String autor, Long autorId, String text, SiteDetails siteDetails) {
		this.autor = autor;
		this.autorId = autorId;
		this.text = text;
		this.setCreatedAt(new Timestamp(System.currentTimeMillis()));
		this.siteDetails = siteDetails;
	}

}
