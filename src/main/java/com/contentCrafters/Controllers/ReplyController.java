package com.contentCrafters.Controllers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.contentCrafters.Repository.ContentRepo;
import com.contentCrafters.Repository.ReplyRepo;
import com.contentCrafters.entities.Content;
import com.contentCrafters.entities.Reply;
import com.contentCrafters.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contentCrafters")
public class ReplyController {

	@Autowired
	private ReplyRepo replyRepo;
	@Autowired
	private ContentRepo contentRepo;

	
	//create reply
	@PostMapping("/replys/{cId}")
	public String createReply(Model model,@PathVariable("cId") int cId,
			@RequestParam String reply, HttpServletRequest req){
		try {
			Content c=contentRepo.findById(cId);
			Reply r=new Reply();
			r.setDesc(reply);
			r.setContent(c);
			HttpSession s=req.getSession(false);
			if(!s.isNew()) {
				r.setUser((User)s.getAttribute("loginedUser"));
				r.setDate_created(new Date());
			}
			
			replyRepo.save(r);
			
			List<Content> list = contentRepo.findAll();
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			model.addAttribute("filters", filters);
			return "ListContentPage";
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return "redirect: contents";
		}
	}
	
	
	//get all replys
	@GetMapping("replys")
	public ResponseEntity<List<Reply>> getAllReplys(Model model){
		try {
			List<Reply> list=replyRepo.findAll();
			if(list.size()==0) return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
			return ResponseEntity.ok(list);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//update 
	@PutMapping("/replys/{r_id}")
	public ResponseEntity<Reply> updateReply(Model mode, @PathVariable("r_id") int r_id, 
			@RequestBody Reply reply){
		try {
			reply.setId(r_id);
			Reply r=replyRepo.save(reply);
			return ResponseEntity.ok(r);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	@DeleteMapping("/replys/{r_id}")
	public ResponseEntity<String> deleteReply(Model model, @PathVariable("r_id")int r_id){
		try {
			replyRepo.deleteById(r_id);
			return ResponseEntity.ok("deleted successfully");
			
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
}
