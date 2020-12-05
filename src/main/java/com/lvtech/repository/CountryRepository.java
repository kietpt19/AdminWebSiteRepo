package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Country;
import com.lvtech.model.Subject;

public interface CountryRepository extends PagingAndSortingRepository<Country, Long>{

	@Query("select c from Country c where c.nameCountry like %?1%")
	List<Country> filterByName(String nameCountries, Pageable pegeable);

	@Query("select c from Country c")
	List<Country> getData(Pageable pegeable);

	@Query("select count(*) from Country")
	Integer getNumberLine();

	@Query("select count(c) from Country c where c.nameCountry like %?1%")
	Integer getNumberLineForFilter(String nameCountry);
	
	@Query("select s from Subject s, Country c where s.country.idCountry = c.idCountry "
			+ "and c.idCountry= :idCountry")
	List<Subject> getSubjectByCountry(Long idCountry);
}
