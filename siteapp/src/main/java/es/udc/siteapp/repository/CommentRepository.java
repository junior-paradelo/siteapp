package es.udc.siteapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import es.udc.siteapp.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {

	@Modifying
	@Query("from Comment where sitedetails_id = :siteDetailsId order by createdAt desc")
	List<Comment> findBySiteDetails(@Param("siteDetailsId") Long siteDetailsId);

}
