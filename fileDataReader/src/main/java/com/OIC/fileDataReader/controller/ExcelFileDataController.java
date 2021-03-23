package com.OIC.fileDataReader.controller;

import java.io.IOException;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.OIC.fileDataReader.dto.ExcelFileDataInserter;
import com.OIC.fileDataReader.service.ExcelFileDataService;

@RestController
public class ExcelFileDataController
{
	@Autowired
	private ExcelFileDataService excelFileDataService;
	@GetMapping("/addexcel")
	public ResponseEntity<String> addExcelData() throws SQLException, IOException
	{
		excelFileDataService.addExcelData();
		return new ResponseEntity<String>("excel added..",HttpStatus.ACCEPTED);
	}
}
