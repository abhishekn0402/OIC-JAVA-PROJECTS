package com.IOC.CSVBatchReaderApplication.Scheduler;
import javax.batch.operations.JobRestartException;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class BatchJobScheduler
{
	
	@Autowired
	private JobLauncher jobLauncher;
	
	@Autowired
	@Qualifier("createMarkSheetJobReserveScreen")
	private Job jobReserveScreen;
	
	@Autowired
	@Qualifier("createMarkSheetJobActiveScreen")
	private Job jobActiveScreen;
	
	

	@Scheduled(fixedDelay = 1500000)
	public void runBatchJobReserveScreen()
	
	{
	        JobParameters params = new JobParametersBuilder()
	                .addLong("jobId", System.currentTimeMillis())
	                .toJobParameters();
		try {
			try {
				jobLauncher.run(jobReserveScreen, params);
			} catch (org.springframework.batch.core.repository.JobRestartException e) {
				
				e.printStackTrace();
			}
		
                } catch (JobExecutionAlreadyRunningException e) {
				e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) 
		{
			e.printStackTrace();
		}
	}
	@Scheduled(fixedDelay = 1500000)
	public void runBatchJobActiveScreen()
	{
	        JobParameters params = new JobParametersBuilder()
	                .addLong("jobId", System.currentTimeMillis())
	                .toJobParameters();
		try {
			try {
				jobLauncher.run(jobActiveScreen, params);
			} catch (org.springframework.batch.core.repository.JobRestartException e) {
				
				e.printStackTrace();
			}
		
                } catch (JobExecutionAlreadyRunningException e) {
				e.printStackTrace();
		} catch (JobRestartException e) {
			e.printStackTrace();
		} catch (JobInstanceAlreadyCompleteException e) {
			e.printStackTrace();
		} catch (JobParametersInvalidException e) 
		{
			e.printStackTrace();
		}
	}	
}

