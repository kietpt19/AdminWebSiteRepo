package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.OtherInfo;

public interface OtherInfoRepository extends PagingAndSortingRepository<OtherInfo, Long> {
	@Query("select o from OtherInfo o")
	List<OtherInfo> getData(Pageable pegeable);

	@Query("select count(*) from OtherInfo")
	Integer getNumberLine();
}
