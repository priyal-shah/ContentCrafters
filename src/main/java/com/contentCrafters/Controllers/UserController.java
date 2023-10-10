package com.contentCrafters.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.contentCrafters.Repository.UserRepo;
import com.contentCrafters.entities.User;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;


@Controller
@RequestMapping("/contentCrafters")
public class UserController {
	
	@Autowired
	private UserRepo userRepo;

	
	//get all users
	@GetMapping("/users")
	public ModelAndView getAllUsers(ModelMap model){
		try {
			List<User> users=userRepo.findAll();
			if(users.size()==0) {model.addAttribute("errorMsg", "No user found");
			return new ModelAndView("",model);
			}
			model.addAttribute("users", users);
			return new ModelAndView("", model);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			model.addAttribute("errorMsg", "Something went wrong");
			return new ModelAndView("", model);
		}
		
	}
	
	
	//get by id
	@GetMapping("/users/{user_id}")
	public ResponseEntity<User> getUserById(
			@PathVariable("user_id") int user_id){
		try {
			User user=userRepo.findById(user_id);
			if(user==null) return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			return ResponseEntity.ok(user);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}
	
	
	//create user
	@PostMapping("/users")
	public String createUser( @ModelAttribute User user,
			@RequestParam(value = "agreement" , defaultValue = "false") boolean agreement,
			Model model, HttpServletRequest req){
		try {
			
			
			System.out.println(user);
			userRepo.save(user);
			model.addAttribute("successMsg", "Registered");
			return "redirect:home";
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			model.addAttribute("errorMsg", "Something went wrong");
			
			return "redirect:registerPage";
		}
	}
	
	@PutMapping("/users/{user_id}")
	public ResponseEntity<User> updateUser(@PathVariable("user_id") int user_id, @RequestBody User user){
		
		try {
			User User=userRepo.findById(user_id);
			User updatedUser=userRepo.save(user);
			return ResponseEntity.ok(updatedUser);
		} catch (Exception e) {
			// TODO: handle exception
			
			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
			
		}
	}
	
	
	//login function
	
	@GetMapping("/login")
	public ModelAndView Login(ModelMap model, @ModelAttribute User user, HttpServletRequest req) {
		try {
			if(user.getEmail()==null || user.getPassword()==null) {
				model.addAttribute("errorMsg", "email or password should not be blank");
				return new ModelAndView("redirect:loginPage", model);
			}
			
			List <User> list=userRepo.findByEmailAndPassword(user.getEmail(), user.getPassword());
			
			if(list.size()<=0) {
				model.addAttribute("errorMsg", "invalid email or password");
				return new ModelAndView("redirect:loginPage");
			}
			
			HttpSession s=req.getSession();
			s.setAttribute("loginedUser", list.get(0));
			return new ModelAndView("redirect:home", model);
			
			
		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
			model.addAttribute("errorMsg", "Someting went wrong");
			return new ModelAndView("redirect:loginPage", model);
		}
	}
	
	
	//logout
	
	@RequestMapping("/logout")
	public String Logout(HttpServletRequest req) {
		HttpSession s=req.getSession();
		s.removeAttribute("loginedUser");
		return "redirect:home";
	}
	
}
