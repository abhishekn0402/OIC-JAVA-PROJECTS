package com.ITO.FusionSalesOrders;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * This class will help us to get Key from fusionKeyContainer, wmsOrderHeaderKeyContainer,
			wmsOrderDetailsKeyContainer, wmsOrderItemKeyContainer Key containers

			This is where we have to add the data to CSV file
 *
 */
@Component
public class RestClientHelperClass{
	@Autowired
	private CSVWritterPayload csvWritterPayload;

	@Autowired
	private FuisonRestClient fuisonRestClient;

	@Value("${report_location}")
	String report_location;

	private Logger logger = LoggerFactory.getLogger(RestClientHelperClass.class);
	Map<String, Object> csvFileKeyContainer= new  LinkedHashMap<String, Object>();
	FileWriter fileWriter=null;
	String fusionOrderNumber;
	public void KeySeparator(Map<String, Object> fusionKeyContainer, Map<String, Object> wmsOrderHeaderKeyContainer,
			Map<String, Object> wmsOrderDetailsKeyContainer, Map<String, Object> wmsOrderItemKeyContainer) throws IOException {
		Iterator<String> fusionKeys=fusionKeyContainer.keySet().iterator();
		logger.info("fusionKeys : "+fusionKeys);
		String compareOrderHdrKey="";
		boolean flag=false;	

		for (Entry<String, Object> currentFusionSet :fusionKeyContainer.entrySet()){
			Object fusionObject=fusionKeyContainer.get(currentFusionSet.getKey());
			String[] currentSplittedFusionReocrd=fusionObject.toString().replaceAll("[\n]", " ").split("#");
			fusionOrderNumber=currentSplittedFusionReocrd[0];
			String sourceTransactionLineNumber=currentSplittedFusionReocrd[6];
			csvWritterPayload.setOrderNumber(currentSplittedFusionReocrd[0]);
			csvWritterPayload.setBusinessUnitName(currentSplittedFusionReocrd[1]);
			csvWritterPayload.setTransactionTypeCode(currentSplittedFusionReocrd[2]);
			csvWritterPayload.setTransactionOn(currentSplittedFusionReocrd[3]);
			csvWritterPayload.setSourceTransactionNumber(currentSplittedFusionReocrd[4]);
			csvWritterPayload.setBuyingPartyName(currentSplittedFusionReocrd[5]);
			csvWritterPayload.setSourceTransactionLineNumber(currentSplittedFusionReocrd[6]);
			csvWritterPayload.setProductNumber(currentSplittedFusionReocrd[7]);
			csvWritterPayload.setProductDescription(currentSplittedFusionReocrd[8]);
			csvWritterPayload.setFusionOrderLineStatus(currentSplittedFusionReocrd[9]);
			csvWritterPayload.setFusionOrderQuantity(currentSplittedFusionReocrd[11]);
			try {
				if(wmsOrderHeaderKeyContainer!=null || wmsOrderDetailsKeyContainer!=null ){		
					for (Entry<String, Object> OrderHdrSet :wmsOrderHeaderKeyContainer.entrySet()){		
						//		if(OrderHdrSet.getKey().toString().contains(fusionOrderNumber)){	
						int orderHdrIndex=StringUtils.ordinalIndexOf(OrderHdrSet.getKey().toString(), ":", 1);
						compareOrderHdrKey=OrderHdrSet.toString().substring(0, orderHdrIndex);
						if(compareOrderHdrKey.equals(fusionOrderNumber)) {
							Object headerObject=OrderHdrSet.getValue();
							String [] currentSplittedWMSOrderHeaderRecord=headerObject.toString().replaceAll("[\n',']", " ").split("#");
							String orderNumber=currentSplittedWMSOrderHeaderRecord[0];
							String wmsOrderHeaderId=currentSplittedWMSOrderHeaderRecord[1];
							String wmsOrderStatus=currentSplittedWMSOrderHeaderRecord[2];
							csvWritterPayload.setWms_Order_Status(wmsOrderStatus);
							for (Entry<String, Object> OrderDtlSet :wmsOrderDetailsKeyContainer.entrySet()){
								int orderDtlIndex=StringUtils.ordinalIndexOf(OrderDtlSet.getKey().toString(), ":", 3);
								String compareDetailsKey=OrderDtlSet.toString().substring(0, orderDtlIndex);
								if(compareDetailsKey.equals(orderNumber+":"+wmsOrderHeaderId+":"+sourceTransactionLineNumber)) {
									Object detailsObject=OrderDtlSet.getValue();
									String [] currentSplittedWMSOrderDetailRecord=detailsObject.toString().replaceAll("[\n',']", " ").split("#");	
									int len =currentSplittedWMSOrderDetailRecord.length;
									String create_Ts=currentSplittedWMSOrderDetailRecord[1];
									String itemId=currentSplittedWMSOrderDetailRecord[4];
									String seqNumber=currentSplittedWMSOrderDetailRecord[6];
									String wmsOrderQuantity=currentSplittedWMSOrderDetailRecord[7];
									csvWritterPayload.setCreated_Ts(create_Ts);
									csvWritterPayload.setSeq_nbr(seqNumber);	
									csvWritterPayload.setWmsOrderQuantity(wmsOrderQuantity);	
									for (Entry<String, Object> itemSet :wmsOrderItemKeyContainer.entrySet()){
										int itemIndex=StringUtils.ordinalIndexOf(itemSet.getKey().toString(), ":", 1);
										String itemCompareKey=itemSet.toString().substring(0, itemIndex);
										if(itemCompareKey.equals(itemId)){
											Object itemObject=itemSet.getValue();
											String [] currentSplittedWMSItemRecord=itemObject.toString().replaceAll("[\n',']", " ").split("#");
											String item_alternate_code=currentSplittedWMSItemRecord[1];
											String item_Description=currentSplittedWMSItemRecord[2];
											csvWritterPayload.setItemcode(item_alternate_code);
											csvWritterPayload.setItem_Description(item_Description);
											csvFileKeyContainer.put(currentFusionSet.getKey(),
													csvWritterPayload.getOrderNumber()+"#"+
															csvWritterPayload.getBusinessUnitName()+"#"+
															csvWritterPayload.getTransactionTypeCode()+"#"+
															csvWritterPayload.getTransactionOn()+"#"+
															csvWritterPayload.getSourceTransactionNumber()+"#"+
															csvWritterPayload.getBuyingPartyName()+"#"+
															csvWritterPayload.getSourceTransactionLineNumber()+"#"+
															csvWritterPayload.getProductNumber()+"#"+
															csvWritterPayload.getProductDescription()+"#"+
															csvWritterPayload.getFusionOrderLineStatus()+"#"+
															csvWritterPayload.getFusionOrderQuantity()+"#"+
															csvWritterPayload.getWmsOrderQuantity()+"#"+
															csvWritterPayload.getWms_Order_Status()+"#"+
															csvWritterPayload.getCreated_Ts()+"#"+
															csvWritterPayload.getSeq_nbr()+"#"+
															csvWritterPayload.getItemcode()+"#"+
															csvWritterPayload.getItem_Description()				
													);
											flag=true;
										}
									}
								}
							}
						}
					}
				}
			} 
			catch (Exception e) {
				logger.info(e.getMessage());	
			}
			if(flag==false){
				//	logger.info("Order not created in WMS : "+fusionOrderNumber);
				csvWritterPayload.setWms_Order_Status(" ");
				csvWritterPayload.setCreated_Ts(" ");
				csvWritterPayload.setSeq_nbr(" ");
				csvWritterPayload.setItemcode(" ");
				csvWritterPayload.setItem_Description(" ");
				csvWritterPayload.setWms_Order_Status(" ");
				csvWritterPayload.setWmsOrderQuantity(" ");
				csvFileKeyContainer.put(currentFusionSet.getKey(),
						csvWritterPayload.getOrderNumber()+"#"+
								csvWritterPayload.getBusinessUnitName()+"#"+
								csvWritterPayload.getTransactionTypeCode()+"#"+
								csvWritterPayload.getTransactionOn()+"#"+
								csvWritterPayload.getSourceTransactionNumber()+"#"+
								csvWritterPayload.getBuyingPartyName()+"#"+
								csvWritterPayload.getSourceTransactionLineNumber()+"#"+
								csvWritterPayload.getProductNumber()+"#"+
								csvWritterPayload.getProductDescription()+"#"+
								csvWritterPayload.getFusionOrderLineStatus()+"#"+
								csvWritterPayload.getFusionOrderQuantity()+"#"+
								csvWritterPayload.getWmsOrderQuantity()+"#"+
								csvWritterPayload.getWms_Order_Status()+"#"+
								csvWritterPayload.getCreated_Ts()+"#"+
								csvWritterPayload.getSeq_nbr()+"#"+
								csvWritterPayload.getItemcode()+"#"+
								csvWritterPayload.getItem_Description()

						);
				//	logger.info("csvWritterPayload : "+ csvWritterPayload);
			}
			else{
				//	logger.info("Order created in WMS : "+fusionOrderNumber);
				csvFileKeyContainer.put(currentFusionSet.getKey(),
						csvWritterPayload.getOrderNumber()+"#"+
								csvWritterPayload.getBusinessUnitName()+"#"+
								csvWritterPayload.getTransactionTypeCode()+"#"+
								csvWritterPayload.getTransactionOn()+"#"+
								csvWritterPayload.getSourceTransactionNumber()+"#"+
								csvWritterPayload.getBuyingPartyName()+"#"+
								csvWritterPayload.getSourceTransactionLineNumber()+"#"+
								csvWritterPayload.getProductNumber()+"#"+
								csvWritterPayload.getProductDescription()+"#"+
								csvWritterPayload.getFusionOrderLineStatus()+"#"+
								csvWritterPayload.getFusionOrderQuantity()+"#"+
								csvWritterPayload.getWmsOrderQuantity()+"#"+
								csvWritterPayload.getWms_Order_Status()+"#"+
								csvWritterPayload.getCreated_Ts()+"#"+
								csvWritterPayload.getSeq_nbr()+"#"+
								csvWritterPayload.getItemcode()+"#"+
								csvWritterPayload.getItem_Description()
						);
			}
			flag=false;	
		}
		//calling csv writer
		//int csvKeyContainerSize=csvFileKeyContainer.size();
		salesOrderDetailsWritter(csvFileKeyContainer);		
		logger.info("Data sucessfully inserted to fusionReport.csv file");
	}
	public void salesOrderDetailsWritter(Map<String, Object> csvFileKeyContainer) throws IOException{
		try {	
			fileWriter = new FileWriter(report_location);
			fileWriter.append("OrderNumber,BusinessUnitName,TransactionTypeCode,TransactionOn,SourceTransactionNumber,BuyingPartyName,"
			+ "SourceTransactionLineNumber,ProductNumber,ProductDescription,"
			+ "FusionOrderLineStatus,FusionOrderQuantity,WMSOrderQuantity,WMS_Order_Status,Created_Ts,Seq_nbr,Itemcode,Item_Description");
			fileWriter.append("\n");
			for (Entry<String, Object> currentFusionSet :csvFileKeyContainer.entrySet()){	
				Object getValueObjectForCSVFile=currentFusionSet.getValue();
				String [] CSVFileValues=getValueObjectForCSVFile.toString().replaceAll("[\n',']"," ").split("#");		
				String fusion_Created_Ts=CSVFileValues[3].toString().substring(0, 10);
				String facility=CSVFileValues[1].toString();// facility or BusinessUnitName
				if (facility.equals(fuisonRestClient.dataFetchingForFacility) 
				   && fusion_Created_Ts.contains(fuisonRestClient.dataFetchingForDate)){
					try {		
						fileWriter.append(CSVFileValues[0]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[1]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[2]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[3]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[4]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[5]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[6]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[7]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[8]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[9]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[10]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[11]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[12]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[13]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[14]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[15]);
						fileWriter.append(",");
						fileWriter.append(CSVFileValues[16]);
						fileWriter.append("\n");
					} 
					catch (Exception e) {
						logger.info("Unable to insert record to csv file : "+e.getMessage());
					}						
				}
				 if(("".equals(fuisonRestClient.dataFetchingForFacility) || fuisonRestClient.dataFetchingForFacility==null)
                       && fusion_Created_Ts.contains(fuisonRestClient.dataFetchingForDate)){
					try {	
					fileWriter.append(CSVFileValues[0]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[1]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[2]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[3]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[4]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[5]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[6]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[7]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[8]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[9]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[10]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[11]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[12]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[13]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[14]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[15]);
					fileWriter.append(",");
					fileWriter.append(CSVFileValues[16]);
					fileWriter.append("\n");
					} 
					catch (Exception e) {
						logger.info("Unable to insert record to csv file : "+e.getMessage());	
					}
				}
			}
		}
		catch (Exception e){
			logger.info("Unable to insert record to csv file : "+e.getMessage());
		}	
		finally{
			fileWriter.flush();
			fileWriter.close();
		}
	}	
}


