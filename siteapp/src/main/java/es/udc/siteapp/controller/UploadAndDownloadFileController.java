package es.udc.siteapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

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

	@GetMapping("/downloads/")
	public List<String> downloadFile(@RequestParam("siteId") Long siteId) {
		return fileStorageService.downloadFiles(siteId);
	}

}
