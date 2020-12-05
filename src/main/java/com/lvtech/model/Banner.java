package com.lvtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Banner {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idBanner;
	@Column(length = 50, nullable = false)
	private String nameBanner;
	@Column(length = 200)
	private String image;
	
	public Banner() {
		super();
	}

	public Banner(String nameBanner, String image) {
		super();
		this.nameBanner = nameBanner;
		this.image = image;
	}
	
	public Long getIdBanner() {
		return idBanner;
	}

	public void setIdBanner(Long idBanner) {
		this.idBanner = idBanner;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getNameBanner() {
		return nameBanner;
	}

	public void setNameBanner(String nameBanner) {
		this.nameBanner = nameBanner;
	}
}
