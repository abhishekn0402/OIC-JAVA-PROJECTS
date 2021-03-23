package com.ito.bi.reports.integration.config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import com.ito.bi.reports.integration.model.ActiveScreenReport;
import com.ito.bi.reports.integration.model.ReserveScreenReport;
@Configuration
@EnableBatchProcessing
public class BatchConfig extends DefaultBatchConfigurer
{
	@Autowired
	private DataSource dataSource;

	@Autowired
	private JobBuilderFactory jobBuilderFactory;

	@Autowired
	private StepBuilderFactory stepBuilderFactory; 

	@Value("${spring.datasource.url}")
	private String dburl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.query}")
	private String query;

	@Value("file:${reserve.input.file}")
	private Resource reserve_Resource;

	@Value("file:${active.input.file}")
	private Resource active_Resource;

	public static Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	
	@Bean(name = "reserveJob")
	public Job reserveJob() throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("reserveJob")
				.incrementer(new RunIdIncrementer())
				.start(reserveStep())
				.build();
	}   
	@Bean(name = "activeJob")
	public Job activeJob() throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("activeJob")
				.incrementer(new RunIdIncrementer())
				.start(activeStep())
				.build();
	}
	
	@Bean
	public Step reserveStep() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("reserveStep")
				.<ReserveScreenReport, ReserveScreenReport>chunk(5000)
				.reader(reserveReader())
				.writer(reserveWriter())
				.build();
	}
	
	@Bean
	public Step activeStep() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("activeStep")
				.<ActiveScreenReport, ActiveScreenReport>chunk(30000)
				.reader(activeReader())
				.writer(activeWriter())
				.build();
	}
	
	@Bean
	public FlatFileItemReader<ActiveScreenReport> activeReader() throws FileUploadException {
		
			FlatFileItemReader<ActiveScreenReport> itemReader = new FlatFileItemReader<ActiveScreenReport>();
			itemReader.setLineMapper(activeLineMapper());
			itemReader.setLinesToSkip(1);
			itemReader.setResource(active_Resource);
			return itemReader;
	}
	
	@Bean
	public FlatFileItemReader<ReserveScreenReport> reserveReader() throws FileUploadException {
		FlatFileItemReader<ReserveScreenReport> itemReader = new FlatFileItemReader<ReserveScreenReport>();
		itemReader.setLineMapper(reserveLineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(reserve_Resource);
		return itemReader;
	}

	@Bean
	public LineMapper<ActiveScreenReport> activeLineMapper() {
		DefaultLineMapper<ActiveScreenReport> lineMapper = new DefaultLineMapper<ActiveScreenReport>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						"facility","location","location_Barcode","location_Type","location_Size_Type","area", "item_Assignment_Type",
						"hierarchy_Code_3", "item_Code","is_Parent","alternative_Item_Code","item_Description","current_Qty",
						"allocated_Qty","batch_Nbr","expiry_date","orig_Qty","last_Counted_At","mod_Time_Stamp","to_Be_Counted_Flag",
						"to_Be_Counted_TS","location_Lock_Code","mod_User","last_Counted_By","max_Units","max_Lpns","min_Units",
						"batch_Nbr_Lock","in_Transit","brand_Code","priority_date","maximum_Volume","putaway_Type","attribute_A",
						"attribute_B","attribute_C","attribute_D","attribute_E","attribute_F","attribute_G","attribute_H","attribute_I",
						"attribute_J","attribute_K","attribute_L","attribute_M","attribute_N","attribute_O","replenishment_Zone","company" 
				});
		lineTokenizer.setIncludedFields(new int[] { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
				                                   30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49});
		BeanWrapperFieldSetMapper<ActiveScreenReport> fieldSetMapper = new BeanWrapperFieldSetMapper<ActiveScreenReport>();
		fieldSetMapper.setTargetType(ActiveScreenReport.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	@Bean
	public LineMapper<ReserveScreenReport> reserveLineMapper() {
		DefaultLineMapper<ReserveScreenReport> lineMapper = new DefaultLineMapper<ReserveScreenReport>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						"location_Type", "location_Size_Type", "location_Area",		
						"item_Code","is_Parent","alternative_Item_Code","item_Description","batch_Nbr","expiry_Date",
						"current_Qty","allocated_Qty","current_LPN","alloc_LPN","last_CC_Counted","mod_TimeStamp",
						"to_be_Counted","to_Be_Counted_TS","loc_Lock","location_Barcode","facility","display_Text",
						"mod_User","loc_CC_User","loc_Max_LPN","loc_Max_Unit","min_Unit","attribute_A",
						"attribute_B","attribute_C","attribute_D","attribute_E","attribute_F","attribute_G",
						"attribute_H","attribute_I","attribute_J","attribute_K","attribute_L","attribute_M",
						"attribute_N","attribute_O","brand_Code","batch_Nbr_Lock","company","lock_Code"
				});
		lineTokenizer.setIncludedFields(new int[] { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
				                                    30,31,32,33,34,35,36,37,38,39,40,41,42,43,44});
		BeanWrapperFieldSetMapper<ReserveScreenReport> fieldSetMapper = new BeanWrapperFieldSetMapper<ReserveScreenReport>();
		fieldSetMapper.setTargetType(ReserveScreenReport.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	@Bean
	public JdbcBatchItemWriter<ActiveScreenReport> activeWriter() throws SQLException{
		int report_id=0;
		Connection connection=DriverManager.getConnection(dburl,username,password);
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		if(resultSet.next()){
			report_id=resultSet.getInt(1);
			logger.info("Generating report_id for active screen report:: "+report_id);
		}
		else{
			logger.error("Unable to generate report_id");
		}
		
		if(resultSet !=null){
			resultSet.close();
		}
		if(statement!=null){
			statement.close();	
		}
		if(connection!=null){
			connection.close();	
		}
		
		JdbcBatchItemWriter<ActiveScreenReport> itemWriter = new JdbcBatchItemWriter<ActiveScreenReport>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO BI_ACTIVE_SCREEN_REPORT1(report_id,facility,location,location_Barcode,location_Type,"
				+ "location_Size_Type,area,item_Assignment_Type,hierarchy_Code_3,item_Code,is_Parent,alternative_Item_Code,"
				+ "item_Description,current_Qty,allocated_Qty,batch_Nbr,expiry_date,orig_Qty,last_Counted_At,mod_Time_Stamp,"
				+ "to_Be_Counted_Flag,to_Be_Counted_TS,location_Lock_Code,mod_User,last_Counted_By,max_Units,max_Lpns,"
				+ "min_Units,batch_Nbr_Lock,in_Transit,brand_Code,priority_date,maximum_Volume,putaway_Type,attribute_A,"
				+ "attribute_B,attribute_C,attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,"
				+ "attribute_J,attribute_K,attribute_L,attribute_M,attribute_N,attribute_O,replenishment_Zone,company) "	
				+ "VALUES ('"+report_id+"',:facility, :location,:location_Barcode,:location_Type,:location_Size_Type, :area,"
				+ ":item_Assignment_Type,:hierarchy_Code_3, :item_Code, :is_Parent,:alternative_Item_Code,:item_Description,"
				+ ":current_Qty,:allocated_Qty,:batch_Nbr,:expiry_date,:orig_Qty,:last_Counted_At,:mod_Time_Stamp,"
				+ ":to_Be_Counted_Flag,:to_Be_Counted_TS,:location_Lock_Code,:mod_User,:last_Counted_By,:max_Units,"
				+ ":max_Lpns,:min_Units, :batch_Nbr_Lock,:in_Transit,:brand_Code,:priority_date,:maximum_Volume,"
				+ ":putaway_Type,:attribute_A,:attribute_B,:attribute_C,:attribute_D,:attribute_E,:attribute_F,:attribute_G,"
				+ ":attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
				+ ":replenishment_Zone,:company)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ActiveScreenReport>());
		return itemWriter;
	}
	@Bean
	public JdbcBatchItemWriter<ReserveScreenReport> reserveWriter() throws SQLException {
		int report_id=0;
		Connection connection=DriverManager.getConnection(dburl,username,password);
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query);
		if(resultSet.next()){
			report_id=resultSet.getInt(1);
			logger.info("Generating report_id for reserve screen report:: "+report_id);
		}
		else{
			logger.error("Unable to generate report_id");
		}
	
		if(resultSet !=null){
			resultSet.close();
		}
		if(statement!=null){
			statement.close();	
		}
		if(connection!=null){
			connection.close();	
		}
		
		JdbcBatchItemWriter<ReserveScreenReport> itemWriter = new JdbcBatchItemWriter<ReserveScreenReport>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO BI_RESERVE_SCREEN_REPORT1(report_id,location_Type, location_Size_Type,location_Area,item_Code,"
				+ "is_Parent,alternative_Item_Code,item_Description,batch_Nbr,expiry_Date,current_Qty,allocated_Qty,"
				+ "current_LPN,alloc_LPN,last_CC_Counted,mod_TimeStamp,to_be_Counted,to_Be_Counted_TS,loc_Lock,location_Barcode,facility,"
				+ "display_Text,mod_User,loc_CC_User,loc_Max_LPN,loc_Max_Unit,min_Unit,attribute_A,attribute_B,attribute_C,"
				+ "attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,attribute_J,attribute_K,attribute_L,"
				+ "attribute_M,attribute_N,attribute_O,brand_Code,batch_Nbr_Lock,company,lock_Code)"
				+ " VALUES ('"+report_id+"',:location_Type,:location_Size_Type,:location_Area,:item_Code,:is_Parent,"
				+ ":alternative_Item_Code,:item_Description, :batch_Nbr, :expiry_Date,:current_Qty,:allocated_Qty, "
				+ ":current_LPN,:alloc_LPN,:last_CC_Counted,:mod_TimeStamp,:to_be_Counted,:to_Be_Counted_TS,"
				+ ":loc_Lock,:location_Barcode,:facility,:display_Text,:mod_User,:loc_CC_User,:loc_Max_LPN,"
				+ ":loc_Max_Unit,:min_Unit, :attribute_A,:attribute_B,:attribute_D,:attribute_D,:attribute_E,:attribute_F,"
				+ ":attribute_G,:attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
				+ ":brand_Code,:batch_Nbr_Lock,:company, :lock_Code)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ReserveScreenReport>());
		return itemWriter;
	}
}