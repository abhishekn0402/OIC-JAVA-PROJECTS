package com.ImageHandler.Project.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.ImageHandler.Project.DTO.Image;
import com.ImageHandler.Project.Services.ImageService;

@RestController
public class ImageController
{
	
	@Autowired
	private ImageService imageService;
	@RequestMapping("/a")
	public String showLoginPage()
	{
		return "NewFile";
		
	}
	@GetMapping("/getImage")
	public ResponseEntity<List<Image>> getImages()
	{
		return new ResponseEntity<List<Image>>(imageService.getAllImage(),HttpStatus.FOUND);

	}
	@RequestMapping(method = RequestMethod.POST, value = "/addImage")
	public  ResponseEntity<String> addImage(@RequestBody Image image)
	{
		
		imageService.addImage(image);	
		return new ResponseEntity<String>("image added successfully", HttpStatus.ACCEPTED);	
	}
}
