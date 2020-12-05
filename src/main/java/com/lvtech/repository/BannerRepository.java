package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Banner;

public interface BannerRepository extends PagingAndSortingRepository<Banner, Long>{

	@Query("select b from Banner b where b.nameBanner like %?1%")
	List<Banner> filterByName(String nameCountries, Pageable pegeable);

	@Query("select b from Banner b")
	List<Banner> getData(Pageable pegeable);

	@Query("select count(*) from Banner")
	Integer getNumberLine();

	@Query("select count(b) from Banner b where b.nameBanner like %?1%")
	Integer getNumberLineForFilter(String nameBanner);
}