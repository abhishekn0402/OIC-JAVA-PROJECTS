package com.OIC.fileDataReader.service;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.OIC.fileDataReader.dto.ExcelFileDataInserter;
import com.OIC.fileDataReader.repo.ExcelFileDataRepo;

@Service
public class ExcelFileDataService
{
	@Autowired
	private ExcelFileDataRepo excelFileDataRepo;
	
	public void addExcelData() throws SQLException, IOException
	{
		FileInputStream fileInputStream=new FileInputStream("C:\\Users\\Abhilash Pramod\\Desktop\\abhishek\\ABHISHEK\\reserve_screen_reportsss.xlsx");
		ExcelFileDataInserter excelFileDataInserter=new ExcelFileDataInserter();
		XSSFWorkbook workbook=new XSSFWorkbook(fileInputStream);
		Sheet sheet=workbook.getSheetAt(0);
		ArrayList<ExcelFileDataInserter> arrayList=new ArrayList<ExcelFileDataInserter>();
		
		for (int rowNum = 1; rowNum <=sheet.getLastRowNum(); rowNum++)
		{
			Row row=sheet.getRow(rowNum);
			for (int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++)
			{
				Cell cell=row.getCell(cellNum);
				if (cell == null)
				{
				row.getCell(cellNum, Row.MissingCellPolicy.CREATE_NULL_AS_BLANK);
				}
				int columnIndex=cell.getColumnIndex();
				System.out.println("column index = "+columnIndex);
				switch (columnIndex)
	 			{
	             
	 			case 0:
	 				cell.setCellType(cell.getCellType().NUMERIC);
	 				int cell_id=(int) cell.getNumericCellValue();
	 				excelFileDataInserter.setId(cell_id);
	 				
	 				
	 				//pstmt.setInt(1, id);
	 				break;
	 				
	 			case 1:	
	 				String cell_location_Type=cell.getStringCellValue();
	 				excelFileDataInserter.setLocation_Type(cell_location_Type);
	 				break;
	 				
	 			case 2:
	 				String cell_location_Size_Type=cell.getStringCellValue();
	 				excelFileDataInserter.setLocation_Size_Type(cell_location_Size_Type);
	                break;
	                
	 			case 3:
	 				String cell_location_Area=cell.getStringCellValue();
	 				excelFileDataInserter.setLocation_Area(cell_location_Area);
	                break;
	                
	 			case 4:

	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_item_Code=cell.getStringCellValue();
	 				excelFileDataInserter.setItem_Code(cell_item_Code);
	                break;
	                
	 			case 5:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_is_Parent= cell.getStringCellValue();
	 				excelFileDataInserter.setIs_Parent(cell_is_Parent);
	 				
	                break;
	 			case 6:
	 				String cell_alternative_Item_Code=cell.getStringCellValue();
	 				excelFileDataInserter.setAlternative_Item_Code(cell_alternative_Item_Code);
	                break;
	                
	 			case 7:
	 				String cell_item_Description=cell.getStringCellValue();
	 				excelFileDataInserter.setItem_Description(cell_item_Description);
	                break;
	                
	 			case 8:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_batch_Nbr=cell.getStringCellValue();
	 				excelFileDataInserter.setBatch_Nbr(cell_batch_Nbr);
	 				//System.out.println(cell.getCellType());
	 				
	                break; 
	                
	 			case 9:
	 				
	 				String cell_expiry_Date=cell.getStringCellValue();
	 				excelFileDataInserter.setExpiry_Date(cell_expiry_Date);
	                break; 
	                
	 			case 10:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_current_Qty= cell.getStringCellValue();
	 				excelFileDataInserter.setCurrent_Qty(cell_current_Qty);
	                break;
	                
	 			case 11:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_allocated_Qty=cell.getStringCellValue();
	 				excelFileDataInserter.setAllocated_Qty(cell_allocated_Qty);
	                break;
	                
	 			case 12:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_current_LPN=cell.getStringCellValue();
	 				excelFileDataInserter.setCurrent_LPN(cell_current_LPN);
	 				System.out.println(cell.getCellType());
	                break;
	                
	 			case 13:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_alloc_LPN=cell.getStringCellValue();
	 				excelFileDataInserter.setAlloc_LPN(cell_alloc_LPN);
	 				System.out.println(cell.getCellType());
	                break; 
	                
	 			
	 			case 14:
	 				String cell_last_CC_Counted=cell.getStringCellValue();
	 				excelFileDataInserter.setLast_CC_Counted(cell_last_CC_Counted);
	                break;
	                
	 			case 15:
	 				String cell_mod_TimeStamp=cell.getStringCellValue();
	 				excelFileDataInserter.setMod_TimeStamp(cell_mod_TimeStamp);
	                break;
	                
	 			case 16:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_to_be_Counted=cell.getStringCellValue();
	 				excelFileDataInserter.setTo_be_Counted(cell_to_be_Counted);
	                break;
	                
	 			case 17:
	 				String cell_to_Be_Counted_TS= cell.getStringCellValue();
	 				excelFileDataInserter.setTo_Be_Counted_TS(cell_to_Be_Counted_TS);
	                break;
	                
	 			case 18:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_loc_Lock= cell.getStringCellValue();
	 				excelFileDataInserter.setLoc_Lock(cell_loc_Lock);
	                break;
	                
	 			case 19:
	 				String cell_location_Barcode= cell.getStringCellValue();
	 				excelFileDataInserter.setLocation_Barcode(cell_location_Barcode);
	                break;
	                
	 			case 20:
	 				String cell_facility= cell.getStringCellValue();
	 				excelFileDataInserter.setFacility(cell_facility);
	                break;  
	                
	 			case 21:
	 				String cell_display_Text= cell.getStringCellValue();
	 				excelFileDataInserter.setDisplay_Text(cell_display_Text);
	                break; 
	                
	 			case 22:
	 				String cell_mod_User= cell.getStringCellValue();
	 				excelFileDataInserter.setMod_User(cell_mod_User);
	                break; 
	                
	 			case 23:
	 				String cell_loc_CC_User= cell.getStringCellValue();
	 				excelFileDataInserter.setLoc_CC_User(cell_loc_CC_User);
	                break;
	                
	 			case 24:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_loc_Max_LPN=cell.getStringCellValue();
	 				excelFileDataInserter.setLoc_Max_LPN(cell_loc_Max_LPN);
	                break;
	                
	 			case 25:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_loc_Max_Unit= cell.getStringCellValue();
	 				excelFileDataInserter.setLoc_Max_Unit(cell_loc_Max_Unit);
	                break;
	                
	 			case 26:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_min_Unit=cell.getStringCellValue();
	 				excelFileDataInserter.setMin_Unit(cell_min_Unit);
	                break;
	                
	 			case 27:
	 				String cell_attribute_A= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_A(cell_attribute_A);
	                break;
	                
	 			case 28:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_attribute_B= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_B(cell_attribute_B);
	                break;
	                  
	 			case 29:
	 				String cell_attribute_C= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_C(cell_attribute_C);
	                break;
	                  
	 			case 30:
	 				String cell_attribute_D= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_D(cell_attribute_D);
	                break;
	                  
	                
	 			case 31:
	 				String cell_attribute_E= cell.getStringCellValue();
	 			    excelFileDataInserter.setAttribute_E(cell_attribute_E);
	                break;
	                
	 			case 32:
	 				String cell_attribute_F= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_F(cell_attribute_F);
	                break;
	                  
	 			case 33:
	 				String cell_attribute_G= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_G(cell_attribute_G);
	                break;
	                  
	                
	                
	 			case 34:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_attribute_H= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_H(cell_attribute_H);
	                break;
	                
	 			case 35:
	 				String cell_attribute_I= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_I(cell_attribute_I);
	                break;
	 			case 36:
	 				String cell_attribute_J= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_J(cell_attribute_J);
	                break;
	 			case 37:
	 				String cell_attribute_K= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_K(cell_attribute_K);
	                break;
	 			case 38:
	 				String cell_attribute_L= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_L(cell_attribute_L);
	                break;
	 			case 39:
	 				String cell_attribute_M= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_M(cell_attribute_M);
	                break;
	 			case 40:
	 				String cell_attribute_N= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_N(cell_attribute_N);
	                break;
	 			case 41:
	 				String cell_attribute_O= cell.getStringCellValue();
	 				excelFileDataInserter.setAttribute_O(cell_attribute_O);
	                break;
	                
	 			case 42:
	 				cell.setCellType(cell.getCellType().STRING);
	 				String cell_brand_Code= cell.getStringCellValue();
	 				excelFileDataInserter.setBrand_Code(cell_brand_Code);
	                break;
	                
	 			case 43:
	 				//cell.setCellType(cell.CELL_TYPE_STRING);
	 				String cell_batch_Nbr_Lock=cell.getStringCellValue();
	 				excelFileDataInserter.setBatch_Nbr_Lock(cell_batch_Nbr_Lock);
	                break; 
	                
	 			case 44:
	 				String cell_company= cell.getStringCellValue();
	 				excelFileDataInserter.setCompany(cell_company);
	                break;
	                
	 			case 45:
	 				String cell_lock_Code= cell.getStringCellValue();
	 				excelFileDataInserter.setLock_Code(cell_lock_Code);
	 				
	                break;
	 			default:
	 				break;
	 			}
				
			}
			arrayList.add(excelFileDataInserter);
		}
		excelFileDataRepo.saveAll(arrayList);	
		
	}
}