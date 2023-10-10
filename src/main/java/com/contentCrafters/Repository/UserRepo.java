package com.contentCrafters.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contentCrafters.entities.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	public User findById(int id);
	
	public List<User> findByEmailAndPassword(String email, String password);
	
	public List<User> findByName(String name);
}
