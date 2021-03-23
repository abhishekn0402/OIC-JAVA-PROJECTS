package com.IOC.CSVBatchReaderApplication.Configuration;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.sql.DataSource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.DefaultBatchConfigurer;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.IOC.CSVBatchReaderApplication.Exception.FileUploadException;
import com.IOC.CSVBatchReaderApplication.Listener.JobCompletionListenerActiveScreen;
import com.IOC.CSVBatchReaderApplication.Listener.JobCompletionListenerReserveScreen;
import com.IOC.CSVBatchReaderApplication.Model.CSVBatchReaderActiveScreen;
import com.IOC.CSVBatchReaderApplication.Model.CSVBatchReaderReserveScreen;
import com.IOC.CSVBatchReaderApplication.Processor.CsvBatchProcessorActiveScreen;
import com.IOC.CSVBatchReaderApplication.Processor.CsvItemProcessor;
import com.IOC.CSVBatchReaderApplication.SFTPClient.FTPConnection;
import com.zaxxer.hikari.HikariDataSource;

@Configuration
@EnableBatchProcessing
@EnableScheduling
public class BatchConfiguration extends DefaultBatchConfigurer
{
	public static Logger logger = LoggerFactory.getLogger(BatchConfiguration.class);
	@Autowired
	public JobBuilderFactory jobBuilderFactory;
	@Autowired
	public StepBuilderFactory stepBuilderFactory;


	public static String connectionURL="jdbc:oracle:thin:@130.61.249.138:1521:CLONE";
	public static String connectionUname="dmcstar";
	public static String connectionPassword="dmcstar";
	public static String query="SELECT SEQ_REPORT_ID.NEXTVAL FROM dual";
	
	/*
	
    @Value("${spring.datasource.connectionURL}")
	private String connectionURL;
	    
	@Value("${spring.datasource.connectionUname}")
	private String connectionUname;
	    
	@Value("${spring.datasource.connectionPassword}")
	private String connectionPassword;
	    
	@Value("${spring.datasource.query}")
	private String query;
 
  */
	@Bean
	public FlatFileItemReader<CSVBatchReaderReserveScreen> reader() throws IOException, FileUploadException 
	{	

		String reserve=FTPConnection.sort_Reserve_Screen_Report().toString();
		return new FlatFileItemReaderBuilder<CSVBatchReaderReserveScreen>()
				.name("csvItemReader")					
				.resource(new FileSystemResource(reserve))
				.lineMapper(lineMapper())
				.linesToSkip(1)
				.build();
	}

