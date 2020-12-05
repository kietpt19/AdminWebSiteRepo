package com.lvtech.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.lvtech.model.ImagePost;

public interface ImagePostRepository extends JpaRepository<ImagePost, Long>{
	@Query("select i from ImagePost i where i.name = :name")
	ImagePost searchByName(String name);
}
