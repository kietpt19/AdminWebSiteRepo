package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Groub;

public interface GroubRepository extends PagingAndSortingRepository<Groub, Long>{
	
	@Query("select g from Groub g where g.nameGroub like %?1%")
	List<Groub> filterByName(String nameGroub, Pageable pegeable);

	@Query("select g from Groub g")
	List<Groub> getData(Pageable pegeable);

	@Query("select count(*) from Groub")
	Integer getNumberLine();

	@Query("select count(g) from Groub g where g.nameGroub like %?1%")
	Integer getNumberLineForFilter(String nameGroub);
}
