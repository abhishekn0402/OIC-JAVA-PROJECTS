package com.CsvFileReader.IOC;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
//@EnableScheduling
public class ScheduledApplication
{
	private static final org.slf4j.Logger log = LoggerFactory.getLogger(ScheduledApplication.class);

	private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

	@Scheduled(fixedRate = 5000)
	public void reportCurrentTime()
	{
		log.info("The time is now {}", dateFormat.format(new Date()));
	}
}
