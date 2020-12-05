package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Guest;

public interface GuestRepository extends PagingAndSortingRepository<Guest, Long>{
	@Query("select g from Guest g where "
			+ "g.phoneGuest = ?1")
	Guest searchPhoneGuest(String guest);
	
	@Query("select g from Guest g where "
			+ "g.fullName like %?1% ")
	List<Guest> filterGuest(String guest, Pageable pegeable);
	
	@Query("select count(*) from Guest")
	Integer getNumberLine();

	@Query("select g from Guest g")
	List<Guest> getData(Pageable pegeable);

	@Query("select count(g) from Guest g where g.fullName like %?1%")
	Integer getNumberLineForFilter(String guest);
}
