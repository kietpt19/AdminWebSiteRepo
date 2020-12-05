package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Event;

public interface EventRepository extends PagingAndSortingRepository<Event, Long>{
	
	@Query("select e from Event e where e.titleEvent like %?1%")
	List<Event> filterByName(String titleEvent, Pageable pegeable);
	
	@Query("select count(*) from Event")
	Integer getNumberLine();

	@Query("select e from Event e")
	List<Event> getData(Pageable pegeable);

	@Query("select count(e) from Event e where e.titleEvent like %?1%")
	Integer getNumberLineForFilter(String titleEvent);
}
