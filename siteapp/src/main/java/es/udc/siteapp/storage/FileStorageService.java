package es.udc.siteapp.storage;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Base64;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import es.udc.siteapp.exception.StorageException;
import es.udc.siteapp.model.Site;
import es.udc.siteapp.model.SiteImage;
import es.udc.siteapp.repository.SiteImageRepository;
import es.udc.siteapp.repository.SiteRepository;

@Service
public class FileStorageService {

	private Path fileStoragePath;
	private String fileStorageLocation;

	@Autowired
	private SiteImageRepository siteImageRepository;

	@Autowired
	private SiteRepository siteRepository;

	public FileStorageService(@Value("${file.storage.location:temp}") String fileStorageLocation) {
		this.fileStorageLocation = fileStorageLocation;
		fileStoragePath = Paths.get(fileStorageLocation).toAbsolutePath().normalize();
		try {
			Files.createDirectories(fileStoragePath);
		} catch (IOException e) {
			throw new StorageException("Issue in creating file directory");
		}
	}

	public String storeFile(Long siteId, MultipartFile file) {
		Optional<Site> result = siteRepository.findById(siteId);
		String filename = null;
		if (result.isPresent()) {
			filename = StringUtils.cleanPath(file.getOriginalFilename());
			Path filePath = Paths.get(fileStoragePath + "\\" + filename);
			try {
				Files.copy(file.getInputStream(), filePath, StandardCopyOption.REPLACE_EXISTING);
			} catch (IOException e) {
				throw new StorageException("Issue in storing the file");
			}
			SiteImage siteImage = new SiteImage(filename, result.get());
			siteImageRepository.save(siteImage);
		}
		return filename;
	}

	public Resource downloadFile(String filename) {
		Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(filename);
		Resource resource;
		try {
			resource = new UrlResource(path.toUri());
		} catch (MalformedURLException e) {
			throw new StorageException("Issue in reading the file: " + e);
		}
		return resource;
	}

	public List<String> downloadFiles(Long siteId) {
		List<SiteImage> list = siteImageRepository.findBySite(siteId);
		List<String> listaB64 = new LinkedList<>();
		for (SiteImage siteImage : list) {
			Path path = Paths.get(fileStorageLocation).toAbsolutePath().resolve(siteImage.getImageName());
			byte[] fileContent;
			try {
				fileContent = Files.readAllBytes(path);
			} catch (IOException e) {
				throw new StorageException("Issue in reading the file: " + e);
			}
			String encodeToString = Base64.getEncoder().encodeToString(fileContent);
			listaB64.add(encodeToString);
		}
		return listaB64;
	}
}
