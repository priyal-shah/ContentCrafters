package com.contentCrafters.Controllers;

import java.net.http.HttpResponse;

import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.contentCrafters.entities.User;

import jakarta.servlet.http.HttpServletResponse;



@Controller
@RequestMapping("/contentCrafters")
public class HomeContollers {

	@Autowired
	private UserController userController;
	
	@RequestMapping("/home")
	public String getHomePage( Model model) {
		model.addAttribute("title", "home");
		return "Home";
	}
	
	@RequestMapping("/loginPage")
	public String LoginPage(Model model) {
		model.addAttribute("title", "Login Page");
		return "Login";
	}
	
	@RequestMapping("/registerPage")
	public String RegisterPage(Model model) {
		model.addAttribute("title", "Register");
		return "SignUp";
	}
	
	
	@RequestMapping("/create")
	public String create(Model model) {
		model.addAttribute("title", "Create");
		return "CreateContent";
	}
	
	
	
	
	
	
	
	
	
	
}
