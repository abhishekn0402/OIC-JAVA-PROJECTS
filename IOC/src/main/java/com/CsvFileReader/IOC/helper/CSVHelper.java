package com.CsvFileReader.IOC.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.web.multipart.MultipartFile;

import com.CsvFileReader.IOC.model.CsvInserter;

public class CSVHelper
{
	 public static String TYPE = "csv";
	 
	 /*
	  static String[] HEADERs = {"id", "location_Type", "location_Size_Type", "location_Area", "item_Code", "is_Parent",
			  "alternative_Item_Code","item_Description","batch_Nbr","expiry_Date", "current_Qty","allocated_Qty","current_LPN","alloc_LPN","last_CC_Counted",
			  "mod_TimeStamp","to_be_Counted","to_Be_Counted_TS","loc_Lock","location_Barcode","facility","display_Text","mod_User",
			  "loc_CC_User","loc_Max_LPN","loc_Max_Unit","min_Unit","attribute_A","attribute_B","attribute_C","attribute_D","attribute_E",
			  "attribute_F","attribute_G","attribute_H","attribute_I","attribute_J","attribute_K","attribute_L","attribute_M","attribute_N","attribute_O","brand_Code","batch_Nbr_Lock",
			  "company","lock_Code"};
     */
	 
	  public static boolean hasCSVFormat(MultipartFile multipartFile) 
	  {
	    if (!TYPE.equals(multipartFile.getContentType()))
	    {
	      return false;
	    }
	    return true;
	  }
      // taking the data from excel and adding it in the arrayList
	  public static List<CsvInserter> csvInserterModel(InputStream inputStream)
	  {
	    try
	    (BufferedReader fileReader = new BufferedReader(new InputStreamReader(inputStream, "UTF-8"));
	        CSVParser csvParser = new CSVParser(fileReader,
	        		CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());) 
	    {

	      List<CsvInserter> csvInsertersList = new ArrayList<CsvInserter>();

	      Iterable<CSVRecord> csvRecords = csvParser.getRecords();
	   
	      for (CSVRecord csvRecord : csvRecords)
	      {
	    	  CsvInserter csvInserter = new CsvInserter (
	    			  
	    			  Integer.parseInt(csvRecord.get("id")),
	    			  csvRecord.get("location_Type"),
		              csvRecord.get("location_Size_Type"),
		              csvRecord.get("location_Area"),
		              csvRecord.get("item_Code"),		           
		              csvRecord.get("is_Parent"),
		              csvRecord.get("alternative_Item_Code"),
		              csvRecord.get("item_Description"), 
		              csvRecord.get("batch_Nbr"),
		              csvRecord.get("expiry_Date"), 
		              csvRecord.get("current_Qty"),
		              csvRecord.get("allocated_Qty"),
		              csvRecord.get("current_LPN"),
		              csvRecord.get("alloc_LPN"),
		              csvRecord.get("last_CC_Counted"),
		              csvRecord.get("mod_TimeStamp"), 
		              csvRecord.get("to_be_Counted"),
		              csvRecord.get("to_Be_Counted_TS"),
		              csvRecord.get("loc_Lock"),
		              csvRecord.get("location_Barcode"),
		              csvRecord.get("facility"),
		              csvRecord.get("display_Text"),
		              csvRecord.get("mod_User"),		              
		              csvRecord.get("loc_CC_User"),
		              csvRecord.get("loc_Max_LPN"),
		              csvRecord.get("loc_Max_Unit"),
		              csvRecord.get("min_Unit"),		              
		              csvRecord.get("attribute_A"),
		              csvRecord.get("attribute_B"),
		              csvRecord.get("attribute_C"),
		              csvRecord.get("attribute_D"),
		              csvRecord.get("attribute_E"),
		              csvRecord.get("attribute_F"),		              
		              csvRecord.get("attribute_G"),
		              csvRecord.get("attribute_H"),
		              csvRecord.get("attribute_I"),
		              csvRecord.get("attribute_J"),
		              csvRecord.get("attribute_K"),
		              csvRecord.get("attribute_L"),
		              csvRecord.get("attribute_M"),
		              csvRecord.get("attribute_N"),
		              csvRecord.get("attribute_O"),
		              csvRecord.get("brand_Code"),
		              csvRecord.get("batch_Nbr_Lock"),
		              csvRecord.get("company"),
		              csvRecord.get("lock_Code")
		             
		            );

	    	  csvInsertersList.add(csvInserter);
	      }
	      
          //returning the arrayList object which contain all the row data
	      return csvInsertersList;
	    }
	    catch (IOException e) 
	    {
	      throw new RuntimeException("fail to parse CSV file: " + e.getMessage());
	    }
	  }
}
