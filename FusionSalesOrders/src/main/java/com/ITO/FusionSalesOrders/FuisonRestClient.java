/**
 * This class will help us to handle the WMS and Fusion Responses
 */
package com.ITO.FusionSalesOrders;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.client.support.BasicAuthenticationInterceptor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/fusion/salesOrderHub")
public class FuisonRestClient
{
	private Logger logger = LoggerFactory.getLogger(FuisonRestClient.class);

	@Value("${fusion_endpoint}")
	String fusion_endpoint;

	@Value("${fusionUser}")
	String fusionUser;

	@Value("${fusionPassword}")
	String fusionPassword;

	@Value("${wmsUser}")
	String wmsUser;

	@Value("${wmsPassword}")
	String wmsPassword;

	@Value("${wms_endpoint_order_hdr}")
	String wms_endpoint_order_hdr;

	@Value("${wms_endpoint_order_dtl}")
	String wms_endpoint_order_dtl;

	@Value("${wms_endpoint_item}")
	String wms_endpoint_item;

	@Value("${test}")
	String test;

	@Autowired
	FusionResponse fusionResponse;

	@Autowired
	FusionResponsePayload fusionResponsePayload;

	@Autowired
	WMSResponse wmsResponse;

	@Autowired
	WMSResponsePayload wmsResponsePayload;

	@Autowired
	RestClientHelperClass restClientHelperClass;


	@GetMapping("/ping")
	public String sayHello()
	{
		return "Hello Fusion and WMS! I'm up and running";
	}	
	String dataFetchingForDate = null;
	String dataFetchingForFacility = null;
	String [] dataFetchingForMultipleFacilities=null;

