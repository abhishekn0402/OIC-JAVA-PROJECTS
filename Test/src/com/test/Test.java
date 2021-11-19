package com.test;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

class Test
{
	
	

	public static void main(String[] args) 
	{
		LocalDate today = LocalDate.now();
		String yesterday = (today.minusDays(1)).format(DateTimeFormatter.ISO_DATE);
		String dayBeoforeTwoDays = (today.minusDays(2)).format(DateTimeFormatter.ISO_DATE);
		System.out.println("today : "+today);
		System.out.println("yesterday : "+yesterday);
		System.out.println("dayBeoforeTwoDays : "+dayBeoforeTwoDays);
		
	}	
}