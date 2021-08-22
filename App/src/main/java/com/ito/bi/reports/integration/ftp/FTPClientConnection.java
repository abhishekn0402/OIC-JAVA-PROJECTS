/*
package com.ito.bi.reports.integration.ftp;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import javax.mail.MessagingException;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FTPClientConnection 
{ 
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
	public static Logger logger = LoggerFactory.getLogger(FTPClientConnection.class);
	
	@Bean
	public String ftpHandler() throws IOException, MessagingException
	{
		ftpClient.connect(fTPConfigProperties.getHostName(),fTPConfigProperties.getPort());
		ftpClient.login(fTPConfigProperties.getUserName(),fTPConfigProperties.getPassword());
		ftpClient.enterLocalPassiveMode();
		ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
		logger.info("Connection established to ftp server..");
		downloadDirectory(ftpClient, remoteDirPath, saveDirPath);
		
		ftpClient.rename(biFolder.concat("reserve_Report.csv"),processedFolder.concat("reserve_Report.csv"));
		logger.info("proccessed reserve_Report.csv file");	
		ftpClient.rename(biFolder.concat("active_Report.csv"),processedFolder.concat("active_Report.csv"));
		logger.info("proccessed active_Report.csv file");
		
		
		ftpClient.rename(biFolder.concat("ib_Shipment_Report.csv"),processedFolder.concat("ib_Shipment_Report.csv"));
		logger.info("proccessed ib_Shipment_Report.csv file");
		
		ftpClient.rename(biFolder.concat("order_Details_Report.csv"),processedFolder.concat("order_Details_Report.csv"));
		logger.info("proccessed order_Details_Report.csv file");
		
		ftpClient.rename(biFolder.concat("iblpn_Details_Report.csv"),processedFolder.concat("iblpn_Details_Report.csv"));
		logger.info("proccessed iblpn_Details_Report.csv file");
		
		logger.info("Logging out from ftp server..");
		ftpClient.logout();
		return null;
	}
	public static void downloadDirectory(FTPClient ftpClient, String parentDir, String saveDir) throws IOException, MessagingException 
	{
		
		//
		String dirToList = parentDir;
		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		if (subFiles != null && subFiles.length >1){
			for (FTPFile aFile : subFiles) {
				String currentFileName = aFile.getName();  
				String ftpFilePath = parentDir + currentFileName;
				String localDirPath = saveDir + parentDir + File.separator
						+ File.separator + currentFileName;
				if ((aFile.isDirectory()) || !aFile.getName().endsWith(".csv")){
					continue;
				}
				else {
					boolean success = downloadSingleFile(ftpClient, ftpFilePath, localDirPath);
					if (success){
						logger.info(ftpFilePath+" File downloaded successfully..");
					} 
					else{
						logger.info("Unable to download "+ftpFilePath+" file..");
					}
				}				
			}		
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
		File parentDir = downloadFile.getParentFile();
		   
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
}

*/