package com.ito.bi.reports.integration.config;

import java.sql.SQLException;

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
import com.ito.bi.reports.integration.model.IBLPNDetailsReport;
import com.ito.bi.reports.integration.model.IBLPNReports;
import com.ito.bi.reports.integration.model.IBShipmentReport;
import com.ito.bi.reports.integration.model.OrderDetailsReport;
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
	
	@Value("file:${ib.shipment.input.file}")
	private Resource iBShipment_Report_Resource;

	@Value("file:${order.details.input.file}")
	private Resource order_Details_report_Resource;
	
	@Value("file:${iblpn.details.input.file}")
	private Resource iblpn_Details_report_Resource;
	
	@Value("file:${iblpn.reports.input.file}")
	private Resource iblpn_Reports_Resource;

	public static Logger logger = LoggerFactory.getLogger(BatchConfig.class);
	
	@Bean(name = "reserveJob")
	public Job reserveJob(NotificationListener listener,Step reserveStep) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("reserveJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(reserveStep())
				.build();
	}   
	@Bean(name = "activeJob")
	public Job activeJob(NotificationListener listener,Step activeStep) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("activeJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(activeStep())
				.build();
	}

	@Bean(name = "ib_shipment_Job")
	public Job ib_shipment_Job(NotificationListener listener,Step ib_shipment_Step) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("ib_shipment_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(ib_shipment_Step())
				.build();
	}

	@Bean(name = "order_Details_Job")
	public Job order_Details_Job(NotificationListener listener,Step order_Details_Step) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("order_Details_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(order_Details_Step())
				.build();
	}

	@Bean(name = "iblpn_Details_Job")
	public Job iblpn_Details_Job(NotificationListener listener,Step iblpn_Details_Step) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("iblpn_Details_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(iblpn_Details_Step())
				.build();
	}

	@Bean(name = "iblpn_Reports_Job")
	public Job iblpn_Reports_Job(NotificationListener listener,Step iblpn_Reports_Step) throws SQLException, FileUploadException {
		return jobBuilderFactory
				.get("iblpn_Reports_Job")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.start(iblpn_Reports_Step())
				.build();
	}
	
	@Bean
	public Step reserveStep() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("reserveStep")
				.<ReserveScreenReport, ReserveScreenReport>chunk(30000)
				.reader(reserveReader())
				.writer(reserveWriter())
				.build();
	}
	
	@Bean
	public Step activeStep() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("activeStep")
				.<ActiveScreenReport, ActiveScreenReport>chunk(5000)
				.reader(activeReader())
				.writer(activeWriter())
				.build();
	}

	@Bean
	public Step ib_shipment_Step() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("ib_shipment_Job")
				.<IBShipmentReport, IBShipmentReport>chunk(5000)
				.reader(ib_shipment_Reader())
				.writer(ib_shipment_Writer())
				.build();
	}

	@Bean
	public Step order_Details_Step() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("order_Details_Job")
				.<OrderDetailsReport, OrderDetailsReport>chunk(5000)
				.reader(order_Details_Reader())
				.writer(order_Details_Writer())
				.build();
	}
	@Bean
	public Step iblpn_Details_Step() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("iblpn_Details_Job")
				.<IBLPNDetailsReport, IBLPNDetailsReport>chunk(5000)
				.reader(iblpn_Details_Reader())
				.writer(iblpn_Details_Writer())
				.build();
	}
	@Bean
	public Step iblpn_Reports_Step() throws SQLException, FileUploadException {
		return stepBuilderFactory
				.get("iblpn_Reports_Job")
				.<IBLPNReports, IBLPNReports>chunk(5000)
				.reader(iblpn_Reports_Reader())
				.writer(iblpn_Reports_Writer())
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
	public FlatFileItemReader<IBShipmentReport> ib_shipment_Reader() throws FileUploadException {
		FlatFileItemReader<IBShipmentReport> itemReader = new FlatFileItemReader<IBShipmentReport>();
		itemReader.setLineMapper(ib_shipment_LineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(iBShipment_Report_Resource);
		return itemReader;
	}
	@Bean
	public FlatFileItemReader<OrderDetailsReport> order_Details_Reader() throws FileUploadException {
		FlatFileItemReader<OrderDetailsReport> itemReader = new FlatFileItemReader<OrderDetailsReport>();
		itemReader.setLineMapper(order_Details_LineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(order_Details_report_Resource);
		return itemReader;
	}

	@Bean
	public FlatFileItemReader<IBLPNDetailsReport> iblpn_Details_Reader() throws FileUploadException {
		FlatFileItemReader<IBLPNDetailsReport> itemReader = new FlatFileItemReader<IBLPNDetailsReport>();
		itemReader.setLineMapper(iblpn_Details_LineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(iblpn_Details_report_Resource);
		return itemReader;
	}
	@Bean
	public FlatFileItemReader<IBLPNReports> iblpn_Reports_Reader() throws FileUploadException {
		FlatFileItemReader<IBLPNReports> itemReader = new FlatFileItemReader<IBLPNReports>();
		itemReader.setLineMapper(iblpn_Reports_LineMapper());
		itemReader.setLinesToSkip(1);
		itemReader.setResource(iblpn_Reports_Resource);
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
	public LineMapper<IBShipmentReport> ib_shipment_LineMapper() {
		DefaultLineMapper<IBShipmentReport> lineMapper = new DefaultLineMapper<IBShipmentReport>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						"company","facility","shipment_No","item_Code","alternative_Item_Code","item_Description","storage_Type",
						"vendor_Name_or_From_Location","shipped_Date","shipped_Qty","received_Qty","iblpn_Number",
						"shipment_Type","shipment_Status","uom","expiry_Date","batch_Number","lock_Code","item_Shelf_Life",
						"original_Create_Ts","verified_Ts","create_User"
				});
		lineTokenizer.setIncludedFields(new int[] { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21});
		BeanWrapperFieldSetMapper<IBShipmentReport> fieldSetMapper = new BeanWrapperFieldSetMapper<IBShipmentReport>();
		fieldSetMapper.setTargetType(IBShipmentReport.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public LineMapper<OrderDetailsReport> order_Details_LineMapper() {
		DefaultLineMapper<OrderDetailsReport> lineMapper = new DefaultLineMapper<OrderDetailsReport>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						
						"company_Code","facility_Code","order_No","customer_Name","ship_To","customer_Po","order_Type",
						"item_Code","item_Description","product_Life","storage_Type","sales_Channel","orig_Order_Qty",
						"allocated_Qty","shipped_Qty","uom","integrated_Wave_Perc","detail_Line_Status","order_Status",
						"create_Time","ship_Time","required_Ship_Date","seq_No","allocation_Ts","expiry_Date",
						"batch_Number","destination_Facility_Code","picked_Time","load_Number"
						
				});
		lineTokenizer.setIncludedFields(new int[] { 0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28});
		BeanWrapperFieldSetMapper<OrderDetailsReport> fieldSetMapper = new BeanWrapperFieldSetMapper<OrderDetailsReport>();
		fieldSetMapper.setTargetType(OrderDetailsReport.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	@Bean
	public LineMapper<IBLPNDetailsReport> iblpn_Details_LineMapper() {
		DefaultLineMapper<IBLPNDetailsReport> lineMapper = new DefaultLineMapper<IBLPNDetailsReport>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						
				"company_Code","facility_Code","lPN_No","lPN_Status","item_code","item_B","item_Description","batch_No",
				"expiry_Date","lock_Code","item_Life","location","allocation_Zone","lPN_Current_Qty","lPN_Allocated_Qty","lPN_Avilable_Qty","allow_Reserve_Partial_pick","ref_Shipment_No",
				"lPN_Create_Time","lPN_received_Time","lPN_modified_Time","lPN_received_User","shipment_Type","shipment_Status"
						
				});
		lineTokenizer.setIncludedFields(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23});
		BeanWrapperFieldSetMapper<IBLPNDetailsReport> fieldSetMapper = new BeanWrapperFieldSetMapper<IBLPNDetailsReport>();
		fieldSetMapper.setTargetType(IBLPNDetailsReport.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}

	@Bean
	public LineMapper<IBLPNReports> iblpn_Reports_LineMapper() {
		DefaultLineMapper<IBLPNReports> lineMapper = new DefaultLineMapper<IBLPNReports>();
		DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
		lineTokenizer.setNames(new String[] 
				{
						
						"facility","company","lPN_Nbr","status","qC_Status","vAS","current_Location","prev_Location","item_Code","is_Parent",
						"alternative_Item_Code","description","orig_Qty","received_Qty","current_Qty","pack_Qty","case_Qty","nbr_Lock","create_TimeStamp",
						"received_TimeStamp","priority_Date","putaway_Type","batch_Nbr","expiry_Date","mod_Time_Stamp","received_Shipment_Nbr","shipment_Type",
						"ref_PO_Nbr","pallet_Nbr","allocated_Qty","attribute_A","attribute_B","attribute_C","attribute_D","attribute_E","attribute_F","attribute_G",
						"attribute_H","attribute_I","attribute_J","attribute_K","attribute_L","attribute_M","attribute_N","attribute_O","received_User",
						"mod_User","mod_TimeStamp","manufacture_Date","lPN_is_Pallet","item_Hierarchy_1_Code","item_Hierarchy_1",
						"item_hierarchy_2","item_Hierarchy_3_Code","item_hierarchy_3","item_Hierarchy_4_Code","item_hierarchy_4","item_Hierarchy_5_Code",
						"item_hierarchy_5","weight","volume","length","width","height","brand_Code"

						
				});
		lineTokenizer.setIncludedFields(new int[] {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,
                30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,53,54,55,56,57,58,59,60,61,62,63,64});
		BeanWrapperFieldSetMapper<IBLPNReports> fieldSetMapper = new BeanWrapperFieldSetMapper<IBLPNReports>();
		fieldSetMapper.setTargetType(IBLPNReports.class);
		lineMapper.setLineTokenizer(lineTokenizer);
		lineMapper.setFieldSetMapper(fieldSetMapper);
		return lineMapper;
	}
	
	@Bean
	public JdbcBatchItemWriter<ActiveScreenReport> activeWriter() throws SQLException{
		JdbcBatchItemWriter<ActiveScreenReport> itemWriter = new JdbcBatchItemWriter<ActiveScreenReport>();
		itemWriter.setDataSource(dataSource);
		itemWriter.setSql("INSERT INTO BI_REP_ACTIVE_SCREEN(report_id,facility,location,location_Barcode,location_Type,"
				+ "location_Size_Type,area,item_Assignment_Type,hierarchy_Code_3,item_Code,is_Parent,alternative_Item_Code,"
				+ "item_Description,current_Qty,allocated_Qty,batch_Nbr,expiry_date,orig_Qty,last_Counted_At,mod_Time_Stamp,"
				+ "to_Be_Counted_Flag,to_Be_Counted_TS,location_Lock_Code,mod_User,last_Counted_By,max_Units,max_Lpns,"
				+ "min_Units,batch_Nbr_Lock,in_Transit,brand_Code,priority_date,maximum_Volume,putaway_Type,attribute_A,"
				+ "attribute_B,attribute_C,attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,"
				+ "attribute_J,attribute_K,attribute_L,attribute_M,attribute_N,attribute_O,replenishment_Zone,company,record_creation_timestamp) "	
				+ "VALUES (:report_id,:facility, :location,:location_Barcode,:location_Type,:location_Size_Type, :area,"
				+ ":item_Assignment_Type,:hierarchy_Code_3, :item_Code, :is_Parent,:alternative_Item_Code,:item_Description,"
				+ ":current_Qty,:allocated_Qty,:batch_Nbr,:expiry_date,:orig_Qty,:last_Counted_At,:mod_Time_Stamp,"
				+ ":to_Be_Counted_Flag,:to_Be_Counted_TS,:location_Lock_Code,:mod_User,:last_Counted_By,:max_Units,"
				+ ":max_Lpns,:min_Units, :batch_Nbr_Lock,:in_Transit,:brand_Code,:priority_date,:maximum_Volume,"
				+ ":putaway_Type,:attribute_A,:attribute_B,:attribute_C,:attribute_D,:attribute_E,:attribute_F,:attribute_G,"
				+ ":attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
				+ ":replenishment_Zone,:company,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ActiveScreenReport>());
		return itemWriter;
	}
	@Bean
	public JdbcBatchItemWriter<ReserveScreenReport> reserveWriter() throws SQLException {
		
		JdbcBatchItemWriter<ReserveScreenReport> itemWriter = new JdbcBatchItemWriter<ReserveScreenReport>();
		itemWriter.setDataSource(dataSource);
	
		itemWriter.setSql("INSERT INTO  BI_REP_RESERVE_SCREEN(report_id,location_Type, location_Size_Type,location_Area,item_Code,"
				+ "is_Parent,alternative_Item_Code,item_Description,batch_Nbr,expiry_Date,current_Qty,allocated_Qty,"
				+ "current_LPN,alloc_LPN,last_CC_Counted,mod_TimeStamp,to_be_Counted,to_Be_Counted_TS,loc_Lock,location_Barcode,facility,"
				+ "display_Text,mod_User,loc_CC_User,loc_Max_LPN,loc_Max_Unit,min_Unit,attribute_A,attribute_B,attribute_C,"
				+ "attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,attribute_I,attribute_J,attribute_K,attribute_L,"
				+ "attribute_M,attribute_N,attribute_O,brand_Code,batch_Nbr_Lock,company,lock_Code,record_creation_timestamp)"
				+ " VALUES (:report_id,:location_Type,:location_Size_Type,:location_Area,:item_Code,:is_Parent,"
				+ ":alternative_Item_Code,:item_Description, :batch_Nbr, :expiry_Date,:current_Qty,:allocated_Qty, "
				+ ":current_LPN,:alloc_LPN,:last_CC_Counted,:mod_TimeStamp,:to_be_Counted,:to_Be_Counted_TS,"
				+ ":loc_Lock,:location_Barcode,:facility,:display_Text,:mod_User,:loc_CC_User,:loc_Max_LPN,"
				+ ":loc_Max_Unit,:min_Unit, :attribute_A,:attribute_B,:attribute_D,:attribute_D,:attribute_E,:attribute_F,"
				+ ":attribute_G,:attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,:attribute_O,"
				+ ":brand_Code,:batch_Nbr_Lock,:company, :lock_Code,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ReserveScreenReport>());
		return itemWriter;
	}
	
	@Bean
	public JdbcBatchItemWriter<IBShipmentReport> ib_shipment_Writer() throws SQLException {
		
		JdbcBatchItemWriter<IBShipmentReport> itemWriter = new JdbcBatchItemWriter<IBShipmentReport>();
		itemWriter.setDataSource(dataSource);
	
		itemWriter.setSql("INSERT INTO  BI_REP_IB_SHIPMENT(report_id,company,facility,shipment_No,item_Code,alternative_Item_Code,"
				+ "item_Description,storage_Type,vendor_Name_or_From_Location,shipped_Qty,received_Qty,shipment_Type,shipment_Status,"
				+ "uom,expiry_Date,batch_Number,lock_Code,item_Shelf_Life,original_Create_Ts,create_User,shipped_Date,iblpn_Number,verified_Ts,record_creation_timestamp)"
				
				+ "VALUES(:report_id,:company,:facility,:shipment_No,:item_Code,:alternative_Item_Code,:item_Description,"
				+ ":storage_Type,:vendor_Name_or_From_Location,:shipped_Qty,:received_Qty,:shipment_Type,:shipment_Status,"
				+ ":uom,:expiry_Date,:batch_Number,:lock_Code,:item_Shelf_Life,:original_Create_Ts,:create_User,:shipped_Date,:iblpn_Number,:verified_Ts,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<IBShipmentReport>());
		return itemWriter;
	}

	@Bean
	public JdbcBatchItemWriter<OrderDetailsReport> order_Details_Writer() throws SQLException {
		
		JdbcBatchItemWriter<OrderDetailsReport> itemWriter = new JdbcBatchItemWriter<OrderDetailsReport>();
		itemWriter.setDataSource(dataSource);
	
		itemWriter.setSql("INSERT INTO  BI_REP_ORDER_DETAILS(report_id,company_Code,facility_Code, order_No,customer_Name,ship_To,"
				+ "customer_Po,order_Type, item_Code, item_Description,product_Life,storage_Type, sales_Channel,orig_Order_Qty,allocated_Qty,shipped_Qty,uom,integrated_Wave_Perc,"
				+ "detail_Line_Status,order_Status,create_Time,ship_Time,required_Ship_Date,seq_No,"
				+ "allocation_Ts,expiry_Date,batch_Number,destination_Facility_Code,picked_Time,load_Number,record_creation_timestamp)"
				
				+ "VALUES(:report_id,:company_Code,:facility_Code, :order_No,:customer_Name,:ship_To,:customer_Po,:order_Type,:item_Code,:item_Description,:product_Life,"
				+ ":storage_Type,:sales_Channel,:orig_Order_Qty,:allocated_Qty,:shipped_Qty,:uom,:integrated_Wave_Perc,:detail_Line_Status,:order_Status,:create_Time,"
				+ ":ship_Time,:required_Ship_Date,:seq_No,:allocation_Ts,:expiry_Date,:batch_Number,:destination_Facility_Code,:picked_Time,:load_Number,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<OrderDetailsReport>());
		return itemWriter;
	}
	@Bean
	public JdbcBatchItemWriter<IBLPNDetailsReport> iblpn_Details_Writer() throws SQLException {
		
		JdbcBatchItemWriter<IBLPNDetailsReport> itemWriter = new JdbcBatchItemWriter<IBLPNDetailsReport>();
		itemWriter.setDataSource(dataSource);
	
		
		itemWriter.setSql("INSERT INTO BI_REP_IBLPN_DETAILS(report_id,company_Code,facility_Code,lPN_No,lPN_Status,item_code,item_B,"
				+ "item_Description,batch_No,expiry_Date,lock_Code,item_Life,location,allocation_Zone,lPN_Current_Qty,lPN_Allocated_Qty,lPN_Avilable_Qty,allow_Reserve_Partial_pick,ref_Shipment_No,"
				+ "lPN_Create_Time,lPN_received_Time,lPN_modified_Time,lPN_received_User,shipment_Type,shipment_Status,record_creation_timestamp)"
				+ "VALUES(:report_id,:company_Code,:facility_Code,:lPN_No,:lPN_Status,:item_code,:item_B,:item_Description,:batch_No,:expiry_Date,"
				+ ":lock_Code,:item_Life,:location,:allocation_Zone,:lPN_Current_Qty,:lPN_Allocated_Qty,:lPN_Avilable_Qty,:allow_Reserve_Partial_pick,:ref_Shipment_No,:lPN_Create_Time,:lPN_received_Time,"
				+ ":lPN_modified_Time,:lPN_received_User,:shipment_Type,:shipment_Status,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<IBLPNDetailsReport>());
		return itemWriter;
	}
	@Bean
	public JdbcBatchItemWriter<IBLPNReports> iblpn_Reports_Writer() throws SQLException {
		
		JdbcBatchItemWriter<IBLPNReports> itemWriter = new JdbcBatchItemWriter<IBLPNReports>();
		itemWriter.setDataSource(dataSource);

		itemWriter.setSql("INSERT INTO BI_REP_IBLPN_REPORT(report_id,facility,company,lPN_Nbr,status,qC_Status,vAS,current_Location,prev_Location,item_Code,is_Parent,"
				+ "alternative_Item_Code,description,orig_Qty,received_Qty,current_Qty,pack_Qty,case_Qty,nbr_Lock,create_TimeStamp,"
				+ "received_TimeStamp,priority_Date,putaway_Type,batch_Nbr,expiry_Date,mod_Time_Stamp,received_Shipment_Nbr,shipment_Type,"
				+ "ref_PO_Nbr,pallet_Nbr,allocated_Qty,attribute_A,attribute_B,attribute_C,attribute_D,attribute_E,attribute_F,attribute_G,attribute_H,"
				+ "attribute_I,attribute_J,attribute_K,attribute_L,attribute_M,attribute_N,attribute_O,received_User,mod_User,mod_TimeStamp,"
				+ "manufacture_Date,lPN_is_Pallet,item_Hierarchy_1_Code,item_Hierarchy_1,item_hierarchy_2,item_Hierarchy_3_Code,"
				+ "item_hierarchy_3,item_Hierarchy_4_Code,item_hierarchy_4,item_Hierarchy_5_Code,item_hierarchy_5,"
				+ "weight,volume,length,width,height,brand_Code,record_creation_timestamp)"
				
				+ "VALUES(:report_id,:facility,:company,:lPN_Nbr,:status,:qC_Status,:vAS,:current_Location,:prev_Location,"
				+ ":item_Code,:is_Parent,:alternative_Item_Code,:description,:orig_Qty,:received_Qty,:current_Qty,:pack_Qty,:case_Qty,:nbr_Lock,"
				+ ":create_TimeStamp,:received_TimeStamp,:priority_Date,:putaway_Type,:batch_Nbr,:expiry_Date,:mod_Time_Stamp,:received_Shipment_Nbr,"
				+ ":shipment_Type,:ref_PO_Nbr,:pallet_Nbr,:allocated_Qty,:attribute_A,:attribute_B,:attribute_C,:attribute_D,:attribute_E,"
				+ ":attribute_F,:attribute_G,:attribute_H,:attribute_I,:attribute_J,:attribute_K,:attribute_L,:attribute_M,:attribute_N,"
				+ ":attribute_O,:received_User,:mod_User,:mod_TimeStamp,:manufacture_Date,:lPN_is_Pallet,:item_Hierarchy_1_Code,:item_Hierarchy_1,"
				+ ":item_hierarchy_2,:item_Hierarchy_3_Code,:item_hierarchy_3,:item_Hierarchy_4_Code,:item_hierarchy_4,:item_Hierarchy_5_Code,"
				+ ":item_hierarchy_5,:weight,:volume,:length,:width,:height,:brand_Code,sysdate)");
		itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<IBLPNReports>());
		return itemWriter;
	}
}