package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.lvtech.model.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	@Query("select e from Employee e where e.employeeName like %?1%")
	List<Employee> filterName(String employee, Pageable pegeable);
	
	@Query("select count(*) from Employee")
	Integer getNumberLine();

	@Query("select e from Employee e")
	List<Employee> getData(Pageable pegeable);

	@Query("select count(e) from Employee e where e.employeeName like %?1%")
	Integer getNumberLineForFilter(String employee);
	
	Employee findByUsername(String username);
}
