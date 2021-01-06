package es.udc.siteapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import es.udc.siteapp.model.FileDocument;

@Repository
public interface DocFileRepository extends JpaRepository<FileDocument, Long> {

	FileDocument findByFileName(String filename);

}