	@SuppressWarnings({ "unchecked", "rawtypes" })
	@GetMapping("/report")
	public String ReportGenerator(@RequestParam("noOfDays") String noOfDays, @RequestParam("facility") String facility) throws IOException
	{
		LinkedHashMap<String, Object> fusionSalesOrderHeaderData = null;
		LinkedHashMap<String, Object> fusionSalesOrderLinesData = null;
		LinkedHashMap<String, Object> currentWmsOrderHeaderData = null;
		LinkedHashMap<String, Object> currentWmsOrderDetailsData = null;
		LinkedHashMap<String, Object> currentOrderDetailsItemDetails = null;
		Map<String, Object> fusionKeyContainer= new  LinkedHashMap<String, Object>();
		Map<String, Object> wmsOrderDetailsKeyContainer= new  LinkedHashMap<String, Object>();
		Map<String, Object> wmsOrderHeaderKeyContainer= new  LinkedHashMap<String, Object>();
		Map<String, Object> wmsOrderItemKeyContainer= new  LinkedHashMap<String, Object>();
		LinkedHashMap<String, Object> wmsOrderDetailOrder_id__id = null;
		LinkedHashMap<String, Object> wmsOrderDetailItem_id__id = null;

		//Declaration of required local variables 
		String fusionOrderHeaderId="";
		String fusionOrderLineId="";
		String orderNumber = "";
		String transactionTypeCode="";
		String businessUnitName="";
		String transactionOn;
		String sourceTransactionNumber;
		String buyingPartyName;
		String sourceTransactionLineNumber;
		String productNumber;
		String productDescription;
		String wms_Order_Status="";
		String itemcode="";
		String item_Description;
		String created_Ts;
		String seq_nbr;	
		String itemId="";
		String item_alternate_code="";
		String status_Id;
		String fusionOrderLineStatus;
		String wmsOrderHeaderId="";
		String wmsOrderDetailId="";
		String orderedQuantity="";
		RestTemplate restTemplate = new RestTemplate();
		RestTemplate wmsRestTemplate = new RestTemplate();
		LocalDate today = LocalDate.now();
		/*
		Date date = new Date();
		Timestamp timestamp=new Timestamp(date.getTime());
		String today=timestamp.toString().substring(0, 10);//2021-10-24
		 */
		//int dayCount=Integer.parseInt(noOfDays);
		//String today="2021-11-01";

		

		try {
			
			
			if("".equals(facility) || facility==null){
				dataFetchingForFacility=null;
			}
			else{
				dataFetchingForFacility=facility;		
			}
	
			if(noOfDays==null || "".equals(noOfDays)){
				dataFetchingForDate=today.toString();
			}
			else if(noOfDays=="-1" || noOfDays.equals("-1")){
				String dayBeforeOneDay = (today.minusDays(1)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeOneDay;
			}
			else if(noOfDays=="-2" || noOfDays.equals("-2")){
				String dayBeforeTwoDay = (today.minusDays(2)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeTwoDay;
			}
			else if(noOfDays=="-3" || noOfDays.equals("-3")){
				String dayBeforeThreeDay = (today.minusDays(3)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeThreeDay;
			}
			else if(noOfDays=="-4" || noOfDays.equals("-4")){
				String dayBeforeFourDay = (today.minusDays(4)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeFourDay;
			}
			else if(noOfDays=="-5" || noOfDays.equals("-5")){
				String dayBeforeFiveDay = (today.minusDays(5)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeFiveDay;
			}
			else if(noOfDays=="-6" || noOfDays.equals("-6")){
				String dayBeforeSixDay = (today.minusDays(6)).format(DateTimeFormatter.ISO_DATE);	
				dataFetchingForDate=dayBeforeSixDay;
			}
			else{
				logger.info("Enter valid query parameter. "+noOfDays+"is not a valid query parameter");	
			}


			restTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(fusionUser,fusionPassword));
			fusionResponse = restTemplate.getForObject(
					fusion_endpoint+"q=CreationDate>'"+dataFetchingForDate+"T00:00:00'&onlyData=true&expand=lines&limit=500", FusionResponse.class);

			/*
			fusionResponse = restTemplate.getForObject(
			fusion_endpoint+"q=CreationDate>'"+dataFeatchingDate+"T00:00:00';CreationDate<'"+
			dataFeatchingDate+"T23:59:59'&onlyData=true&expand=lines&limit=500", FusionResponse.class);
			 */

			/*
			fusionResponse = restTemplate.getForObject(
					fusion_endpoint+"81796&onlyData=true&expand=lines", FusionResponse.class);	
			 */

			ArrayList items=fusionResponse.getItems();
			logger.info("fusion orders size : "+items.size());
			for (int i = 0; i < items.size(); i++){
				fusionSalesOrderHeaderData = (LinkedHashMap<String, Object>)fusionResponse.getItems().get(i);
				fusionOrderHeaderId=fusionSalesOrderHeaderData.get("HeaderId").toString();
				orderNumber=fusionSalesOrderHeaderData.get("OrderNumber").toString();
				transactionTypeCode = fusionSalesOrderHeaderData.get("TransactionTypeCode").toString();
				businessUnitName= fusionSalesOrderHeaderData.get("BusinessUnitName").toString();
				transactionOn = fusionSalesOrderHeaderData.get("TransactionOn").toString();
				sourceTransactionNumber =  fusionSalesOrderHeaderData.get("SourceTransactionNumber").toString();
				buyingPartyName =  fusionSalesOrderHeaderData.get("BuyingPartyName").toString();	
				fusionResponsePayload.setFusionOrderHeaderId(fusionOrderHeaderId);
				fusionResponsePayload.setOrderNumber(orderNumber);
				fusionResponsePayload.setBusinessUnitName(businessUnitName);
				fusionResponsePayload.setTransactionTypeCode(transactionTypeCode);
				fusionResponsePayload.setTransactionOn(transactionOn);
				fusionResponsePayload.setSourceTransactionNumber(sourceTransactionNumber);
				fusionResponsePayload.setBuyingPartyName(buyingPartyName);

				ArrayList lines=(ArrayList) fusionSalesOrderHeaderData.get("lines");
				for (int j = 0; j < lines.size(); j++){
					fusionSalesOrderLinesData=(LinkedHashMap<String, Object>) lines.get(j);
					fusionOrderLineId =  fusionSalesOrderLinesData.get("LineId").toString();
					sourceTransactionLineNumber =  fusionSalesOrderLinesData.get("SourceTransactionLineNumber").toString();
					productNumber =  fusionSalesOrderLinesData.get("ProductNumber").toString();
					productDescription =  fusionSalesOrderLinesData.get("ProductDescription").toString();
					fusionOrderLineStatus =  fusionSalesOrderLinesData.get("Status").toString();
					orderedQuantity =  fusionSalesOrderLinesData.get("OrderedQuantity").toString();
					fusionResponsePayload.setSourceTransactionLineNumber(sourceTransactionLineNumber);
					fusionResponsePayload.setProductNumber(productNumber);
					fusionResponsePayload.setProductDescription(productDescription);
					fusionResponsePayload.setFusionOrderLineStatus(fusionOrderLineStatus);
					fusionResponsePayload.setOrderedQuantity(orderedQuantity);

					fusionKeyContainer.put(orderNumber+":"+fusionOrderHeaderId+":"+fusionOrderLineId+":"+productNumber+":"+sourceTransactionLineNumber,
							fusionResponsePayload.getOrderNumber()+"#"+
									fusionResponsePayload.getBusinessUnitName()+"#"+
									fusionResponsePayload.getTransactionTypeCode()+"#"+
									fusionResponsePayload.getTransactionOn()+"#"+
									fusionResponsePayload.getSourceTransactionNumber()+"#"+
									fusionResponsePayload.getBuyingPartyName()+"#"+
									fusionResponsePayload.getSourceTransactionLineNumber()+"#"+
									fusionResponsePayload.getProductNumber()+"#"+
									fusionResponsePayload.getProductDescription()+"#"+
									fusionResponsePayload.getFusionOrderLineStatus()+"#"+
									fusionResponsePayload.getFusionOrderHeaderId()+"#"+
									fusionResponsePayload.getOrderedQuantity()+"#"+
									fusionResponsePayload.getFusionOrderLineId());	
				}
			}
			logger.info("***********all fusion order details added to fusionKeyContainer***********");
		}
		catch (Exception e) {
			logger.info("exception : "+e.getMessage());	
		}
		logger.info("fusionKeyContainer  : "+fusionKeyContainer.size());	
		try {
			wmsRestTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(wmsUser,wmsPassword));
			wmsResponse = wmsRestTemplate.getForObject(
					wms_endpoint_order_hdr+"create_ts__range="+dataFetchingForDate+"T00:00:00,"+today
					+"T23:59:59&fields=id,order_nbr,create_ts,status_id",WMSResponse.class);

			/*
		    wmsResponse = wmsRestTemplate.getForObject(
		    wms_endpoint_order_hdr+"order_nbr=81796&fields=id,order_nbr,create_ts,status_id", WMSResponse.class);
			 */

			ArrayList order_Hdr=wmsResponse.getResults();
			logger.info("WMS order_hdr size : "+order_Hdr.size());
			for (int j = 0; j < order_Hdr.size(); j++) {
				currentWmsOrderHeaderData=(LinkedHashMap<String, Object>) wmsResponse.getResults().get(j);
				wmsOrderHeaderId=currentWmsOrderHeaderData.get("id").toString();
				orderNumber=currentWmsOrderHeaderData.get("order_nbr").toString();
				status_Id=currentWmsOrderHeaderData.get("status_id").toString();
				wmsResponsePayload.setWmsOrderHeaderId(wmsOrderHeaderId);
				wmsResponsePayload.setOrderNumber(orderNumber);
				wmsResponsePayload.setWms_Order_Status(Order_Status(wms_Order_Status,status_Id));
				wmsOrderHeaderKeyContainer.put(orderNumber+":"+wmsOrderHeaderId,
						wmsResponsePayload.getOrderNumber()+"#"+
								wmsResponsePayload.getWmsOrderHeaderId()+"#"+
								wmsResponsePayload.getWms_Order_Status());
			}
			logger.info("wmsOrderHeaderKeyContainer  : "+wmsOrderHeaderKeyContainer.size());
			logger.info("***********all WMS order header details added to wmsOrderHeaderKeyContainer***********");
			wmsRestTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(wmsUser,wmsPassword));	

			wmsResponse = wmsRestTemplate.getForObject(
					wms_endpoint_order_dtl+"create_ts__range="+dataFetchingForDate+"T00:00:00,"+today
					+"T23:59:59&fields=id,order_id,item_id,seq_nbr,create_ts,ord_qty",WMSResponse.class);

			/*
		    wmsResponse = wmsRestTemplate.getForObject(
			wms_endpoint_order_dtl+"order_id__order_nbr=81796&fields=id,order_id,item_id,seq_nbr,create_ts", WMSResponse.class);
			 */
			int orderDtlPageCount=wmsResponse.getPage_count();
			if(orderDtlPageCount==0){
				logger.info("no Orders");
			}
			else if(orderDtlPageCount>=1){
				for (int i = 1; i <=orderDtlPageCount; i++) {
					int pageNumber=wmsResponse.getPage_nbr();
					if(pageNumber==1 && i==1){
						ArrayList order_Dtl=wmsResponse.getResults();
						for (int k = 0; k < order_Dtl.size(); k++) {
							currentWmsOrderDetailsData=(LinkedHashMap<String, Object>) wmsResponse.getResults().get(k);
							wmsOrderDetailId= currentWmsOrderDetailsData.get("id").toString();
							wmsOrderDetailOrder_id__id= (LinkedHashMap<String, Object>) currentWmsOrderDetailsData.get("order_id");
							wmsOrderDetailItem_id__id= (LinkedHashMap<String, Object>) currentWmsOrderDetailsData.get("item_id");
							wmsOrderHeaderId=wmsOrderDetailOrder_id__id.get("id").toString();//order header id
							orderNumber=wmsOrderDetailOrder_id__id.get("key").toString();//order number
							itemId=wmsOrderDetailItem_id__id.get("id").toString();//itemId
							itemcode=wmsOrderDetailItem_id__id.get("key").toString();//code
							created_Ts= currentWmsOrderDetailsData.get("create_ts").toString();
							seq_nbr= currentWmsOrderDetailsData.get("seq_nbr").toString();
							orderedQuantity= currentWmsOrderDetailsData.get("ord_qty").toString();

							wmsResponsePayload.setOrderNumber(orderNumber);
							wmsResponsePayload.setCreated_Ts(created_Ts);
							wmsResponsePayload.setWmsOrderHeaderId(wmsOrderHeaderId);
							wmsResponsePayload.setItemcode(itemcode);
							wmsResponsePayload.setItemId(itemId);
							wmsResponsePayload.setWmsOrderDetailId(wmsOrderDetailId);
							wmsResponsePayload.setSeq_nbr(seq_nbr);	
							wmsResponsePayload.setOrder_Qty(orderedQuantity);	
							wmsOrderDetailsKeyContainer.put(orderNumber+":"+wmsOrderHeaderId+":"+seq_nbr+":"+itemId+":"+wmsOrderDetailId+":"+itemcode,
									wmsResponsePayload.getOrderNumber()+"#"+
											wmsResponsePayload.getCreated_Ts()+"#"+
											wmsResponsePayload.getWmsOrderHeaderId()+"#"+
											wmsResponsePayload.getItemcode()+"#"+
											wmsResponsePayload.getItemId()+"#"+
											wmsResponsePayload.getWmsOrderDetailId()+"#"+
											wmsResponsePayload.getSeq_nbr()+"#"+
											wmsResponsePayload.getOrder_Qty());
						}
						logger.info("***********all WMS order DTL details added to wmsOrderDetailsKeyContainer***********");
					}
					else {
						try {

							WMSResponse nextPageWMSResponse = wmsRestTemplate.getForObject(
									wms_endpoint_order_dtl+"create_ts__range="+dataFetchingForDate+"T00:00:00,"+today+"T23:59:59&fields=id,order_id,item_id,seq_nbr,create_ts,ord_qty&page="+i, WMSResponse.class);

							/*create_ts__range
							 * create_ts__range="+dataFeatchingDate+"T00:00:00,"+today+"T23:59:59&
						WMSResponse nextPageWMSResponse = wmsRestTemplate.getForObject(
								wms_endpoint_order_dtl+"order_id__order_nbr=81796&fields=id,order_id,item_id,seq_nbr,create_ts&page="+i, WMSResponse.class);
							 */
							ArrayList order_Dtl=nextPageWMSResponse.getResults();
							logger.info("order_dtl size : "+order_Dtl.size());
							for (int k = 0; k < order_Dtl.size(); k++) {
								currentWmsOrderDetailsData=(LinkedHashMap<String, Object>) nextPageWMSResponse.getResults().get(k);
								wmsOrderDetailId= currentWmsOrderDetailsData.get("id").toString();
								wmsOrderDetailOrder_id__id= (LinkedHashMap<String, Object>) currentWmsOrderDetailsData.get("order_id");
								wmsOrderDetailItem_id__id= (LinkedHashMap<String, Object>) currentWmsOrderDetailsData.get("item_id");
								wmsOrderHeaderId=wmsOrderDetailOrder_id__id.get("id").toString();//order header id
								orderNumber=wmsOrderDetailOrder_id__id.get("key").toString();//order number
								itemId=wmsOrderDetailItem_id__id.get("id").toString();//itemId
								itemcode=wmsOrderDetailItem_id__id.get("key").toString();//code
								created_Ts= currentWmsOrderDetailsData.get("create_ts").toString();
								seq_nbr= currentWmsOrderDetailsData.get("seq_nbr").toString();
								orderedQuantity= currentWmsOrderDetailsData.get("ord_qty").toString();

								wmsResponsePayload.setOrderNumber(orderNumber);
								wmsResponsePayload.setCreated_Ts(created_Ts);
								wmsResponsePayload.setWmsOrderHeaderId(wmsOrderHeaderId);
								wmsResponsePayload.setItemcode(itemcode);
								wmsResponsePayload.setItemId(itemId);
								wmsResponsePayload.setWmsOrderDetailId(wmsOrderDetailId);
								wmsResponsePayload.setSeq_nbr(seq_nbr);	
								wmsResponsePayload.setOrder_Qty(orderedQuantity);	

								wmsOrderDetailsKeyContainer.put(orderNumber+":"+wmsOrderHeaderId+":"+seq_nbr+":"+itemId+":"+wmsOrderDetailId+":"+itemcode,
										wmsResponsePayload.getOrderNumber()+"#"+
												wmsResponsePayload.getCreated_Ts()+"#"+
												wmsResponsePayload.getWmsOrderHeaderId()+"#"+
												wmsResponsePayload.getItemcode()+"#"+
												wmsResponsePayload.getItemId()+"#"+
												wmsResponsePayload.getWmsOrderDetailId()+"#"+
												wmsResponsePayload.getSeq_nbr()+"#"+
												wmsResponsePayload.getOrder_Qty());
							}
						} catch (Exception e) {
							logger.info("exception : "+e.getMessage());	
						}		
					}	
				}
			}

		} catch (Exception e) {
			logger.info("exception : "+e.getMessage());	
		}
		logger.info("wms dtl orders : "+wmsOrderDetailsKeyContainer.size());
		try {
			wmsRestTemplate.getInterceptors().add(new BasicAuthenticationInterceptor(wmsUser,wmsPassword));	
			wmsResponse = wmsRestTemplate.getForObject(
					wms_endpoint_item+"fields=id,item_alternate_code,description,create_ts", WMSResponse.class);

			int pageCount=wmsResponse.getPage_count();
			if(pageCount==0){
				logger.info("no Items");
			}
			else if(pageCount>=1){
				for (int i = 1; i <=pageCount; i++) {
					int pageNumber=wmsResponse.getPage_nbr();
					if(pageNumber==1 && i==1){
						ArrayList itemEntity=wmsResponse.getResults();
						for (int k = 0; k < itemEntity.size(); k++) {
							currentOrderDetailsItemDetails=(LinkedHashMap<String, Object>) wmsResponse.getResults().get(k);
							itemId= currentOrderDetailsItemDetails.get("id").toString();
							item_alternate_code= currentOrderDetailsItemDetails.get("item_alternate_code").toString();
							item_Description= currentOrderDetailsItemDetails.get("description").toString();
							wmsResponsePayload.setItemId(itemId);
							wmsResponsePayload.setItemcode(item_alternate_code);
							wmsResponsePayload.setItem_Description(item_Description);

							wmsOrderItemKeyContainer.put(itemId+":"+item_alternate_code+":"+item_Description,
									wmsResponsePayload.getItemId()+"#"+
											wmsResponsePayload.getItemcode()+"#"+
											wmsResponsePayload.getItem_Description());
						}			
					}
					else {
						try {
							WMSResponse nextPageWMSResponse = wmsRestTemplate.getForObject(
									wms_endpoint_item+"fields=id,item_alternate_code,description,create_ts&page="+i, WMSResponse.class);		
							ArrayList itemEntity=nextPageWMSResponse.getResults();
							for (int l = 0; l < itemEntity.size(); l++) {
								currentOrderDetailsItemDetails=(LinkedHashMap<String, Object>) nextPageWMSResponse.getResults().get(l);
								itemId= currentOrderDetailsItemDetails.get("id").toString();
								item_alternate_code= currentOrderDetailsItemDetails.get("item_alternate_code").toString();
								item_Description= currentOrderDetailsItemDetails.get("description").toString();
								wmsResponsePayload.setItemId(itemId);
								wmsResponsePayload.setItemcode(item_alternate_code);
								wmsResponsePayload.setItem_Description(item_Description);
								wmsOrderItemKeyContainer.put(itemId+":"+item_alternate_code+":"+wmsOrderHeaderId+":"+item_Description,
										wmsResponsePayload.getItemId()+"#"+
												wmsResponsePayload.getItemcode()+"#"+
												wmsResponsePayload.getItem_Description());
							}
						} catch (Exception e) {
							logger.info("exception : "+e.getMessage());	
						}		
					}	
				}
			}
			logger.info("***********all the items added to linkedHashMap***********");
		} catch (Exception e) {
			logger.info("NO items for today");	
		}
		restClientHelperClass.KeySeparator(fusionKeyContainer,wmsOrderHeaderKeyContainer,wmsOrderDetailsKeyContainer,wmsOrderItemKeyContainer);
		return "Report generated..";
	}

	//method for setting the order status
	private String Order_Status(String wms_Order_Status,String status_Id){
		if(Integer.parseInt(status_Id)==0){
			wms_Order_Status="CREATED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);			
		}
		else if(Integer.parseInt(status_Id)==10){
			wms_Order_Status="PARTLY_ALLOCATED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);			
		}
		else if(Integer.parseInt(status_Id)==20){		
			wms_Order_Status="ALLOCATED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else if(Integer.parseInt(status_Id)==30){
			wms_Order_Status="IN_PACKING";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else if(Integer.parseInt(status_Id)==40){
			wms_Order_Status="PACKED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else if(Integer.parseInt(status_Id)==50){
			wms_Order_Status="LOADED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else if(Integer.parseInt(status_Id)==90){
			wms_Order_Status="SHIPPED";	
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else if(Integer.parseInt(status_Id)==99){
			wms_Order_Status="CANCELLED";
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		else{
			wms_Order_Status=null;
			wmsResponsePayload.setWms_Order_Status(wms_Order_Status);
		}
		return wms_Order_Status;
	}			
}

