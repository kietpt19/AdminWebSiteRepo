package com.lvtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageLogo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idLogo;
	@Column(length = 100, nullable = false)
	private String nameLogo;
	@Column(length = 100, nullable = false)
	private String typeLogo;
	@Column(length = 1000, columnDefinition = "LONGBLOB")
	private byte[] picByteLogo;
	
	public ImageLogo() {
		super();
	}
	
	public ImageLogo(String nameLogo, String typeLogo) {
		super();
		this.nameLogo = nameLogo;
		this.typeLogo = typeLogo;
	}

	public ImageLogo(String nameLogo, String typeLogo, byte[] picByteLogo) {
		super();
		this.nameLogo = nameLogo;
		this.typeLogo = typeLogo;
		this.picByteLogo = picByteLogo;
	}

	public Long getIdLogo() {
		return idLogo;
	}

	public void setIdLogo(Long idLogo) {
		this.idLogo = idLogo;
	}

	public String getNameLogo() {
		return nameLogo;
	}

	public void setNameLogo(String nameLogo) {
		this.nameLogo = nameLogo;
	}

	public String getTypeLogo() {
		return typeLogo;
	}

	public void setTypeLogo(String typeLogo) {
		this.typeLogo = typeLogo;
	}

	public byte[] getPicByteLogo() {
		return picByteLogo;
	}

	public void setPicByteLogo(byte[] picByteLogo) {
		this.picByteLogo = picByteLogo;
	}
}
