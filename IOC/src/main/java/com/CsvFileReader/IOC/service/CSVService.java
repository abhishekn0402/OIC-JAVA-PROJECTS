package com.CsvFileReader.IOC.service;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.CsvFileReader.IOC.helper.CSVHelper;
import com.CsvFileReader.IOC.model.CsvInserter;
import com.CsvFileReader.IOC.repository.CSVRepository;
@Service
public class CSVService
{
	@Autowired
	CSVRepository repository;

	public void save(MultipartFile multipartFile)
	{
		try {
			List<CsvInserter> csvDataBaseInserter = CSVHelper.csvInserterModel(multipartFile.getInputStream());
			//saving in the database
			System.out.println("Total Size ::"+csvDataBaseInserter.size());
			
			
			
			
			//for testing printing item code in each row
			for (int i = 0; i < csvDataBaseInserter.size(); i++)
			{
				System.out.println(csvDataBaseInserter.get(i).getItem_Code());
			}
			
			
			System.out.println("Before Insert ::"+System.currentTimeMillis());
			repository.saveAll(csvDataBaseInserter);
			System.out.println("After Insert ::"+System.currentTimeMillis());
		   } 
		
		catch (IOException e)
		   {
			throw new RuntimeException("fail to store csv data: " + e.getMessage());
		   }
	}  
}
