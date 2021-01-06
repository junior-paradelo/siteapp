package es.udc.siteapp.storage;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class FileUploadResponse {

	private String filename;
	private String url;
	private String contentType;
}
