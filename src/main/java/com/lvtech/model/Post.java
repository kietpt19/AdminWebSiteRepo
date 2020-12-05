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
public class Post {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long idPost;
	@Column(nullable = false, columnDefinition="TEXT")
	private String titlePost;
	@Column(nullable = false, columnDefinition="TEXT")
	private String mainContent;
	@Column(nullable = false)
	private LocalDateTime postDate = java.time.LocalDateTime.now().plusHours(7);
	@Lob
	@Column(name = "content", columnDefinition="MEDIUMTEXT")
	private String content;
	
	@ManyToOne
	@JoinColumn
	private Subject subject;
	
	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "img_post")
	private ImagePost img;
	
	@ManyToOne
	@JoinColumn
	private Employee employee;

	public Post() {
		super();
	}

	public Post(String titlePost, String mainContent, LocalDateTime postDate, String content) {
		super();
		this.titlePost = titlePost;
		this.mainContent = mainContent;
		this.postDate = postDate;
		this.content = content;
	}

	public String getMainContent() {
		return mainContent;
	}

	public void setMainContent(String mainContent) {
		this.mainContent = mainContent;
	}

	public Long getIdPost() {
		return idPost;
	}

	public void setIdPost(Long idPost) {
		this.idPost = idPost;
	}

	public String getTitlePost() {
		return titlePost;
	}

	public void setTitlePost(String titlePost) {
		this.titlePost = titlePost;
	}

	public LocalDateTime getPostDate() {
		return postDate;
	}

	public void setPostDate(LocalDateTime postDate) {
		this.postDate = postDate;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Subject getSubject() {
		return subject;
	}

	public void setSubject(Subject subject) {
		this.subject = subject;
	}

	public ImagePost getImg() {
		return img;
	}

	public void setImg(ImagePost img) {
		this.img = img;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
}
