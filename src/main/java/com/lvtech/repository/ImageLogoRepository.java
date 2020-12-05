package com.lvtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lvtech.model.ImageLogo;

public interface ImageLogoRepository extends JpaRepository<ImageLogo, Long>{
	@Query("select i from ImageLogo i where i.nameLogo = :nameLogo")
	ImageLogo searchByName(String nameLogo);
}
