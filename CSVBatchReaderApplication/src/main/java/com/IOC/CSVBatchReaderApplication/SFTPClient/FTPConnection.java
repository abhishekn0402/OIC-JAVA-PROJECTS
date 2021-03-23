package com.IOC.CSVBatchReaderApplication.SFTPClient;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.SocketException;
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
import org.springframework.beans.factory.annotation.Value;

import com.IOC.CSVBatchReaderApplication.Exception.FileUploadException;
import com.jcraft.jsch.JSchException;

public class FTPConnection
{    
	public static String host = "02cb1a2.netsolhost.com";
	public static String user = "starlinks";
	public static String pass = "$#@Starlinks";
	public static int port = 21;

	public static Logger logger = LoggerFactory.getLogger(FTPConnection.class);

	public static String reserve="";
	public static String active="";
	public static FTPClient ftpClient=new FTPClient();
	
	public static void downloadDirectory(FTPClient ftpClient, String parentDir,String currentDir, String saveDir) throws IOException, MessagingException 
	{
		String dirToList = parentDir;
		FTPFile[] subFiles = ftpClient.listFiles(dirToList);
		System.out.println(subFiles.length);
		if (subFiles != null && subFiles.length >1)
		{
			System.out.println("true");
			for (FTPFile aFile : subFiles) 
			{
				String currentFileName = aFile.getName();                 
				String filePath = parentDir + "/" + currentDir + "/"+ currentFileName;
				if (currentDir.equals(""))
				{
					filePath = parentDir + "/" + currentFileName;
				}                   
				String newDirPath = saveDir + parentDir + File.separator
						+ currentDir + File.separator + currentFileName;
				if (currentDir.equals(""))
				{
					newDirPath = saveDir + parentDir + File.separator   
							+ currentFileName;
				}

				if ((aFile.isDirectory()) || !aFile.getName().endsWith(".csv"))
				{
					continue;
				}
				else 
				{
					boolean success = downloadSingleFile(ftpClient, filePath,
							newDirPath);
					if (success)
					{
						logger.info(filePath+" File downloaded successfully...");
					} 
					else
					{
						logger.info("Unable to download "+filePath+" file..");
					}
				}				
			}		
		}
		
		else
		{
		        System.out.println("preparing to send mail........");
				Properties properties =new Properties();
				properties.put("mail.smtp.auth", "true");
				properties.put("mail.smtp.starttls.enable", "true");
				properties.put("mail.smtp.host", "smtp.gmail.com");
				properties.put("mail.smtp.port", "587");
				String myAccount="abhishek.abhi0402@gmail.com";
				String password="Abhi@7337800402";
				String recipient="nagaraj.raju777@gmail.com";
				Session session=Session.getDefaultInstance(properties, new Authenticator()
				{
					@Override
					protected PasswordAuthentication getPasswordAuthentication()
					{
						return  new PasswordAuthentication(myAccount, password);
					}		
				});
				Message message = prepareMessage(session, myAccount, recipient);
				
				Transport.send(message);
				
				
				System.out.println("message sent successfully........");
			}
		}
		
	private static Message prepareMessage(Session session, String myAccount, String recipient)
	{
		try
		{
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("UPLOAD FILES");
			message.setText("Kindly upload your csv files to process further..");
			return message;
		} 
		catch (Exception e)
		{
			logger.info("Please upload your files....");
		}
		finally
		{
			
		}   
		return null;
	}

	
        
	public static boolean downloadSingleFile(FTPClient ftpClient,
			String remoteFilePath, String savePath) throws IOException
	{
		File downloadFile = new File(savePath); 
		File parentDir = downloadFile.getParentFile();
		if (!parentDir.exists()) 
		{
			parentDir.mkdir();
		}     
		OutputStream outputStream = new BufferedOutputStream(new FileOutputStream(downloadFile));
		try 
		{
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			return ftpClient.retrieveFile(remoteFilePath, outputStream);
		} catch (IOException ex)
		{
			throw ex;
		} finally
		{
			if (outputStream != null)
			{
				outputStream.close();
			}
		}    
	}
	public static String sort_Reserve_Screen_Report() throws FileUploadException  
	{  
		String reserve_Screen_Report="Reserve_Screen_Report";
		int innerFiles = 0;
		File file=new File("C:/Users/Abhilash Pramod/Desktop/sft/Reports/BI");
		File[] sortedFiles=file.listFiles();

		if(sortedFiles.length>0)
		{	
		for (innerFiles = 0; innerFiles < sortedFiles.length; innerFiles++)
		{
			if(sortedFiles[innerFiles].getName().contains(reserve_Screen_Report))
			{
				return sortedFiles[innerFiles].getAbsolutePath();			
			}
		}
		return sortedFiles[innerFiles].getAbsolutePath();
		}
		throw new FileUploadException("No Reserve_Screen_Report file exist.");
		}
	
	public static String sort_Active_Screen_Report() throws FileUploadException
	{
		String active_Screen_Report="Active_Screen_Report";
		int innerFiles = 0;

		File file=new File("C:/Users/Abhilash Pramod/Desktop/sft/Reports/BI");
		File[] sortedFiles=file.listFiles();
		if(sortedFiles.length>0)
		{	
		for (innerFiles = 0; innerFiles < sortedFiles.length; innerFiles++)
		{
			if(sortedFiles[innerFiles].getName().contains(active_Screen_Report))
			{
				return sortedFiles[innerFiles].getAbsolutePath();	
			}
		}	
		return sortedFiles[innerFiles].getAbsolutePath();	//path of the file location
		}
		throw new FileUploadException("No Active_Screen_Report file exist");
	}
		
	public static void main(String[] args) throws JSchException, SocketException, IOException, FileUploadException, MessagingException 
	{
		
		try 
		{
			
			ftpClient.connect(host, port);
			ftpClient.login(user, pass);
			ftpClient.enterLocalPassiveMode();
			ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
			logger.info("Connection established..");
		} 

		catch (IOException e) 
		{    
			logger.info("Connection refused..");
			logger.info(e.getMessage());
		} 
		
			String remoteDirPath = "/Reports/BI";
			String saveDirPath = "C:/Users/Abhilash Pramod/Desktop/sft";
			
			FTPConnection.downloadDirectory(ftpClient, remoteDirPath, "", saveDirPath);
			try
			{
			 reserve=sort_Reserve_Screen_Report();
			}
			catch (Exception e)
			{
				logger.info("Please upload Reserve_Screen_Report file..");
			}
			try
			{
			 active=sort_Active_Screen_Report();
			}
			catch (Exception e)
			{
				logger.info("Please upload your Active_Screen_Report file..");
			}
			try
			{
			int length_sub_Reserve_Screen_Report=reserve.length();
			int length_sub_Active_Screen_Report=active.length();	
			String sub_Reserve_Screen_Report=reserve.substring(48, length_sub_Reserve_Screen_Report);
			ftpClient.rename("/Reports/BI/".concat(sub_Reserve_Screen_Report),"/Reports/BI/PROCESSED/".concat(sub_Reserve_Screen_Report));
			logger.info("proccessed "+sub_Reserve_Screen_Report+" file");
	
			String sub_Active_Screen_Report=active.substring(48, length_sub_Active_Screen_Report);
			ftpClient.rename("/Reports/BI/".concat(sub_Active_Screen_Report),"/Reports/BI/PROCESSED/".concat(sub_Active_Screen_Report));
			logger.info("proccessed "+sub_Active_Screen_Report+" file");
			
			logger.info("Logging out..");
			ftpClient.logout(); 

			}
			catch (Exception e)
			{
				logger.info("Please upload all your files before process further..");
			}
	}
}



