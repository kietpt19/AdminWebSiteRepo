package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Form;

public interface FormRepository extends PagingAndSortingRepository<Form, Long> {
	
	@Query("select f from Form f where f.codeForm like %?1%")
	List<Form> filterByCodeForm(String codeForm, Pageable pegeable);

	@Query("select f from Form f")
	List<Form> getData(Pageable pegeable);

	@Query("select count(*) from Form")
	Integer getNumberLine();

	@Query("select count(f) from Form f where f.codeForm like %?1%")
	Integer getNumberLineForFilter(String codeForm);

}