	@Bean
	public LineMapper<CSVBatchReaderReserveScreen> lineMapper() 
	{
		DefaultLineMapper<CSVBatchReaderReserveScreen> lineMapper = new DefaultLineMapper<CSVBatchReaderReserveScreen>();
		lineMapper.setLineTokenizer(new DelimitedLineTokenizer()
		{
			{	
				setNames(new String[]

						{
								"location_Type", "location_Size_Type", "location_Area",		
								"item_Code","is_Parent","alternative_Item_Code","item_Description","batch_Nbr","expiry_Date",
								"current_Qty","allocated_Qty","current_LPN","alloc_LPN","last_CC_Counted","mod_TimeStamp",
								"to_be_Counted","to_Be_Counted_TS","loc_Lock","location_Barcode","facility","display_Text",
								"mod_User","loc_CC_User","loc_Max_LPN","loc_Max_Unit","min_Unit","attribute_A",
								"attribute_B","attribute_C","attribute_D","attribute_E","attribute_F","attribute_G",
								"attribute_H","attribute_I","attribute_J","attribute_K","attribute_L","attribute_M",
								"attribute_N","attribute_O","brand_Code","batch_Nbr_Lock","company","lock_Code"
						}
						);
			}
		}
				);
		lineMapper.setFieldSetMapper(new BeanWrapperFieldSetMapper<CSVBatchReaderReserveScreen>()
		{
			{
				setTargetType(CSVBatchReaderReserveScreen.class);
			}
		}
				);
		return lineMapper;
	}
	@Bean
	public JdbcBatchItemWriter<CSVBatchReaderReserveScreen> writer(DataSource dataSource) throws SQLException 
	{
		int report_id=0;
		Connection connection=DriverManager.getConnection(connectionURL,connectionUname,connectionPassword);
		logger.info("connectionURL is "+connectionURL);
		Statement statement=connection.createStatement();
		ResultSet resultSet=statement.executeQuery(query);

		if(resultSet.next())
		{
			report_id=resultSet.getInt(1);
			logger.info("Generating report_id :: "+report_id);
		}
		else
		{
			logger.error("Unable to generate report_id");
		}
		if(resultSet !=null)
		{
			resultSet.close();
		}
		if(statement!=null)
		{
			statement.close();	
		}
		if(connection!=null)
		{
			connection.close();	
		}

		logger.info(":Inserting data into BI_RESERVE_SCREEN_REPORT table");
		return new JdbcBatchItemWriterBuilder<CSVBatchReaderReserveScreen>()
				.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CSVBatchReaderReserveScreen>())
				.sql("INSERT INTO BI_RESERVE_SCREEN_REPORT(report_id, location_Type, location_Size_Type,location_Area,item_Code,"
						+ "is_Parent,alternative_Item_Code,item_Description,batch_Nbr,expiry_Date,current_Qty,allocated_Qty,"
						+ "current_LPN,alloc_LPN,last_CC_Counted,mod_TimeStamp,to_be_Counted,to_Be_Counted_TS,loc_Lock,location_Barcode,facility,"
						+ "display_Text,mod_User,loc_CC_User,loc_Max_LPN,loc_Max_Unit,min_Unit,attribute_A,attribute_B,attribute_C,"
						+ "attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,attribute_J,attribute_K,attribute_L,"
						+ "attribute_M,attribute_N,attribute_O,brand_Code,batch_Nbr_Lock,company,lock_Code)"

						+ " VALUES ('"+report_id+"', :location_Type,:location_Size_Type,:location_Area,:item_Code,:is_Parent,"
						+ ":alternative_Item_Code,:item_Description, :batch_Nbr, :expiry_Date,:current_Qty,:allocated_Qty, "
						+ ":current_LPN,:alloc_LPN,:last_CC_Counted,:mod_TimeStamp,:to_be_Counted,:to_Be_Counted_TS,"
						+ ":loc_Lock,:location_Barcode,:facility,:display_Text,:mod_User,:loc_CC_User,:loc_Max_LPN,"
						+ ":loc_Max_Unit,:min_Unit, :attribute_A,:attribute_B,:attribute_D,:attribute_D,:attribute_E,:attribute_F,"
						+ ":attribute_G,:attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
						+ ":brand_Code,:batch_Nbr_Lock,:company, :lock_Code)")
				.dataSource(dataSource)
				.build();
	}

	@Bean
	public ItemProcessor<CSVBatchReaderReserveScreen,CSVBatchReaderReserveScreen> processor()
	{
		return new CsvItemProcessor();
	}

	@Bean(name = "createMarkSheetJobReserveScreen")
	public Job createMarkSheetJobReserveScreen(JobCompletionListenerReserveScreen listener, Step stepReserveScreen)
	{
		return jobBuilderFactory
				.get("createMarkSheetJobReserveScreen")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(stepReserveScreen)
				.end()
				.build();
	}
	@Bean	
	public Step stepReserveScreen(ItemReader<CSVBatchReaderReserveScreen> reader, ItemWriter<CSVBatchReaderReserveScreen> writer,
			ItemProcessor<CSVBatchReaderReserveScreen, CSVBatchReaderReserveScreen> processor)
	{
		return stepBuilderFactory
				.get("stepReserveScreen")
				.<CSVBatchReaderReserveScreen, CSVBatchReaderReserveScreen>chunk(5)
				.reader(reader)
				.processor(processor)
				.writer(writer) 
				.build();
	}
	@Bean
	public DataSource getDataSource() 
	{

				HikariDataSource dataSource = new HikariDataSource();	
				dataSource.setJdbcUrl(connectionURL);
				dataSource.setUsername(connectionUname);
				dataSource.setUsername(connectionPassword);
				return dataSource;
	}

	@Bean
	public JdbcTemplate jdbcTemplate(DataSource dataSource)
	{
		return new JdbcTemplate(dataSource);
	}

	public void setDataSource(DataSource dataSource)
	{

	}	
	
	@Configuration
	public class BatchConfigurationActiveScreen 
	{
		@Bean
		public FlatFileItemReader<CSVBatchReaderActiveScreen> readerActiveScreen() throws IOException, FileUploadException 
		{
			String active=FTPConnection.sort_Active_Screen_Report().toString();
			return new FlatFileItemReaderBuilder<CSVBatchReaderActiveScreen>()
					.name("csvItemReaderActiveScreen")		
					.resource(new FileSystemResource(active))
					.lineMapper(lineMapperActiveScreen())
					.linesToSkip(1)
					.build();
		}



		@Bean
		public LineMapper<CSVBatchReaderActiveScreen> lineMapperActiveScreen() 
		{
			DefaultLineMapper<CSVBatchReaderActiveScreen> lineMapperActiveScreen = new DefaultLineMapper<CSVBatchReaderActiveScreen>();
			lineMapperActiveScreen.setLineTokenizer(new DelimitedLineTokenizer()
			{
				{

					setNames(new String[]
							{       "facility","location","location_Barcode","location_Type","location_Size_Type","area", "item_Assignment_Type",
									"hierarchy_Code_3", "item_Code","is_Parent","alternative_Item_Code","item_Description","current_Qty",
									"allocated_Qty","batch_Nbr","expiry_date","orig_Qty","last_Counted_At","mod_Time_Stamp","to_Be_Counted_Flag",
									"to_Be_Counted_TS","location_Lock_Code","mod_User","last_Counted_By","max_Units","max_Lpns","min_Units",
									"batch_Nbr_Lock","in_Transit","brand_Code","priority_date","maximum_Volume","putaway_Type","attribute_A",
									"attribute_B","attribute_C","attribute_D","attribute_E","attribute_F","attribute_G","attribute_H","attribute_I",
									"attribute_J","attribute_K","attribute_L","attribute_M","attribute_N","attribute_O","replenishment_Zone","company"

							}
							);
				}
			}
					);
			lineMapperActiveScreen.setFieldSetMapper(new BeanWrapperFieldSetMapper<CSVBatchReaderActiveScreen>()
			{
				{
					setTargetType(CSVBatchReaderActiveScreen.class);
				}
			}
					);
			return lineMapperActiveScreen;
		}
		
		@Bean
		public JdbcBatchItemWriter<CSVBatchReaderActiveScreen> writerActiveScreen(@Qualifier("getDataSourceActiveScreen") DataSource dataSource) throws SQLException 
		{
			int report_id=0;
			Connection connection=DriverManager.getConnection(connectionURL,connectionUname,connectionPassword);
			Statement statement=connection.createStatement();
			ResultSet resultSet=statement.executeQuery(query);
			if(resultSet.next())
			{
				report_id=resultSet.getInt(1);	
			}
			if(resultSet !=null)
			{
				resultSet.close();
			}
			if(statement!=null)
			{
				statement.close();	
			}
			if(connection!=null)
			{
				connection.close();	
			}

			logger.info(":Inserting data into BI_ACTIVE_SCREEN_REPORT table");
			return new JdbcBatchItemWriterBuilder<CSVBatchReaderActiveScreen>()
					.itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<CSVBatchReaderActiveScreen>())//csvBatchReaderdto table name
					.sql("INSERT INTO BI_ACTIVE_SCREEN_REPORT(report_id,facility,location,location_Barcode,location_Type,"
							+ "location_Size_Type,area,item_Assignment_Type,hierarchy_Code_3,item_Code,is_Parent,alternative_Item_Code,"
							+ "item_Description,current_Qty,allocated_Qty,batch_Nbr,expiry_date,orig_Qty,last_Counted_At,mod_Time_Stamp,"
							+ "to_Be_Counted_Flag,to_Be_Counted_TS,location_Lock_Code,mod_User,last_Counted_By,max_Units,max_Lpns,"
							+ "min_Units,batch_Nbr_Lock,in_Transit,brand_Code,priority_date,maximum_Volume,putaway_Type,attribute_A,"
							+ "attribute_B,attribute_C,attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,"
							+ "attribute_J,attribute_K,attribute_L,attribute_M,attribute_N,attribute_O,replenishment_Zone,company) "	

		   					+ "VALUES ("+report_id+",:facility, :location,:location_Barcode,:location_Type,:location_Size_Type, :area,"
		   					+ ":item_Assignment_Type,:hierarchy_Code_3, :item_Code, :is_Parent,:alternative_Item_Code,:item_Description,"
		   					+ ":current_Qty,:allocated_Qty,:batch_Nbr,:expiry_date,:orig_Qty,:last_Counted_At,:mod_Time_Stamp,"
		   					+ ":to_Be_Counted_Flag,:to_Be_Counted_TS,:location_Lock_Code,:mod_User,:last_Counted_By,:max_Units,"
		   					+ ":max_Lpns,:min_Units, :batch_Nbr_Lock,:in_Transit,:brand_Code,:priority_date,:maximum_Volume,"
		   					+ ":putaway_Type,:attribute_A,:attribute_B,:attribute_C,:attribute_D,:attribute_E,:attribute_F,:attribute_G,"
		   					+ ":attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
		   					+ ":replenishment_Zone,:company)")
					.dataSource(dataSource)
					.build();
		}

		@Bean
		public ItemProcessor<CSVBatchReaderActiveScreen,CSVBatchReaderActiveScreen> processorActiveScreen()
		{
			return new CsvBatchProcessorActiveScreen();
		}

		@Bean(name = "createMarkSheetJobActiveScreen")
		public Job createMarkSheetJobActiveScreen(JobCompletionListenerActiveScreen listenerActiveScreen, Step stepActiveScreen)
		{
			return jobBuilderFactory
					.get("createMarkSheetJobActiveScreen")
					.incrementer(new RunIdIncrementer())
					.listener(listenerActiveScreen)
					.flow(stepActiveScreen)
					.end()
					.build();
		}
		
		@Bean
		public Step stepActiveScreen(ItemReader<CSVBatchReaderActiveScreen> readerActiveScreen, ItemWriter<CSVBatchReaderActiveScreen> writerActiveScreen,
				ItemProcessor<CSVBatchReaderActiveScreen, CSVBatchReaderActiveScreen> processorActiveScreen)
		{
			return stepBuilderFactory
					.get("stepActiveScreen")
					.<CSVBatchReaderActiveScreen, CSVBatchReaderActiveScreen>chunk(5)
					.reader(readerActiveScreen)
					.processor(processorActiveScreen)
					.writer(writerActiveScreen) 
					.build();
		}

		@Bean
		@Primary
		public DataSource getDataSourceActiveScreen() 
		{
			        HikariDataSource dataSource = new HikariDataSource();
			        dataSource.setJdbcUrl(connectionURL);
			        dataSource.setUsername(connectionUname);
			        dataSource.setPassword(connectionPassword);
			        logger.info(":connection established to database.");
			        return dataSource;
		}
		
		@Bean
		public JdbcTemplate jdbcTemplateActiveScreen(DataSource dataSource)
		{

					return new JdbcTemplate(dataSource);
		}
		
		public void setDataSource(DataSource dataSource)
		{

		}
	}
}

