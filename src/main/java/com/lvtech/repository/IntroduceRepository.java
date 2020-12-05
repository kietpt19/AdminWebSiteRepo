package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Introduce;

public interface IntroduceRepository extends PagingAndSortingRepository<Introduce, Long>{

	@Query("select i from Introduce i where i.titleIntroduce like %?1%")
	List<Introduce> filterByIntroduce(String titleIntroduce, Pageable pegeable);

	@Query("select i from Introduce i")
	List<Introduce> getData(Pageable pegeable);

	@Query("select count(*) from Introduce")
	Integer getNumberLine();

	@Query("select count(i) from Introduce i where i.titleIntroduce like %?1%")
	Integer getNumberLineForFilter(String titleIntroduce);
}
