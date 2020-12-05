package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Subject;

public interface SubjectRepository extends PagingAndSortingRepository<Subject, Long>{

	@Query("select s from Subject s where s.nameSubject like %?1%")
	List<Subject> filterByName(String nameSubject, Pageable pegeable);

	@Query("select s from Subject s")
	List<Subject> getData(Pageable pegeable);
	
	@Query("select s from Subject s, Country c where "
			+ "s.country.idCountry = c.idCountry "
			+ "and s.country.idCountry= :idCountry")
	List<Subject> getAllSubjectByCountry(Long idCountry);
	
	@Query("select count(s) from Subject s, Country c where "
			+ "s.country.idCountry = c.idCountry "
			+ "and s.country.idCountry= :idCountry")
	Integer getNumberOneSubjectByCountry(Long idCountry);
	
	@Query("select s from Subject s, Country c where "
			+ "s.country.idCountry = c.idCountry "
			+ "and s.country.idCountry= :idCountry")
	Subject getOneSubjectByCountry(Long idCountry);
	
	@Query("select DISTINCT s.country.idCountry from Subject s")
	List<Integer> getIdSubject();

	@Query("select count(*) from Subject")
	Integer getNumberLine();

	@Query("select count(s) from Subject s where s.nameSubject like %?1%")
	Integer getNumberLineForFilter(String nameSubject);
	
//	@Query("select bd from BillDetails bd, Bill b where " + "bd.bill.id = b.id and b.id = :billID ")
//	List<BillDetails> findBillDetails(Integer billID);
}
