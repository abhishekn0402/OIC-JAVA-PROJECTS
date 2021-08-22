package com.test;

import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.ChannelSftp.LsEntry;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;


@Component
public class SFTPClient
{
	
	@Autowired
	SFTPConfigProperties sftpConfig;
	
	public static final SFTPClient INSTANCE = new SFTPClient();
	
	private static final String SFTP = "sftp";
	private Logger logger = LoggerFactory.getLogger(SFTPClient.class);
    
	private JSch jsch = null;
	private Session session = null;
	private Channel channel = null;
	private ChannelSftp channelSftp = null;
    
    private SFTPClient() 
    {
    	
    }
    
     
    public void sftpFileTransfer(String host,int port, String user, String openSSHPrivateKey, String remoteDir,
			String localDir, String file) 
	{
		try {
			session = getSession(host, port, openSSHPrivateKey, user);

			if (session != null) {
				transferFile(localDir, remoteDir, file);
			}

		} catch (JSchException e)
		{
			
			logger.error("JSchException : Failed to transfer the file::"+file);
			logger.error("JSchException :"+e);
		} 
		catch (SftpException e) {
			logger.error("JSchException : Failed to transfer the file::"+file);
			logger.error("SftpException :"+e);
		} finally {

			if (channelSftp != null) {
				channelSftp.disconnect();
				channelSftp.exit();
			}
			if (channel != null)
				channel.disconnect();

			if (session != null)
				session.disconnect();
		}
	}
    
    
    public boolean sftpGetFile(String host,int port, String user, String openSSHPrivateKey, String remoteDir,
			String localDir, String file) 
	{
		boolean found = false;
    	try 
		{	
			session = getSession(host, port, openSSHPrivateKey, user);

			if (session != null) 
			{
				logger.info("localDir...."+localDir);
				logger.info("remoteDir...."+remoteDir);
				logger.info("file...."+file);
				getFile(localDir, remoteDir, file);
				found = true;
			}

		} catch (JSchException e) {
			// TODO Auto-generated catch block
			logger.error("JSchException : Failed to copy file::"+file);
			logger.error("JSchException :"+e);
			found = false;
		} catch (SftpException e) {
			logger.error("SftpException : Failed to copy file::"+file);
			logger.error("SftpException :"+e);
			found = false;
		} finally {

			if (channelSftp != null) {
				channelSftp.disconnect();
				channelSftp.exit();
			}
			if (channel != null)
				channel.disconnect();

			if (session != null)
				session.disconnect();
		}
    	return found;
	}

    
    private void transferFile(String localDir,String remoteDir,String fileName) throws JSchException, SftpException
	{
		channel = session.openChannel(SFTP);
		channel.connect();
		logger.info("shell channel connected....");
		channelSftp = (ChannelSftp) channel;
		logger.info("From ::"+localDir+"/"+fileName);
		logger.info("To ::"+remoteDir+"/"+fileName);
		channelSftp.put(localDir+"/"+fileName, remoteDir+"/"+fileName, ChannelSftp.OVERWRITE);
		logger.info("Changed the directory...");
	}
    
    
    private void getFile(String localDir,String remoteDir,String fileName) throws JSchException, SftpException
	{
		channel = session.openChannel(SFTP);
		channel.connect();
		logger.info("shell channel connected....");
		channelSftp = (ChannelSftp) channel;
		Vector<LsEntry> files = channelSftp.ls(remoteDir+"/"+fileName);
		if(files != null && files.size() > 0)
		{
			for (LsEntry file : files) 
			{
				logger.info("Copying file "+file.getFilename()+" from "+remoteDir);
				//channelSftp.get(remoteDir+"/"+file.getFilename(), localDir,null,ChannelSftp.RESUME);
				channelSftp.get(remoteDir+"/"+file.getFilename(), localDir);
			}
		}
	}

	private Session getSession(String sftpHost,int sftpPort,String openSSHPrivateKey,String userName) throws JSchException 
	{
		jsch = new JSch();
		logger.info("Key...::"+openSSHPrivateKey);
		jsch.addIdentity(openSSHPrivateKey);
		session = null;
		session = jsch.getSession(userName, sftpHost, sftpPort);
		session.setConfig("StrictHostKeyChecking", "no");
		session.connect();
		logger.info("Got the session...");
		return session;
	}
 }