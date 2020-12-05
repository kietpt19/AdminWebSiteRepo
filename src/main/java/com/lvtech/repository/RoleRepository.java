package com.lvtech.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lvtech.model.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {

	@Query("select r.form.codeForm from Role r where r.groub.idGroub = :id_groub")
	List<String> getCodeForm(Long id_groub);

	@Query("select r from Role r")
	List<Role> getData(Pageable pegeable);

	@Query("select count(*) from Role")
	Integer getNumberLine();

}
