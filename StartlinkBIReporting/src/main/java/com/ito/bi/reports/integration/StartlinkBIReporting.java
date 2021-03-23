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
    FTPConfigProperties fTPConfigProperties;
    
    @Value("${ftp.remoteDir.BI}")
	private String biFolder;

	@Value("${ftp.remoteDir.PROCESSED}")
	private String processedFolder;

	@Value("${ftp.remoteDir}")
	private String remoteDirPath;

	@Value("${ftp.saveDir}")
	private String saveDirPath;
	
	public static FTPClient ftpClient=new FTPClient();

    public static Logger logger = LoggerFactory.getLogger(StartlinkBIReporting.class);
    public static void main(String[] args)
    {	
        SpringApplication.run(StartlinkBIReporting.class, args);
    } 

    //for testing
    @Scheduled(fixedRate = 120000)
    public void ftpHandler() throws IOException, MessagingException 
    {
    	ftpClient.connect(fTPConfigProperties.getHostName(),fTPConfigProperties.getPort());
		ftpClient.login(fTPConfigProperties.getUserName(),fTPConfigProperties.getPassword());
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		logger.info("Connection established to ftp server..");
		downloadDirectory(ftpClient, remoteDirPath, saveDirPath);
		ftpClient.logout(); 	 
    }
    
    public void downloadDirectory(FTPClient ftpClient, String parentDir, String saveDir) throws IOException, MessagingException 
	{
		String dirToList = parentDir;
		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		if (subFiles != null && subFiles.length >1){
			for (FTPFile aFile : subFiles) {
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
				
				if ((aFile.isDirectory()) || !aFile.getName().endsWith(".csv")){
					continue;
				}
				else if (aFile.getName().startsWith("AQM_JEDDAH_BI Report_Reserve_Screen_Report")
						|| aFile.getName().startsWith("AQM_JEDDAH_BI Report_Active_Screen_Report"))
				{
					boolean success = downloadSingleFile(ftpClient, ftpFilePath, localDirPath);
					if (success)
					{
						logger.info(ftpFilePath+" File downloaded successfully..");
						ftpClient.rename(biFolder.concat(currentFileName),processedFolder.concat(currentFileName));
						ftpClient.deleteFile(biFolder.concat(currentFileName));
					} 
					else
					{
						logger.info("Unable to download "+ftpFilePath+" file..");
					}
				}				
			}		
		}
		else{
			logger.info("Sending mail to admin..");
			Properties properties =new Properties();
			properties.put("mail.smtp.auth", "true");
			properties.put("mail.smtp.starttls.enable", "true");
			properties.put("mail.smtp.host", "smtp.gmail.com");
			properties.put("mail.smtp.port", "587");
			String admin_Account="abhishek.abhi0402@gmail.com";
			String password="Abhi@7337800402";
			String to_recipient="Abhishek.Nagaraj@itorizon.com";
			String cc_recipients = "satya.nekkanti@itorizon.com,sahaja.bhupalam@itorizon.com";
			
			Session session=Session.getDefaultInstance(properties, new Authenticator(){
				@Override
				protected PasswordAuthentication getPasswordAuthentication(){
					return  new PasswordAuthentication(admin_Account, password);
				}		
			});
			Message message = prepareMessage(session, admin_Account, to_recipient,cc_recipients);
			Transport.send(message);
			logger.info("mail sent successfully..");			
		}
	}
	private static Message prepareMessage(Session session, String admin_Account, String to_recipient, String cc_recipients){
		try{
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(admin_Account));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(to_recipient));
            InternetAddress[] iAdressArray = InternetAddress.parse(cc_recipients);
            message.setRecipients(Message.RecipientType.CC, iAdressArray); 
			message.setSubject("UPLOAD YOUR FILES");
			message.setText("Kindly upload your csv files to process further"+ "\r\n"+ "Thanks and Regards"+"\r\n"+"OIC ADMIN");
			return message;
		} 
		catch (Exception e){
			logger.info("Please upload your files....");
		}
		return null;
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
    
    @Scheduled(fixedRate = 400000)
    public void performActiveReport() throws Exception{
    	logger.info("before inserting to the  active screen table ::"+System.currentTimeMillis());
        JobParameters params = new JobParametersBuilder()
                .addString("ACTIVE:", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(activeJob, params);
        logger.info("after insertion to the active screen table ::"+System.currentTimeMillis());
    }
    
    @Scheduled(fixedRate = 300000)
    public void performReserveReport() throws Exception {
    	logger.info("before inserting to the  reserve screen table ::"+System.currentTimeMillis());
        JobParameters params = new JobParametersBuilder()
                .addString("RESERVE:", String.valueOf(System.currentTimeMillis()))
                .toJobParameters();
        jobLauncher.run(reserveJob, params);
        logger.info("before insertion to the  reserve screen table ::"+System.currentTimeMillis());
    }
}