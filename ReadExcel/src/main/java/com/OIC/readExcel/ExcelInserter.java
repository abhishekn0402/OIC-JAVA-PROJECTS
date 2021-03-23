package com.OIC.readExcel;


import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExcelInserter 
{
	public static void result() throws IOException
	{
		FileReader fileReader=null;
		Connection con=null;
		ResultSet res=null;
		PreparedStatement pstmt=null;
		try 
		{
			//String dbrul="jdbc:mysql://localhost:3306/jdbc?";
			//String uname="root1";
			//String password="root1";

			// reading the properties file here(for that created a file)
			fileReader=new FileReader("properiesfile.file");
			Properties properties=new Properties();
			properties.load(fileReader);
			String dburl=properties.getProperty("dburl");
			String username=properties.getProperty("username");
			String password=properties.getProperty("password");
			con=DriverManager.getConnection(dburl, username, password);
			String query=properties.getProperty("query");
			//String query="INSERT INTO business_partner (business_partner_id, business_partner_name, business_partner_code, contact_name, address1, address2, city, province, country, postal_code, parent_company, toll_free_number, phone, phone_extension, fax, website_url, payment_condition, gl_number, driver_min_age, api_enabled, status, one_way_fee_paid_by, country_code) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
			pstmt=con.prepareStatement(query);


			//reading the data from excel sheet(info present in the properties file)
			String bpdata=properties.getProperty("bp_data");
			FileInputStream fileInputStream=new FileInputStream(bpdata);
			Workbook workbook = new XSSFWorkbook(fileInputStream);


			//getting the first sheet in the excel sheet
			Sheet firstSheet = workbook.getSheetAt(0);
			//to store the value to the database
		    int[] resc=jdbcmethod( pstmt, firstSheet);
			
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				if (con != null) {
					con.close();
				}
				if (pstmt != null) {
					pstmt.close();
				}
				if (res != null) {
					res.close();
				}
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static int[] jdbcmethod(PreparedStatement pstmt, Sheet firstSheet) throws SQLException
	{
		try
		{
			for (int i = 1; i <= firstSheet.getLastRowNum(); i++)//getLastRowNum() will give you the total number of rows in a sheet
			{
				Row nextRow = firstSheet.getRow(i);//getRow(i) will give you the row at specified index

				for (int j = 0; j < nextRow.getLastCellNum(); j++)//returns total how many cells are there in specified row or index of last cell
				{

					Cell nextCellinsheet = nextRow.getCell(j);//return the cell at specified index

					if (nextCellinsheet == null)
					{
						nextCellinsheet = nextRow.getCell(j, Row.CREATE_NULL_AS_BLANK);
					}
					int columnIndexinsheet = nextCellinsheet.getColumnIndex();//index of the cell
					//System.out.println(columnIndexinsheet);
					switch (columnIndexinsheet)
					{
					case 0:
						int business_partner_id = (int) nextCellinsheet.getNumericCellValue();//the value of the cell as a number
						//System.out.println("value"+nextCellinsheet.getNumericCellValue());
						pstmt.setInt(1, business_partner_id);
						break;
					case 1:
						String business_partner_name = nextCellinsheet.getStringCellValue();
						pstmt.setString(2, business_partner_name);
						break;
					case 2:
						String business_partner_code = nextCellinsheet.getStringCellValue();
						pstmt.setString(3, business_partner_code);
						break;
					case 3:
						String contact_name = nextCellinsheet.getStringCellValue();
						pstmt.setString(4, contact_name);
						break;
					case 4:
						String address1 = nextCellinsheet.getStringCellValue();
						pstmt.setString(5, address1);
						break;
					case 5:
						String address2 = nextCellinsheet.getStringCellValue();
						pstmt.setString(6, address2);
						break;
					case 6:
						String city = nextCellinsheet.getStringCellValue();
						pstmt.setString(7, city);
						break;
					case 7:
						String province = nextCellinsheet.getStringCellValue();
						pstmt.setString(8, province);
						break;
					case 8:
						String country = nextCellinsheet.getStringCellValue();
						pstmt.setString(9, country);
						break;
					case 9:
						String postal_code = nextCellinsheet.getStringCellValue();
						pstmt.setString(10, postal_code);
						break;
					case 10:
						String parent_company = nextCellinsheet.getStringCellValue();
						pstmt.setString(11, parent_company);
						break;
					case 11:
						String toll_free_number = nextCellinsheet.getStringCellValue();
						pstmt.setString(12, toll_free_number);
						break;
					case 12:
						nextCellinsheet.setCellType(nextCellinsheet.CELL_TYPE_STRING);//converting into string type
						String phone = nextCellinsheet.getStringCellValue();
						pstmt.setString(13, phone);
						break;
					case 13:
						nextCellinsheet.setCellType(nextCellinsheet.CELL_TYPE_STRING);
						String phone_extension = nextCellinsheet.getStringCellValue();
						pstmt.setString(14, phone_extension);
						break;
					case 14:
						String fax = nextCellinsheet.getStringCellValue();
						pstmt.setString(15, fax);
						break;
					case 15:
						String website_url = nextCellinsheet.getStringCellValue();
						pstmt.setString(16, website_url);
						break;
					case 16:
						int payment_condition = (int) nextCellinsheet.getNumericCellValue();
						pstmt.setInt(17, payment_condition);
						break;
					case 17:
						String gl_number = nextCellinsheet.getStringCellValue();
						pstmt.setString(18, gl_number);
						break;
					case 18:
						int driver_min_age = (int) nextCellinsheet.getNumericCellValue();
						pstmt.setInt(19, driver_min_age);
						break;
					case 19:
						int api_enabled = (int) nextCellinsheet.getNumericCellValue();
						pstmt.setInt(20, api_enabled);
						break;
					case 20:
						int status = (int) nextCellinsheet.getNumericCellValue();
						pstmt.setInt(21, status);
						break;
					case 21:
						String one_way_fee_paid_by = nextCellinsheet.getStringCellValue();
						pstmt.setString(22, one_way_fee_paid_by);
						break;
					case 22:
						String country_code = nextCellinsheet.getStringCellValue();
						pstmt.setString(23, country_code);
						break;
					}
				}
				// adding to batch after completion of each row.
				pstmt.addBatch();
			}
			// execution of whole rows i.e batch
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return pstmt.executeBatch();

	}
	public static void main( String[] args )throws IOException
	{
		result();
	}
}
