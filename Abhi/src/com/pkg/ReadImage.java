package com.pkg;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReadImage
{

	public static void main(String[] args) throws SQLException, IOException
	{
		try {
			Class.forName("com.mysql.jdbc.Driver");
			Connection con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			
			String query = "insert into mytable(pic) value(?)";
			PreparedStatement psmt = con.prepareStatement(query);
			FileInputStream fileInputStream=new FileInputStream("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\ImageHandler\\dog.jpg");
			psmt.setBinaryStream(1, fileInputStream, fileInputStream.available());
			
			String query2 = "insert into mytable(pic) value(?)";
			PreparedStatement psmt2 = con.prepareStatement(query2);
			FileInputStream fileInputStream1=new FileInputStream("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\ImageHandler\\cat.jpg");
			//psmt.setBinaryStream(int index,"inputstream object",int specified number of bytes)
			psmt2.setBinaryStream(1, fileInputStream1, fileInputStream1.available());
			
			psmt.executeUpdate();
			psmt2.executeUpdate();
			System.out.println("Done");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
