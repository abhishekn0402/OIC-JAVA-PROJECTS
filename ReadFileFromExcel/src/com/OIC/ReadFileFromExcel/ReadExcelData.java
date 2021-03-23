package com.OIC.ReadFileFromExcel;

import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.mysql.jdbc.Driver;


public class ReadExcelData
{
	static Connection con=null;
	static PreparedStatement pstmt=null;
	static FileReader fileReader=null;
	public static void fileReaderMethod() throws IOException
	{
		try {
			Driver driver=new Driver();
			DriverManager.registerDriver(driver);
			fileReader=new FileReader("property.file");
			Properties properties=new Properties();
			properties.load(fileReader);
			String dburl=properties.getProperty("dburl");
			String uName=properties.getProperty("username");
			String password=properties.getProperty("password");
			con=DriverManager.getConnection(dburl, uName, password);
			String query=properties.getProperty("query");
			pstmt=con.prepareStatement(query);
			String exceldata=properties.getProperty("excel_data");
			System.out.println(exceldata);
			//File file=new File();
			//FileInputStream fileInputStream=new FileInputStream("C://Users/Abhilash Pramod/Desktop/abhishek/New folder/New folder/reserve_screen_report.xlsx");
			FileInputStream fileInputStream = new FileInputStream(exceldata);
			//FileReader fileReader1=new FileReader(fileInputStream);
			
			Workbook workbook=new XSSFWorkbook(fileInputStream);
			Sheet firstSheet=workbook.getSheetAt(0);
			
			int [] a=dataInserter(pstmt,firstSheet);
			System.out.println(a.length);
			
		} 
		catch (SQLException e) 
		{
			// TODO Auto-generated catch block
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
				
				if (fileReader != null) {
					fileReader.close();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	public static int[] dataInserter(PreparedStatement pstmt, Sheet firstSheet) throws SQLException
	
	{
		for (int rowIndex = 1; rowIndex <=firstSheet.getLastRowNum(); rowIndex++)
		{
			Row row=firstSheet.getRow(rowIndex);
			
			for (int cellnum = 0; cellnum < row.getLastCellNum(); cellnum++)
			{
				Cell cell=row.getCell(cellnum);
			if (cell == null)
			{
				cell=row.getCell(cellnum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
			}
             int columnIndex= cell.getColumnIndex();
             System.out.println(columnIndex);
             
             switch (columnIndex)
 			{
             
 			case 0:
 				int id= (int) cell.getNumericCellValue();
 				pstmt.setInt(1, id);
 				
 				break;
 				
 			case 1:
 				String location_Type=cell.getStringCellValue();
 				pstmt.setString(2, location_Type);
 				break;
 				
 			case 2:
 				String location_Size_Type=cell.getStringCellValue();
 				pstmt.setString(3, location_Size_Type);
                break;
 			case 3:
 				String location_Area=cell.getStringCellValue();
 				pstmt.setString(4, location_Area);
                break;
 			case 4:
 				cell.setCellType(cell.getCellType().STRING);
 				String item_Code=cell.getStringCellValue();
 				
 				pstmt.setString(5, item_Code);
                break;
 			case 5:
 				int is_Parent=(int) cell.getNumericCellValue();
 				pstmt.setInt(6, is_Parent);
                break;
 			case 6:
 				String alternative_Item_Code=cell.getStringCellValue();
 				pstmt.setString(7, alternative_Item_Code);
                break;
                
 			case 7:
 				String item_Description=cell.getStringCellValue();
 				pstmt.setString(8, item_Description);
                break;
 			case 8:
 				cell.setCellType(cell.getCellType().STRING);
 				String batch_Nbr=cell.getStringCellValue();
 				System.out.println(cell.getCellType());
 				pstmt.setString(9, batch_Nbr);
                break; 
 			case 9:
 				String expiry_Date=cell.getStringCellValue();
 				pstmt.setString(10, expiry_Date);
                break; 
 			case 10:
 				int current_Qty=(int) cell.getNumericCellValue();
 				pstmt.setInt(11, current_Qty);
                break; 
 			case 11:
 				int allocated_Qty=(int) cell.getNumericCellValue();
 				pstmt.setInt(12, allocated_Qty);
                break; 
 			case 12:
 				int current_LPN=(int) cell.getNumericCellValue();
 				pstmt.setInt(13, current_LPN);
                break; 
 			case 13:
 				String alloc_LPN=cell.getStringCellValue();
 				pstmt.setString(14, alloc_LPN);
                break;  
 			case 14:
 				String last_CC_Counted=cell.getStringCellValue();
 				pstmt.setString(15, last_CC_Counted);
                break;
 			case 15:
 				String mod_TimeStamp=cell.getStringCellValue();
 				pstmt.setString(16, mod_TimeStamp);
                break;
 			case 16:
 				int to_be_Counted=(int) cell.getNumericCellValue();
 				pstmt.setInt(17, to_be_Counted);
                break;
 			case 17:
 				String to_Be_Counted_TS= cell.getStringCellValue();
 				pstmt.setString(18, to_Be_Counted_TS);
                break;
 			case 18:
 				int loc_Lock=(int) cell.getNumericCellValue();
 				pstmt.setInt(19, loc_Lock);
                break;
 			case 19:
 				String location_Barcode= cell.getStringCellValue();
 				pstmt.setString(20, location_Barcode);
                break;  
 			case 20:
 				String facility= cell.getStringCellValue();
 				pstmt.setString(21, facility);
                break;  
                
 			case 21:
 				String display_Text= cell.getStringCellValue();
 				pstmt.setString(22, display_Text);
                break; 
                
 			case 22:
 				String mod_User= cell.getStringCellValue();
 				pstmt.setString(23, mod_User);
                break; 
 			case 23:
 				String loc_CC_User= cell.getStringCellValue();
 				pstmt.setString(24, loc_CC_User);
                break;
 			case 24:
 				double loc_Max_LPN= cell.getNumericCellValue();
 				pstmt.setDouble(25, loc_Max_LPN);
                break;
 			case 25:
 				double loc_Max_Unit= cell.getNumericCellValue();
 				pstmt.setDouble(26, loc_Max_Unit);;
                break;
 			case 26:
 				int min_Unit= (int) cell.getNumericCellValue();
 				pstmt.setInt(27, min_Unit);
                break;
                
 			case 27:
 				String attribute_A= cell.getStringCellValue();
 				pstmt.setString(28, attribute_A);
                break;
                
 			case 28:
 				cell.setCellType(cell.getCellType().NUMERIC);
 				int attribute_B= (int) cell.getNumericCellValue();
 				pstmt.setInt(29, attribute_B);
                break;
                  
 			case 29:
 				String attribute_C= cell.getStringCellValue();
 				pstmt.setString(30, attribute_C);
                break;
                  
 			case 30:
 				String attribute_D= cell.getStringCellValue();
 				pstmt.setString(31, attribute_D);
                break;
                  
                
 			case 31:
 				String attribute_E= cell.getStringCellValue();
 				pstmt.setString(32, attribute_E);
                break;
 			case 32:
 				String attribute_F= cell.getStringCellValue();
 				pstmt.setString(33, attribute_F);
                break;
                  
 			case 33:
 				String attribute_G= cell.getStringCellValue();
 				pstmt.setString(34, attribute_G);
                break;
                  
                
                
 			case 34:
 				String attribute_H= cell.getStringCellValue();
 				pstmt.setString(35, attribute_H);
                break;
 			case 35:
 				String attribute_I= cell.getStringCellValue();
 				pstmt.setString(36, attribute_I);
                break;
 			case 36:
 				String attribute_J= cell.getStringCellValue();
 				pstmt.setString(37, attribute_J);
                break;
 			case 37:
 				String attribute_K= cell.getStringCellValue();
 				pstmt.setString(38, attribute_K);
                break;
 			case 38:
 				String attribute_L= cell.getStringCellValue();
 				pstmt.setString(39, attribute_L);
                break;
 			case 39:
 				String attribute_M= cell.getStringCellValue();
 				pstmt.setString(40, attribute_M);
                break;
 			case 40:
 				String attribute_N= cell.getStringCellValue();
 				pstmt.setString(41, attribute_N);
                break;
 			case 41:
 				String attribute_O= cell.getStringCellValue();
 				pstmt.setString(42, attribute_O);
                break;
 			case 42:
 				int brand_Code=(int) cell.getNumericCellValue();
 				pstmt.setInt(43, brand_Code);
                break; 
 			case 43:
 				int batch_Nbr_Lock=(int) cell.getNumericCellValue();
 				pstmt.setInt(44, batch_Nbr_Lock);
                break; 
 			case 44:
 				String company= cell.getStringCellValue();
 				pstmt.setString(45, company);
                break;
 			case 45:
 				String lock_Code= cell.getStringCellValue();
 				pstmt.setString(46, lock_Code);
                break;
 			default:
 				break;
 			}
			}
			pstmt.addBatch();
		}
		 return pstmt.executeBatch();
	}

	public static void main(String[] args) throws IOException
	{
		fileReaderMethod();
	}
}
