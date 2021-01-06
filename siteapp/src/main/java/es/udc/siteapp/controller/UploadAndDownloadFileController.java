package es.udc.siteapp.controller;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.google.common.net.HttpHeaders;

import es.udc.siteapp.model.FileDocument;
import es.udc.siteapp.repository.DocFileRepository;
import es.udc.siteapp.storage.FileStorageService;
import es.udc.siteapp.storage.FileUploadResponse;

@RestController
public class UploadAndDownloadFileController {

	@Autowired
	private FileStorageService fileStorageService;

	@Autowired
	private DocFileRepository docFileRepository;

	@PostMapping("/upload")
	FileUploadResponse uploadFile(@RequestParam("file") MultipartFile file) {
		String filename = fileStorageService.storeFile(file);

		// http://localhost:9090/download/abc.jpg
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(filename)
				.toUriString();
		String contentType = file.getContentType();
		return new FileUploadResponse(filename, contentType, url);
	}

	@GetMapping("/download/{filename}")
	ResponseEntity<Resource> downloadFile(@PathVariable String filename, HttpServletRequest request) {
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

	@PostMapping("/uploadDb")
	FileUploadResponse uploadFileDb(@RequestParam("file") MultipartFile file) throws IOException {

		String name = StringUtils.cleanPath(file.getOriginalFilename());
		FileDocument fileDocument = new FileDocument();
		fileDocument.setFileName(name);
		fileDocument.setDocFile(file.getBytes());

		docFileRepository.save(fileDocument);

		// http://localhost:9090/abc.jpg
		String url = ServletUriComponentsBuilder.fromCurrentContextPath().path("/download/").path(name).toUriString();
		String contentType = file.getContentType();
		return new FileUploadResponse(name, contentType, url);
	}

	@GetMapping("/downloadDb/{filename}")
	ResponseEntity<byte[]> downloadFileDb(@PathVariable String filename, HttpServletRequest request) {

		FileDocument doc = docFileRepository.findByFileName(filename);

		String mimeType = request.getServletContext().getMimeType(doc.getFileName());

		return ResponseEntity.ok().contentType(MediaType.parseMediaType(mimeType))
				.header(HttpHeaders.CONTENT_DISPOSITION, "inline;fileName=" + doc.getFileName()).body(doc.getDocFile());
	}

}
