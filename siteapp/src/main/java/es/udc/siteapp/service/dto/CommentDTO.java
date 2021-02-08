package es.udc.siteapp.service.dto;

import java.sql.Timestamp;

import es.udc.siteapp.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class CommentDTO {

	private Long autorId;

	private String autorName;

	private String text;

	private Timestamp createdAt;

	private Long siteDetailsId;

	public CommentDTO(Comment comment) {
		this.autorId = comment.getAutorId();
		this.autorName = comment.getAutor();
		this.text = comment.getText();
		this.createdAt = comment.getCreatedAt();
		this.siteDetailsId = comment.getSiteDetails().getId();
	}

}
