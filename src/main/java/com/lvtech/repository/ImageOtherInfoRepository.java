package com.lvtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lvtech.model.ImageOtherInfo;

public interface ImageOtherInfoRepository extends JpaRepository<ImageOtherInfo, Long>{
	@Query("select i from ImageOtherInfo i where i.name = :name")
	ImageOtherInfo searchByName(String name);
}
