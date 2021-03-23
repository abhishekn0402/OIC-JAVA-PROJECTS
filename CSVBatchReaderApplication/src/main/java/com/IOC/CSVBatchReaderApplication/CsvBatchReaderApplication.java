package com.IOC.CSVBatchReaderApplication;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
@SpringBootApplication (exclude = {DataSourceAutoConfiguration.class })
public class CsvBatchReaderApplication
{
	public static void main(String[] args)
	{	
		SpringApplication.run(CsvBatchReaderApplication.class, args);
	}
}
