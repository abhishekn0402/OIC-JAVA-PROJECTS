package com.test;

import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.IOException;

import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;


public class FTPUtils
{
	private static final FTPUtils CLIENT = new FTPUtils();

	public static final void downloadImage(String fname) throws IOException, FileNotFoundException
	{
		String curr_input_img = null;
		BufferedImage original_img = null;
		String finalfolderpath = "/home/users/interface_wms/Test";// AppConstants.FTP_PATH + path;
		try {
			
			String user = "interface_wms";
		    String host = "147.154.104.74";
		    int port = 5032;
		    String pass = "WinzerCorp2020";
		    JSch jsch = new JSch();
		    Session session = jsch.getSession(user, host, port);
		    session.setConfig("StrictHostKeyChecking", "no");
		    session.setPassword(pass);
		    session.connect();
		    System.out.println("Connection established.");
		    System.out.println("Creating SFTP Channel.");
		    
		    
		    /***
		     * 
		     * 
		     * String user = "interface_wms";
		    String host = "147.154.104.74";
		    int port = 5032;
		    String pass = "WinzerCorp2020";
		    JSch jsch = new JSch();
		    Session session = jsch.getSession(user, host, port);
		    session.setConfig("StrictHostKeyChecking", "no");
		    session.setPassword(pass);
		    session.connect();
		    System.out.println("Connection established.");
		    System.out.println("Creating SFTP Channel.");*/
		    
		} catch (Exception ex) {
			System.out.println(ex);
		}
	}
	
	public static void main(String[] args) 
	{
		try {
			FTPUtils.CLIENT.downloadImage("image1.jpg");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
