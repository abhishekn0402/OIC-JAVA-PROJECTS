package com.ito.bi.reports.integration;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ito.bi.reports.integration.ftp.FTPConfigProperties;
/**
 * WMS BI Reporting application.
 *
 * @author Satya Nekkanti, Abhishek Nagaraj
 */
@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@EnableScheduling
public class StartlinkBIReporting
{
	@Autowired
	JobLauncher jobLauncher;

	@Autowired
	@Qualifier("activeJob")
	Job activeJob;

	@Autowired
	@Qualifier("reserveJob")
	Job reserveJob;

	@Autowired
	@Qualifier("ib_shipment_Job")
	Job ib_shipment_Job;

	@Autowired
	@Qualifier("order_Details_Job")
	Job order_Details_Job;

	@Autowired
	@Qualifier("iblpn_Details_Job")
	Job iblpn_Details_Job;

	@Autowired
	@Qualifier("iblpn_Reports_Job")
	Job iblpn_Reports_Job;	

	@Autowired
	FTPConfigProperties fTPConfigProperties;

	@Value("${spring.datasource.url}")
	private String dburl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.query}")
	private String query;

	@Value("${ftp.remoteDir.BI}")
	private String biFolder;

	@Value("${ftp.remoteDir.PROCESSED}")
	private String processedFolder;

	@Value("${ftp.remoteDir}")
	private String remoteDirPath;

	@Value("${ftp.saveDir}")
	private String saveDirPath;

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
	
	@Value("${mail.admin_Account}")
	private String admin_Account;

	@Value("${mail.admin_password}")
	private String admin_password;

	@Value("${mail.to_recipient}")
	private String to_recipient;

	@Value("${mail.cc_recipients}")
	private String cc_recipients;
	


	public static FTPClient ftpClient=new FTPClient();

	public static Logger logger = LoggerFactory.getLogger(StartlinkBIReporting.class);
	public static void main(String[] args)
	{	
		SpringApplication.run(StartlinkBIReporting.class, args);
	}

	@Scheduled(fixedRate = 180000)
	public void ftpHandler() throws IOException, MessagingException 
	{
		try
		{
			ftpClient.connect(fTPConfigProperties.getHostName(),fTPConfigProperties.getPort());
			ftpClient.login(fTPConfigProperties.getUserName(),fTPConfigProperties.getPassword());
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			logger.info("Connection established to ftp server..");
			logger.info("Checking the files availability..");
			downloadDirectory(ftpClient, remoteDirPath, saveDirPath);
			ftpClient.logout();  
		}
		catch (Exception exception){
			notification(exception);
			logger.info("Unble to move or delete files in FTP server due to unexpected issues from FTP server"
					+ "and contact FTP server admin");	
			logger.info(exception.getMessage());

		}
	}
	public void downloadDirectory(FTPClient ftpClient, String parentDir, String saveDir) throws IOException, MessagingException, FileUploadException, InterruptedException 
	{
		logger.info("Entered downloading dirctory - BI folder");
		String dirToList = parentDir;
		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		if (subFiles != null && subFiles.length >1){
			for (FTPFile aFile : subFiles)
			{
				String currentFileName = aFile.getName(); 

				String ftpFilePath = parentDir + currentFileName;

				String localDirPath = saveDir + parentDir;

				if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Reserve_Screen_Report")){
					localDirPath = localDirPath+ "reserve_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Active_Screen_Report")){
					localDirPath = localDirPath+ "active_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_IB_Shipment_OIC")){
					localDirPath = localDirPath+ "ib_Shipment_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_IBLPNs_Details_OIC")){
					localDirPath = localDirPath+ "iblpn_Details_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_Order_Detail_OIC")){
					localDirPath = localDirPath+ "order_Details_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_IBLPN_Report")){
					localDirPath = localDirPath+ "iblpn_Reports.csv";
				}
				if ((aFile.isDirectory()) || !aFile.getName().endsWith(".csv")){
					continue;
				}
				else if (aFile.getName().startsWith("AQM_JEDDAH_BI Report_Reserve_Screen_Report")
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_Active_Screen_Report") 
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_Gen2_IB_Shipment_OIC")
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_Gen2_Order_Detail_OIC")
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_Gen2_IBLPNs_Details_OIC")
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_IBLPN_Report")){
					boolean success = downloadSingleFile(ftpClient, ftpFilePath, localDirPath);
					if (success){
						logger.info(ftpFilePath+" File downloaded successfully..");						
						boolean fileMoved=ftpClient.rename(biFolder.concat(currentFileName),processedFolder.concat(currentFileName));
						logger.info(currentFileName+" File move to PROCESSED folder successfully..");
						if(!fileMoved){
							throw new FileUploadException("Unble to move or delete files in FTP server due to unexpected issues from FTP server");	
						}	
					} 
					else{
						logger.info("Unable to download "+ftpFilePath+" file..");
					}
				}
			}		
		}
	}
	public static boolean downloadSingleFile(FTPClient ftpClient,
			String remoteFilePath, String savePath) throws IOException{

		File downloadFile = new File(savePath); 
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
		try {
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			logger.info("copying files from ftp to local folders");
			return ftpClient.retrieveFile(remoteFilePath, outputStream);

		} catch (IOException ex){
			logger.info("exception");
			throw ex;
		} 
		finally{
			if (outputStream != null){
				outputStream.close();
			}
		}    
	}

	@Scheduled(fixedRate = 3600000)//180000
	public void performActiveReport() throws Exception{
		try {
			logger.info("before inserting to the  active screen table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("ACTIVE:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(activeJob, params);
			logger.info("after insertion to the active screen table ::"+System.currentTimeMillis());
		} catch (Exception exception)
		{
			logger.info("unable to insert data to the BI_REP_ACTIVE_SCREEN table");
			notification(exception);
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void performReserveReport() throws Exception {
		try {
			logger.info("before inserting to the  reserve screen table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("RESERVE:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(reserveJob, params);
			logger.info("after insertion to the  reserve screen table ::"+System.currentTimeMillis());
		} catch (Exception exception) {
			logger.info("unable to insert data to the BI_REP_RESERVE_SCREEN table");
			notification(exception);
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_IB_Shipment_Report() throws Exception {
		try {
			logger.info("before inserting to the  IBShipment_Report table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("IB_SHIPMENT:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(ib_shipment_Job, params);
			logger.info("after insertion to the ib_shipment table ::"+System.currentTimeMillis());
		} catch (Exception exception)
		{
			logger.info("unable to insert data to the BI_REP_IB_SHIPMENT table");
			notification(exception);
		}
	}

	@Scheduled(fixedRate = 3600000)//1800000- 30 mins
	public void perform_order_Details_Report() throws Exception {
		try {
			logger.info("before inserting to the order_Details table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("ORDER_DETAILS:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(order_Details_Job, params);
			logger.info("after insertion to the order_Details table ::"+System.currentTimeMillis());
		}
		catch (Exception exception) {
			logger.info("unable to insert data to the BI_REP_ORDER_DETAILS table");
			notification(exception);
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_iblpn_Details_Report() throws Exception {

		try {
			logger.info("before inserting to the iblpn_Details table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("IBLPN_DETAILS:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(iblpn_Details_Job, params);
			logger.info("after insertion to the iblpn_Details table ::"+System.currentTimeMillis());
		} catch (Exception exception) {
			logger.info("unable to insert data to the BI_REP_IBLPN_DETAILS table");	
			notification(exception);
		}
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_iblpn_Reports_Report() throws Exception {
		try {
			logger.info("before inserting to the iblpn_Reports table ::"+System.currentTimeMillis());
			JobParameters params = new JobParametersBuilder()
					.addString("IBLPN_REPORTS:", String.valueOf(System.currentTimeMillis()))
					.toJobParameters();
			jobLauncher.run(iblpn_Reports_Job, params);
			logger.info("after insertion to the iblpn_reports table ::"+System.currentTimeMillis());
		} catch (Exception exception) {
			logger.info("unable to insert data to the BI_REP_IBLPN_REPORT table");	
			notification(exception);
		}
	}
	public void notification(Exception exception) throws MessagingException
	{
		logger.info("Sending mail to admin..");
		Properties properties =new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "outlook.office365.com");
		properties.put("mail.smtp.port", "587");

		Session session=Session.getDefaultInstance(properties, new Authenticator(){
			@Override
			protected PasswordAuthentication getPasswordAuthentication(){
				return  new PasswordAuthentication(admin_Account, admin_password);
			}		
		});
		Message message = prepareMessage(session, admin_Account, to_recipient,cc_recipients, exception);
		Transport.send(message);
		logger.info("mail sent successfully..");	
	}
	private static Message prepareMessage(Session session, String admin_Account, String to_recipient, String cc_recipients, Exception exception){
		try{
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(admin_Account));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_recipient));
			InternetAddress[] iAdressArray = InternetAddress.parse(cc_recipients);
			message.setRecipients(Message.RecipientType.CC, iAdressArray); 
			message.setSubject("WMS BI-REPORTS EMAIL NOTIFICATION");
			message.setContent(
					"<h3>Please check the server log and inform the respective stakeholders for the quick help.</h3>\r\n"
							+ "	<h3>The issue may arised due to the following reasons</h3>\r\n"
							+ "	<ul>\r\n"
							+ "  <li>Unable to download/move WMS BI-report files from FTP server. Please check the FTP server logs</li>\r\n"
							+ "	 <li>Database connectivity issue - unable to insert data</li>\r\n"
							+ "	 <li>Table namespace issue - unable to insert data</li>\r\n"
							+ "	 <li>exact error : </li>\r\n"+exception.getMessage()
							+ "	 </ul>","text/html");
			return message;
		} 
		catch (Exception e){
			logger.info("unable to send mail..");
		}
		return null;
	}
}
