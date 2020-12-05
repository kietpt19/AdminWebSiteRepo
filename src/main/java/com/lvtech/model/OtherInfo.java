package com.lvtech.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class OtherInfo {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idOtherInfo;
	@Column(nullable = false)
	private String address;
	@Column(length = 50, nullable = false)
	private String phone;
	@Column(nullable = false)
	private String email;
	@Column(nullable = false)
	private String facebook;
	@Column(nullable = false)
	private String google;
	@Column(nullable = false)
	private String tweeter;
	@Column(nullable = false, columnDefinition="MEDIUMTEXT")
	private String introduce;
	@Column(nullable = false, columnDefinition="TEXT")
	private String nameCompany;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imgLogo")
	private ImageLogo imgLogo;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "imgOtherInfo")
	private ImageOtherInfo imgOtherInfo;
	
	@ManyToOne
	@JoinColumn
	private Employee employee;

	public OtherInfo() {
		super();
	}
	
	public OtherInfo(String address, String phone, String email, String facebook, String google,
			String tweeter, String introduce, String nameCompany, ImageLogo imgLogo, ImageOtherInfo imgOtherInfo,
			Employee employee) {
		super();
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.facebook = facebook;
		this.google = google;
		this.tweeter = tweeter;
		this.introduce = introduce;
		this.nameCompany = nameCompany;
		this.imgLogo = imgLogo;
		this.imgOtherInfo = imgOtherInfo;
		this.employee = employee;
	}

	public Long getIdOtherInfo() {
		return idOtherInfo;
	}

	public void setIdOtherInfo(Long idOtherInfo) {
		this.idOtherInfo = idOtherInfo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFacebook() {
		return facebook;
	}

	public void setFacebook(String facebook) {
		this.facebook = facebook;
	}

	public String getGoogle() {
		return google;
	}

	public void setGoogle(String google) {
		this.google = google;
	}
	
	public String getTweeter() {
		return tweeter;
	}

	public void setTweeter(String tweeter) {
		this.tweeter = tweeter;
	}
	
	public String getIntroduce() {
		return introduce;
	}

	public void setIntroduce(String introduce) {
		this.introduce = introduce;
	}

	public String getNameCompany() {
		return nameCompany;
	}

	public void setNameCompany(String nameCompany) {
		this.nameCompany = nameCompany;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	public ImageLogo getImgLogo() {
		return imgLogo;
	}

	public void setImgLogo(ImageLogo imgLogo) {
		this.imgLogo = imgLogo;
	}

	public ImageOtherInfo getImgOtherInfo() {
		return imgOtherInfo;
	}

	public void setImgOtherInfo(ImageOtherInfo imgOtherInfo) {
		this.imgOtherInfo = imgOtherInfo;
	}
}
