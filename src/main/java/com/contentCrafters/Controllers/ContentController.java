package com.contentCrafters.Controllers;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.contentCrafters.Repository.ContentRepo;
import com.contentCrafters.Repository.UserRepo;
import com.contentCrafters.entities.Content;
import com.contentCrafters.entities.User;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/contentCrafters")
public class ContentController {

	@Autowired
	private ContentRepo contentRepo;

	@Autowired
	private UserRepo userRepo;
	@Autowired
	private ServletContext context;

	// create content
	@PostMapping(path = "/contents")
	public String createContent(Model model, @RequestParam("title") String title,
			@RequestParam("content") String content,
			@RequestParam(value = "imageUrl", defaultValue = "") String imageUrl, HttpServletRequest req) {
		try {
			HttpSession s = req.getSession(false);
			Content contentObj = new Content();
			contentObj.setTitle(title);
			contentObj.setContent(content);
			contentObj.setFile(imageUrl);

			if (s.isNew())
				return "redirect:loginPage";

			contentObj.setUser((User) s.getAttribute("loginedUser"));
			contentObj.setDate_created(new Date());
			contentObj.setDislike(0);
			contentObj.setLike(0);

			Content c = contentRepo.save(contentObj);
			return "redirect:home";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return "redirect:create";
		}
	}

	// get all content
	@RequestMapping("/contents")
	public String getAllContent(Model model) {
		try {
			List<Content> list = contentRepo.findAll();
			for(int i=0;i<list.size();) {
				Content c=list.get(i);
				if(c.getUser()!=null && c.getUser().getType()!=null && c.getUser().getType()==0)list.remove(i);
				else i++;
			}
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			model.addAttribute("filters", filters);
			return "ListContentPage";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return "Home";
		}
	}

	@RequestMapping("/filter-contents")
	public String getAllContent(Model model, @RequestParam(name ="title", defaultValue = "") String title,
			@RequestParam(name="creator", defaultValue = "") String creator, @RequestParam(name="date") String date) {
		try {
			
			Date date1 = new Date();
			if (date != "") {
				date1 = new SimpleDateFormat("yyyy-mm-dd").parse(date);
			}
			List<User> user=new ArrayList<>();
			if(creator!="") user=userRepo.findByName( creator);
			
			List<Content> list1=new ArrayList<>();
			if(user.size()!=0)  
				for(User u:user)
				list1.addAll(contentRepo.findByUser(u));
			List<Content> list2=new ArrayList<>();
			
			
			List<Content> list3=new ArrayList<>();
			if(title!="")list3= contentRepo.findByTitle(title);

			
			List <Content> list=new ArrayList<>();
			list.addAll(list1);
			for(Content c: list2)if(!list.contains(c))list.add(c);
			
			for(Content c: list3)if(!list.contains(c))list.add(c);
//			for (int i = 0; i < list.size();) {
//
//				if (title != "" && list.get(i).getTitle() != null) {
//					Pattern pattern1 = Pattern.compile(title, Pattern.CASE_INSENSITIVE);
//					Matcher matcher1 = pattern1.matcher(list.get(i).getTitle());
//					boolean matchFound1 = matcher1.find();
//					if (!matchFound1)
//						list.remove(i);
//				} else if (creator != "" && list.get(i).getUser() != null && list.get(i).getUser().getName() != null) {
//					Pattern pattern2 = Pattern.compile(creator, Pattern.CASE_INSENSITIVE);
//					Matcher matcher2 = pattern2.matcher(list.get(i).getUser().getName());
//					boolean matchFound2 = matcher2.find();
//					if (!matchFound2)
//						list.remove(i);
//				} else if (date != "" && date1.compareTo(list.get(i).getDate_created()) != 0) {
//					list.remove(i);
//				} else
//					i++;
//			}
//			
			
			
			System.out.println(list);
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			if (title != "")
				filters.add(title);
			if (creator != "")
				filters.add(creator);
			if (date != "")
				filters.add(date);
			model.addAttribute("filters", filters);

			return "ListContentPage";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return "Home";
		}
	}

	// get by user id
	@GetMapping("/my-contents")
	public String getContentById(Model model, HttpServletRequest req) {
		try {

			HttpSession s = req.getSession(false);

			User u = (User) s.getAttribute("loginedUser");
			List<Content> list = contentRepo.findByUser(u);
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			model.addAttribute("filters", filters);
			model.addAttribute("canBeDeleted", 1);
			return "ListContentPage";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			return "Home";
		}
	}

	// update
	@PutMapping("/contents/{c_id}")
	public ResponseEntity<Content> UpdateContent(Model model, @PathVariable("c_id") int c_id,
			@RequestBody Content content) {
		try {
			content.setC_id(c_id);
			Content c = contentRepo.save(content);
			return ResponseEntity.ok(c);
		} catch (Exception e) {
			// TODO: handle exception

			System.out.println(e.getLocalizedMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}
	}

	// delete
	@RequestMapping("/delete-contents/{c_id}")
	public String deleteContent(Model model, @PathVariable("c_id") int c_id, HttpServletRequest req) {
		try {
			contentRepo.deleteById(c_id);
			HttpSession s = req.getSession(false);

			User u = (User) s.getAttribute("loginedUser");
			List<Content> list = contentRepo.findByUser(u);
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			model.addAttribute("filters", filters);
			model.addAttribute("canBeDeleted", 1);
			return "ListContentPage";
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getLocalizedMessage());
			HttpSession s = req.getSession(false);

			User u = (User) s.getAttribute("loginedUser");
			List<Content> list = contentRepo.findByUser(u);
			model.addAttribute("contents", list);
			model.addAttribute("title", "Contents");
			List<String> filters = new ArrayList<>();
			filters.add("all contents");
			model.addAttribute("filters", filters);
			model.addAttribute("canBeDeleted", 1);
			return "ListContentPage";
		}
	}

	@RequestMapping("/like/{cId}")
	public String Like(@PathVariable("cId") int cId, HttpServletRequest req, Model model) {

		try {
			Content c = contentRepo.findById(cId);

			if (c.getLike() == null)
				c.setLike(1);
			else
				c.setLike(c.getLike() + 1);
			contentRepo.save(c);
			System.out.println("path" + context.getContextPath());

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
			return "Home";
		}

	}

	@RequestMapping("/dislike/{cId}")
	public String Disike(@PathVariable("cId") int cId, HttpServletRequest req, Model model) {

		try {
			Content c = contentRepo.findById(cId);

			if (c.getDislike() == null)
				c.setDislike(1);
			else
				c.setDislike(c.getDislike() + 1);
			contentRepo.save(c);
			System.out.println("path" + context.getContextPath());

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
			return "Home";
		}

	}

}
