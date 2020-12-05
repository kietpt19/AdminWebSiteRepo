package com.lvtech.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;

@Entity
public class Guest {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idGuest;
	@Column(length = 200, nullable = false)
	private String fullName;
	@Column(nullable = false)
	private String phoneGuest;
	@Column(nullable = false)
	private String emailGuest;
	@Lob
	@Column(name = "studyTime", columnDefinition="TEXT")
	private String studyTime;
	@Lob
	@Column(name = "note", columnDefinition="TEXT")
	private String note;
	
	
	
	public Guest() {
		super();
	}

	public Guest(String fullName, String phoneGuest, String emailGuest, String studyTime) {
		super();
		this.fullName = fullName;
		this.phoneGuest = phoneGuest;
		this.emailGuest = emailGuest;
		this.studyTime = studyTime;
	}

	public Long getIdGuest() {
		return idGuest;
	}

	public void setIdGuest(Long idGuest) {
		this.idGuest = idGuest;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getPhoneGuest() {
		return phoneGuest;
	}

	public void setPhoneGuest(String phoneGuest) {
		this.phoneGuest = phoneGuest;
	}

	public String getEmailGuest() {
		return emailGuest;
	}

	public void setEmailGuest(String emailGuest) {
		this.emailGuest = emailGuest;
	}

	public String getStudyTime() {
		return studyTime;
	}

	public void setStudyTime(String studyTime) {
		this.studyTime = studyTime;
	}
}
