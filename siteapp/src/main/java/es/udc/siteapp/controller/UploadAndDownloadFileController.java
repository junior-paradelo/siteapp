package es.udc.siteapp.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.net.HttpHeaders;

import es.udc.siteapp.storage.FileStorageService;

@RestController
@RequestMapping("/api/")
public class UploadAndDownloadFileController {

	@Autowired
	private FileStorageService fileStorageService;

	@PostMapping("/upload/{id}")
	public ResponseEntity<Void> uploadFile(@PathVariable Long id, @RequestParam("image") MultipartFile file) {
		fileStorageService.storeFile(id, file);
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@PostMapping(value = "/uploads/{id}")
	public ResponseEntity<Void> uploadFiles(@PathVariable Long id, @RequestParam("images") MultipartFile[] files) {
		for (MultipartFile file : files) {
			fileStorageService.storeFile(id, file);
		}
		return new ResponseEntity<>(HttpStatus.OK);
	}

	@GetMapping("/download/{filename}")
	public ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
		Resource resource = fileStorageService.downloadFile(filename);
		String mimeType;

		try {
			mimeType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
		} catch (IOException e) {
			mimeType = MediaType.APPLICATION_OCTET_STREAM_VALUE;
		}

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + resource.getFilename()).body(resource);
	}

	@GetMapping("/downloads/")
	public List<String> downloadFile(@RequestParam("siteId") Long siteId) {
		return fileStorageService.downloadFiles(siteId);
	}

}
