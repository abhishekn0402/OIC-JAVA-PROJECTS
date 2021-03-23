package com.pkg;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.Driver;
import com.mysql.jdbc.Statement;

public class WriteImage
{
 
	public static void main(String[] args) throws IOException
	{
		Connection con=null;
		Statement stmt= null;
		InputStream inputStream=null;
		FileOutputStream fileOutputStream=null;
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc","root","root");
			String query = "select pic from mytable where id=1";
			//statement will enable you to send the query to the database
			//Since it is select and static sql query I am using Statement object
			PreparedStatement pstmt=con.prepareStatement(query);
			ResultSet rs=pstmt.executeQuery();
			File file=new File("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\ImageHandler\\a.jpg");
			fileOutputStream=new FileOutputStream(file);
			if(rs.next())
			{
				inputStream= rs.getBinaryStream("pic");
				byte[] bytedata=new byte[1024];
				//byte[] imagedata = rs.getBytes("pic");
				//what type of content you want to send to client to view
				while(inputStream.read()>0)
				{
					fileOutputStream.write(bytedata);
				}
			}
			System.out.println("done");
			
		} catch (SQLException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
