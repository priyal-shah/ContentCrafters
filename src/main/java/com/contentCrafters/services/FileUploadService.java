package com.contentCrafters.services;

import java.io.IOException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class FileUploadService {

	private final Path root=Paths.get("contentFiles");
	
	  public void init() {
	    try {
	    	
	    	System.out.println(root.toString());
	      Files.createDirectories(root);
	    } catch (Exception e) {
	      throw new RuntimeException("Could not initialize folder for upload!");
	    }
	  }
	  
	  public void save(MultipartFile file) {
		    try {
		    	System.out.println(root.toString());
		    	Files.createDirectories(root);
		      Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
		    } catch (Exception e) {
		      if (e instanceof FileAlreadyExistsException) {
		        throw new RuntimeException("A file of that name already exists.");
		      }

		      throw new RuntimeException(e.getMessage());
		    }
		  }
	
}
