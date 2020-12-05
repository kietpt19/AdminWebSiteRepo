package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Post;

public interface PostRepository extends PagingAndSortingRepository<Post, Long>{
	
	@Query("select p from Post p where p.titlePost like %?1%")
	List<Post> filterByName(String titlePost, Pageable pegeable);
	
	@Query("select count(*) from Post")
	Integer getNumberLine();

	@Query("select p from Post p")
	List<Post> getData(Pageable pegeable);

	@Query("select count(p) from Post p where p.titlePost like %?1%")
	Integer getNumberLineForFilter(String titlePost);
	
	@Query("select count(p) from Post p, Country c, Subject s where s.country.idCountry = c.idCountry "
			+ "and s.idSubject = p.subject.idSubject and c.idCountry= :country_id")
	Integer getNumberLineForCountry(Long country_id);
	
	@Query("select count(p) from Post p where p.subject.idSubject= :subject_id")
	Integer getNumberLineForSubject(Long subject_id);
	
	@Query("select p from Post p where p.subject.idSubject= :subject_id")
	List<Post> getPostBySubject(Long subject_id, Pageable pegeable);
	
	@Query("select p from Post p, Country c, Subject s "
			+ "where s.country.idCountry = c.idCountry "
			+ "and s.idSubject = p.subject.idSubject "
			+ "and s.country.idCountry = :country_id")
	List<Post> getPostByCountry(Long country_id, Pageable pegeable);
}
