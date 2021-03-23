/*
package com.ito.bi.reports.integration.ftp;

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
		logger.info("Logging out from ftp server..");
		ftpClient.logout();
		return null;
	}
	public static void downloadDirectory(FTPClient ftpClient, String parentDir, String saveDir) throws IOException, MessagingException 
	{
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
