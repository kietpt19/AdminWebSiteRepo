package com.lvtech.model;

import java.nio.CharBuffer;
import java.nio.charset.Charset;
import java.nio.ByteBuffer;
import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ImageOtherInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(length = 100, nullable = false)
	private String name;
	@Column(length = 100, nullable = false)
	private String type;
	@Column(length = 1000, columnDefinition = "LONGBLOB")
	private byte[] picByte;

	public ImageOtherInfo() {
		super();
	}

	public ImageOtherInfo(String name, String type, byte[] picByte) {
		super();
		this.name = name;
		this.type = type;
		this.picByte = picByte;
	}

	public ImageOtherInfo(String name, String type) {
		super();
		this.name = name;
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public byte[] getPicByte() {
		return picByte;
	}

	public void setPicByte(byte[] picByte) {
		this.picByte = picByte;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
}
