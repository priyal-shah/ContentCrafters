package com.contentCrafters.entities;

import java.util.Date;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="content")
public class Content {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int c_id;
	@ManyToOne
	private User user;
	private String title;
	@Column(length = 1000)
	private String content;
	private String file;
	private Integer like;
	private Integer dislike;
	private Date date_created;
	@OneToMany(mappedBy = "content", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Reply> reply;
	
	public int getC_id() {
		return c_id;
	}
	public void setC_id(int c_id) {
		this.c_id = c_id;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getFile() {
		return file;
	}
	public void setFile(String file) {
		this.file = file;
	}
	public Integer getLike() {
		return like;
	}
	public void setLike(Integer like) {
		this.like = like;
	}
	public Integer getDislike() {
		return dislike;
	}
	public void setDislike(Integer dislike) {
		this.dislike = dislike;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	public List<Reply> getReply() {
		return reply;
	}
	public void setReply(List<Reply> reply) {
		this.reply = reply;
	}
	public Content(int c_id, User user, String title, String content, String file, Integer like, Integer dislike,
			Date date_created) {
		super();
		this.c_id = c_id;
		this.user = user;
		this.title = title;
		this.content = content;
		this.file = file;
		this.like = like;
		this.dislike = dislike;
		this.date_created = date_created;
	}
	public Content() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	
}
