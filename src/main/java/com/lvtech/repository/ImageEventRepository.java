package com.lvtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lvtech.model.ImageEvent;

public interface ImageEventRepository extends JpaRepository<ImageEvent, Long>{
	@Query("select i from ImageEvent i where i.name = :name")
	ImageEvent searchByName(String name);
}
