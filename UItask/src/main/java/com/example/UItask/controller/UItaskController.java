package com.example.UItask.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;
import java.util.zip.DataFormatException;
import java.util.zip.Inflater;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.example.UItask.DTO.User;
import com.example.UItask.service.UserService;

@Controller
public class UItaskController
{
	@Autowired
	private UserService userService;
	@RequestMapping("/home")
	public String homePage(Model model)
	{
		model.addAttribute("name", "Abhi");
		//ModelAndView modelAndView=new ModelAndView();
		return "home";
		//return new ModelAndView("home");
	}
	@GetMapping("/register")//register--to print register form
	public String showForm(Model model1)
	{
		User user1=new User();//user1 is a model attribute and its get mapped when user click on the button
		model1.addAttribute("user1", user1);
		return "register";

	}


	@PostMapping("/register") //to print success form
	public String submitForm(@ModelAttribute("user1") User user1,@RequestParam("myFinalImage") String myFinalImage,@RequestParam("username") String username,@RequestParam("password") String password, @RequestParam("file") MultipartFile file,HttpServletResponse response) throws IOException, DataFormatException
	{
        
		byte [] image=file.getBytes();
		Inflater inflater=new Inflater();
		inflater.setInput(image);
		
		 ByteArrayOutputStream outputStream = new ByteArrayOutputStream(image.length);
		String encodedImage=Base64.getEncoder().encodeToString(image);
		 byte[] buffer = new byte[1024];
		 
		 while (!inflater.finished())
		 {
			
			                 int count = inflater.inflate(buffer);
			
			                 outputStream.write(buffer, 0, count);
			
		 }
		byte [] inflatorArray=outputStream.toByteArray();
	
		byte [] decoded=Base64.getDecoder().decode(encodedImage);
		String decodedimagedata=new String(decoded);
		User user=new User(username,encodedImage,password,decodedimagedata, myFinalImage);
		user.setEncodedImage(encodedImage);
		user.setDecodedimagedata(decodedimagedata);
		System.out.println(image);
		//System.out.println("Encoded data "+encodedImage);
		//System.out.println("Decoded data"+decodedimagedata);

		
		
		

		userService.updateUser(user);
		/*
		  try {

		  byte[] image=file.getBytes();
		 // User userImage=new User(username,image,password);
		  User user=new User(image);
		  userService.updateUser(user, file);
		  response.setContentType("image/jpeg, image/jpg, image/png, image/gif"); }
		  catch (IOException e) { // TODO Auto-generated catch block
		  e.printStackTrace(); }

		 */
		return "Success";
	}

	@GetMapping("/getUsers")
	public ResponseEntity<List<User>> getAllUser()
	{
		return new ResponseEntity<List<User>> (userService.getAllUser(), HttpStatus.ACCEPTED);
	}

	
	@GetMapping("/getUser/{id}")
	public String getImageById(@ModelAttribute("user2") User user1,Model model1,@PathVariable int id)
	{
		Optional<User> userallInfo=userService.getImageById(id);
		String retrivedImage=userallInfo.get().getUsername();
		//model1.addAttribute("myFinalImage",retrivedImage);
		//model1.addAttribute("user2",user1);
	    System.out.println("UserName ="+retrivedImage);
		return retrivedImage;
		
	}
	
}
