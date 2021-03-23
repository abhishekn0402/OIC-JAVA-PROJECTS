package com.CsvFileReader.IOC.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.CsvFileReader.IOC.helper.CSVHelper;
import com.CsvFileReader.IOC.message.ResponseMessage;
import com.CsvFileReader.IOC.service.CSVService;

import ch.qos.logback.core.util.ContentTypeUtil;

//@CrossOrigin("http://localhost:8080")
@Controller
@RequestMapping("/api/csv")
public class CSVController
{
	@Autowired
	CSVService fileService;
	
	@GetMapping("/ping")
	public String sayHello()
	{
		return "Hello User! I'm up and running";
	}
	
	@GetMapping("/upload")
	public ResponseEntity<ResponseMessage> uploadFile() throws IOException //@RequestParam("file") MultipartFile file
	{
		//reading file from local
		File local_file= new File("C:\\Users\\Abhilash Pramod\\Postman\\files\\reserve_screen_report.csv");//reserve_screen_report.csv
		FileInputStream fileInputStream=new FileInputStream(local_file);

		//converting the inputstream file into MultipartFile file
		MultipartFile multipartFile = new MockMultipartFile(local_file.getName(), local_file.getName(),ContentTypeUtil.getSubType("text/csv"), fileInputStream);
		System.out.println(multipartFile.getContentType());
		System.out.println(multipartFile.getSize());
		String message = "";
		

		//checking the type of file if csv then true
		if (CSVHelper.hasCSVFormat(multipartFile)) 
		{
			//for testing purpose
			System.out.println("True");
			try{
				fileService.save(multipartFile);

				message = "Uploaded the file successfully: " + multipartFile.getOriginalFilename();
				return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
			} 
			catch (Exception e)
			{
				e.printStackTrace();
				message = "Could not upload the file: " + multipartFile.getOriginalFilename() + "!";
				return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
			}
		}

		message = "Please upload a csv file!";
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseMessage(message));
	}

}
