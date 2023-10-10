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
@Table(name="_user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private String name;
	@Column(unique = true)
	private String email;
	private String password;
	private String occupation;
	private String highest_degree;
	private String institute_Company;
	//private 0 , public 1
	@Column(name="type")
	private Integer type;
	private String profile_photo;
	@OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
	private List<Content> content;
	private Date date_created;
	
	@OneToMany(cascade = CascadeType.REMOVE, mappedBy = "user", fetch = FetchType.LAZY)
	private List<Reply> Reply;
	
	
	public User(int id, String name, String email, String password, String occupation, String highest_degree,
			String intitute_company,Integer profile_type, String profile_photo, Date date_created, List<Reply> list) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.occupation = occupation;
		this.highest_degree = highest_degree;
		this.institute_Company=intitute_company;
		this.type = profile_type;
		this.profile_photo = profile_photo;
		this.date_created = date_created;
		this.Reply=list;
	}
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getOccupation() {
		return occupation;
	}
	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}
	public String getHighest_degree() {
		return highest_degree;
	}
	public void setHighest_degree(String highest_degree) {
		this.highest_degree = highest_degree;
	}
	
	public String getInstitute_Company() {
		return institute_Company;
	}
	public void setInstitute_Company(String institute_Company) {
		this.institute_Company = institute_Company;
	}
	public Integer getProfile_type() {
		return type;
	}
	
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public String getProfile_photo() {
		return profile_photo;
	}
	public void setProfile_photo(String profile_photo) {
		this.profile_photo = profile_photo;
	}
	public Date getDate_created() {
		return date_created;
	}
	public void setDate_created(Date date_created) {
		this.date_created = date_created;
	}
	
	
	public List<Content> getContent() {
		return content;
	}
	public void setContent(List<Content> content) {
		this.content = content;
	}
	public List<Reply> getReply() {
		return Reply;
	}
	public void setReply(List<Reply> reply) {
		Reply = reply;
	}
	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", email=" + email + ", password=" + password + ", occupation="
				+ occupation + ", highest_degree=" + highest_degree + ", institute_Company=" + institute_Company
				+ ", profile_type=" + type + ", profile_photo=" + profile_photo + ", date_created="
				+ date_created + "]";
	}
	
	
	
	
}
