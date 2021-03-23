
package com.IOC.CSVBatchReaderApplication.Listener;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import com.IOC.CSVBatchReaderApplication.Model.CSVBatchReaderReserveScreen;

@Component
public class JobCompletionListenerReserveScreen implements JobExecutionListener
{
	@Autowired
	public JdbcTemplate jdbcTemplate;
	@Override
	public void beforeJob(JobExecution jobExecution)
	{
		System.out.println("Executing job id " + jobExecution.getId());
		System.out.println("before insertion = "+System.currentTimeMillis());
	}

	@Override
	public void afterJob(JobExecution jobExecution) 
	
	{
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) 
		{
	        List<CSVBatchReaderReserveScreen> result = jdbcTemplate.query("SELECT report_id,location_Type,location_Size_Type,location_Area,item_Code,is_Parent,alternative_Item_Code,item_Description,batch_Nbr,expiry_Date,"
	        		+ "current_Qty,allocated_Qty,current_LPN,alloc_LPN,last_CC_Counted,mod_TimeStamp,to_be_Counted,to_Be_Counted_TS,"
	        		+ "loc_Lock,location_Barcode,facility,display_Text,mod_User,loc_CC_User,loc_Max_LPN,loc_Max_Unit,min_Unit,attribute_A,"
	        		+ "attribute_B,attribute_C,attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,attribute_J,"
	        		+ "attribute_K,attribute_L,attribute_M,attribute_N,attribute_O,brand_Code,batch_Nbr_Lock,company,lock_Code FROM BI_RESERVE_SCREEN_REPORT", 
	        		
	        		
	        		new RowMapper<CSVBatchReaderReserveScreen>()
	        		
	        	{		
	            @Override
	            public CSVBatchReaderReserveScreen mapRow(ResultSet rs, int row) throws SQLException
	            {
	                return new CSVBatchReaderReserveScreen(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
	                		rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10),
	                		rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15),
	                		rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20),
	                		rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25),
	                		rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30),
	                		rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34), rs.getString(35),
	                		rs.getString(36), rs.getString(37), rs.getString(38), rs.getString(39), rs.getString(40),
	                		rs.getString(41), rs.getString(42), rs.getString(43), rs.getString(44), rs.getString(45), rs.getString(46));
	            }
	        });
	        
	        System.out.println("Number of Records:"+result.size());
	        System.out.println("After insertion = "+System.currentTimeMillis());
		}
	}
	
	
}

