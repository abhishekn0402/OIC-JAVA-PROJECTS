package com.ito.bi.reports.integration;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.nio.channels.OverlappingFileLockException;

import javax.mail.MessagingException;

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
import org.springframework.core.io.Resource;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import com.ito.bi.reports.integration.ftp.FTPConfigProperties;

@SpringBootApplication
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
	//
	@Value("file:${ib.shipment.input.file}")
	private Resource iBShipment_Report_Resource;

	@Value("file:${order.details.input.file}")
	private Resource order_Details_report_Resource;

	@Value("file:${iblpn.details.input.file}")
	private Resource iblpn_Details_report_Resource;

	@Value("file:${iblpn.reports.input.file}")
	private Resource iblpn_Reports_Resource;

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
		catch (Exception e)
		{
			logger.info(e.getMessage());
			logger.info("Unble to move or delete files in FTP server due to unexpected issues from FTP server"
					+ "and contact FTP server admin");
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

				if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Reserve_Screen_Report"))
				{
					localDirPath = localDirPath+ "reserve_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Active_Screen_Report"))
				{
					localDirPath = localDirPath+ "active_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_IB_Shipment_OIC"))
				{
					localDirPath = localDirPath+ "ib_Shipment_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_IBLPNs_Details_OIC"))
				{
					localDirPath = localDirPath+ "iblpn_Details_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_Gen2_Order_Detail_OIC"))
				{
					localDirPath = localDirPath+ "order_Details_Report.csv";
				}
				else if(currentFileName.startsWith("AQM_JEDDAH_BI Report_IBLPN_Report"))
				{
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
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_IBLPN_Report"))
				{
					boolean success = downloadSingleFile(ftpClient, ftpFilePath, localDirPath);
					if (success)
					{
						logger.info(ftpFilePath+" File downloaded successfully..");						
						boolean fileMoved=ftpClient.rename(biFolder.concat(currentFileName),processedFolder.concat(currentFileName));
						logger.info(currentFileName+" File move to PROCESSED folder successfully..");
						if(!fileMoved)
						{
							throw new FileUploadException("Unble to move or delete files in FTP server due to unexpected issues from FTP server");	
						}	
					} 
					else
					{
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
			throw ex;
		} 
		finally{
			if (outputStream != null){
				outputStream.close();
			}
		}    
	}

	@Scheduled(fixedRate = 3600000)
	public void performActiveReport() throws Exception{
		logger.info("before inserting to the  active screen table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("ACTIVE:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(activeJob, params);
		logger.info("after insertion to the active screen table ::"+System.currentTimeMillis());
	}

	@Scheduled(fixedRate = 3600000)
	public void performReserveReport() throws Exception {
		logger.info("before inserting to the  reserve screen table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("RESERVE:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(reserveJob, params);
		logger.info("after insertion to the  reserve screen table ::"+System.currentTimeMillis());
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_IB_Shipment_Report() throws Exception {
		logger.info("before inserting to the  IBShipment_Report table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("IB_SHIPMENT:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(ib_shipment_Job, params);
		logger.info("after insertion to the ib_shipment table ::"+System.currentTimeMillis());
	}

	@Scheduled(fixedRate = 3600000)//1800000- 30 mins
	public void perform_order_Details_Report() throws Exception {
		logger.info("before inserting to the order_Details table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("ORDER_DETAILS:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(order_Details_Job, params);
		logger.info("after insertion to the order_Details table ::"+System.currentTimeMillis());
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_iblpn_Details_Report() throws Exception {
		logger.info("before inserting to the iblpn_Details table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("IBLPN_DETAILS:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(iblpn_Details_Job, params);
		logger.info("after insertion to the iblpn_Details table ::"+System.currentTimeMillis());
	}

	@Scheduled(fixedRate = 3600000)
	public void perform_iblpn_Reports_Report() throws Exception {
		logger.info("before inserting to the iblpn_Reports table ::"+System.currentTimeMillis());
		JobParameters params = new JobParametersBuilder()
				.addString("IBLPN_REPORTS:", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(iblpn_Reports_Job, params);
		logger.info("after insertion to the iblpn_reports table ::"+System.currentTimeMillis());
	}
}