package com.contentCrafters.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.contentCrafters.entities.Reply;

@Repository
public interface ReplyRepo extends JpaRepository<Reply, Integer> {

	public Reply findById(int id);
}
