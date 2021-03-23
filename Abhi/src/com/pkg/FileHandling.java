package com.pkg;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class FileHandling
{	
	public static void sendMail(String recipient) throws Exception 
	{
        System.out.println("preparing to send mail........");
		Properties properties =new Properties();
		properties.put("mail.smtp.auth", "true");
		properties.put("mail.smtp.starttls.enable", "true");
		properties.put("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.port", "587");
		String myAccount="abhishek.abhi0402@gmail.com";
		String password="Abhi@7337800402";
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

	private static Message prepareMessage(Session session, String myAccount, String recipient)
	{
		
		try {
			Message message=new MimeMessage(session);
			message.setFrom(new InternetAddress(myAccount));
			message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
			message.setSubject("test mail");
			message.setText("upload your files");
			return message;
		} 
		catch (Exception e)
		{
			Logger.getLogger(FileHandling.class.getName()).log(Level.SEVERE, null, e);
		}
		return null;
	}
}
