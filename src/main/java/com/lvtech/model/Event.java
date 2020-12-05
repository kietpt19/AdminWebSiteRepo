package com.lvtech.model;

import java.time.LocalDateTime;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class Event {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idEvent;
	@Column(nullable = false, columnDefinition="TEXT")
	private String titleEvent;
	@Column(nullable = false, columnDefinition="TEXT")
	private String mainContent;
	@Column(nullable = false)
	private LocalDateTime eventDate = java.time.LocalDateTime.now().plusHours(7);
	@Lob
	@Column(name = "content", columnDefinition="MEDIUMTEXT")
	private String content;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "img_event")
	private ImageEvent img;
	
	@ManyToOne
	@JoinColumn
	private Employee employee;

	public Event() {
		super();
	}

	public Event(String titleEvent, LocalDateTime eventDate, String content, String mainContent) {
		super();
		this.titleEvent = titleEvent;
		this.eventDate = eventDate;
		this.content = content;
		this.mainContent = mainContent;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public Long getIdEvent() {
		return idEvent;
	}

	public void setIdEvent(Long idEvent) {
		this.idEvent = idEvent;
	}

	public String getTitleEvent() {
		return titleEvent;
	}

	public void setTitleEvent(String titleEvent) {
		this.titleEvent = titleEvent;
	}

	public LocalDateTime getEventDate() {
		return eventDate;
	}

	public void setEventDate(LocalDateTime eventDate) {
		this.eventDate = eventDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public ImageEvent getImg() {
		return img;
	}

	public void setImg(ImageEvent img) {
		this.img = img;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
