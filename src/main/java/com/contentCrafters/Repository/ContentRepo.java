package com.contentCrafters.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.contentCrafters.entities.Content;
import com.contentCrafters.entities.User;


@Repository
public interface ContentRepo extends JpaRepository<Content, Integer> {

	public Content findById(int id);
	
	public List<Content> findByUser(User user);
	
	public List<Content> findByTitle(String title);
	
//	public List<Content> findByDate_created(Date date);
	
//	public List<Content> findByType(int type);
	
//	@Query("select u from content where u.title like % = : pattern% or u.dateCreated like % = : pattern% ")
//	public List<Content> searchContentByTitleOrDate(String pattern);
	
//	@Query("select u from content where u.title = : n")
//	public List<Content> getByTitle(@Param("n") String title);
	
}
