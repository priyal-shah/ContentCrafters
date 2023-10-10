package com.contentCrafters.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.contentCrafters.Repository.UserRepo;
import com.contentCrafters.entities.User;

@RequestMapping("/contentCrafters")
public class UserController2 {

	@Autowired
	private UserRepo userRepo;
	
	
	@PostMapping("/register")
	public ModelAndView register(ModelMap model, @ModelAttribute User user,
			@RequestParam(value = "agreement", defaultValue = "false") boolean agreement) {
		
		System.out.println("inside the controller");
		if(agreement==false) {
			model.addAttribute("errorMsg", "please agree the terms and condition");
			return new ModelAndView("registerPage", model);
		}
			
		try {
			userRepo.save(user);
			model.addAttribute("successMsg", "Registered SuccessFully");
			return new ModelAndView("regiterPage", model);
		} catch (Exception e) {
			// TODO: handle exception
			model.addAttribute("errorMsg", "Something went wrong");
			return new ModelAndView("registerPage", model);
		}
		
		
	}
}
