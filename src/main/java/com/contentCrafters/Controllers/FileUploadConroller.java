package com.contentCrafters.Controllers;

import java.io.File;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.contentCrafters.services.FileUploadService;

@RestController
@RequestMapping("/contentCrafters")
public class FileUploadConroller {

	
	@Autowired
	private FileUploadService fileUploadService; 
	
	
	@PostMapping("/upload") 
	  public ResponseEntity<?> handleFileUpload( @RequestBody MultipartFile file, BindingResult bindingREsult ) {

	    String fileName = file.getOriginalFilename();
	    System.out.println(fileName);
	    try {
	      fileUploadService.save(file);
	    } catch (Exception e) {
	      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
	    } 
	    return ResponseEntity.ok("File uploaded successfully.");
	  }
	
}
