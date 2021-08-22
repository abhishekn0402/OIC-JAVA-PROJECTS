package com.ito.bi.reports.integration.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class NotificationListener extends JobExecutionListenerSupport{

	@Value("${spring.datasource.url}")
	private String dburl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;
	
	public NotificationListener() 
    {
		
    }
    @Override
    public void afterJob(final JobExecution jobExecution) 
    {
    	JobParameters params  = jobExecution.getJobParameters();
    	if(params !=null)
    	{
    		String activeJob = params.getString("ACTIVE:");
    		String reserveJob = params.getString("RESERVE:");
    		String ib_shipment_Job = params.getString("IB_SHIPMENT:");
    		String order_Details_Job = params.getString("ORDER_DETAILS:");
    		String iblpn_Details_Job = params.getString("IBLPN_DETAILS:");
    		String iblpn_Reports_Job = params.getString("IBLPN_REPORTS:");
    		if(activeJob != null && activeJob.length() > 0)
    		{
    			updateReportId("update BI_REP_ACTIVE_SCREEN set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
    		else if (reserveJob != null && reserveJob.length() > 0)
    		{
    			updateReportId("update BI_REP_RESERVE_SCREEN set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
 
    		else if (ib_shipment_Job != null && ib_shipment_Job.length() > 0)
    		{
    			updateReportId("update BI_REP_IB_SHIPMENT set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
    		else if (order_Details_Job != null && order_Details_Job.length() > 0)
    		{
    			updateReportId("update BI_REP_ORDER_DETAILS set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
    		else if (iblpn_Details_Job != null && iblpn_Details_Job.length() > 0)
    		{
    			updateReportId("update BI_REP_IBLPN_DETAILS set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
    		else if (iblpn_Reports_Job != null && iblpn_Reports_Job.length() > 0)
    		{
    			updateReportId("update BI_REP_IBLPN_REPORT set report_id = "+jobExecution.getJobId()+" where report_id=0");
    		}
    	}
    }

	private void updateReportId(String updateQry) 
	{
		Connection conn = null;
		Statement statement = null;
		try
		{
			conn = DriverManager.getConnection(dburl, username, password);
			statement = conn.createStatement();	
			statement.executeUpdate(updateQry);
		}
		catch (SQLException e) 
		{
			e.printStackTrace();
		}
		finally 
		{	
			try
			{
				if(statement!=null) statement.close();	
				if(conn !=null) conn.close();
			}catch (Exception e) 
			{
				e.printStackTrace();
			}
		}
	} 
}
