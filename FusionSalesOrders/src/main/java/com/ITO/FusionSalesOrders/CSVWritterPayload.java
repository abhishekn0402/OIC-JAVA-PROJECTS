package com.ITO.FusionSalesOrders;

import org.springframework.stereotype.Component;
/**
 * This class will help us to append the data to CSV file
 *
 */
@Component
public class CSVWritterPayload
{
	private String orderNumber; // Order
	private String businessUnitName; //Business Unit Name
	private String transactionTypeCode;//order type	
	private String transactionOn;//ordered Date
	private String sourceTransactionNumber; // Source Order
	private String buyingPartyName; // Customer Profile
	private String sourceTransactionLineNumber; // Order Line
	private String productNumber; // Item Name
	private String productDescription; //Item description
	private String fusionOrderLineStatus; // Order Line Status
	private String fusionOrderQuantity;	
	
	private String wms_Order_Status; // WMS Order details Status --it is there order hdr api
	private String created_Ts; // WMS_Create Timestamps -- it is there order dtl api
	private String seq_nbr; // Order Line -- it is there order dtl api
	private String itemcode; // Item Name  -- this is not there in order dtl api
	private String item_Description; //Item Description -- this is not there in order dtl api
	private String wmsOrderQuantity;
	
	public String getOrderNumber() {
		return orderNumber;
	}
	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}
	public String getBusinessUnitName() {
		return businessUnitName;
	}
	public void setBusinessUnitName(String businessUnitName) {
		this.businessUnitName = businessUnitName;
	}
	public String getTransactionTypeCode() {
		return transactionTypeCode;
	}
	public void setTransactionTypeCode(String transactionTypeCode) {
		this.transactionTypeCode = transactionTypeCode;
	}
	public String getTransactionOn() {
		return transactionOn;
	}
	public void setTransactionOn(String transactionOn) {
		this.transactionOn = transactionOn;
	}
	public String getSourceTransactionNumber() {
		return sourceTransactionNumber;
	}
	public void setSourceTransactionNumber(String sourceTransactionNumber) {
		this.sourceTransactionNumber = sourceTransactionNumber;
	}
	public String getBuyingPartyName() {
		return buyingPartyName;
	}
	public void setBuyingPartyName(String buyingPartyName) {
		this.buyingPartyName = buyingPartyName;
	}
	public String getSourceTransactionLineNumber() {
		return sourceTransactionLineNumber;
	}
	public void setSourceTransactionLineNumber(String sourceTransactionLineNumber) {
		this.sourceTransactionLineNumber = sourceTransactionLineNumber;
	}
	public String getProductNumber() {
		return productNumber;
	}
	public void setProductNumber(String productNumber) {
		this.productNumber = productNumber;
	}
	public String getProductDescription() {
		return productDescription;
	}
	public void setProductDescription(String productDescription) {
		this.productDescription = productDescription;
	}
	public String getFusionOrderLineStatus() {
		return fusionOrderLineStatus;
	}
	public void setFusionOrderLineStatus(String fusionOrderLineStatus) {
		this.fusionOrderLineStatus = fusionOrderLineStatus;
	}
	public String getFusionOrderQuantity() {
		return fusionOrderQuantity;
	}
	public void setFusionOrderQuantity(String fusionOrderQuantity) {
		this.fusionOrderQuantity = fusionOrderQuantity;
	}
	public String getWms_Order_Status() {
		return wms_Order_Status;
	}
	public void setWms_Order_Status(String wms_Order_Status) {
		this.wms_Order_Status = wms_Order_Status;
	}
	public String getCreated_Ts() {
		return created_Ts;
	}
	public void setCreated_Ts(String created_Ts) {
		this.created_Ts = created_Ts;
	}
	public String getSeq_nbr() {
		return seq_nbr;
	}
	public void setSeq_nbr(String seq_nbr) {
		this.seq_nbr = seq_nbr;
	}
	public String getItemcode() {
		return itemcode;
	}
	public void setItemcode(String itemcode) {
		this.itemcode = itemcode;
	}
	public String getItem_Description() {
		return item_Description;
	}
	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}
	public String getWmsOrderQuantity() {
		return wmsOrderQuantity;
	}
	public void setWmsOrderQuantity(String wmsOrderQuantity) {
		this.wmsOrderQuantity = wmsOrderQuantity;
	}
	@Override
	public String toString() {
		return "CSVWritterPayload [orderNumber=" + orderNumber + ", businessUnitName=" + businessUnitName
				+ ", transactionTypeCode=" + transactionTypeCode + ", transactionOn=" + transactionOn
				+ ", sourceTransactionNumber=" + sourceTransactionNumber + ", buyingPartyName=" + buyingPartyName
				+ ", sourceTransactionLineNumber=" + sourceTransactionLineNumber + ", productNumber=" + productNumber
				+ ", productDescription=" + productDescription + ", fusionOrderLineStatus=" + fusionOrderLineStatus
				+ ", fusionOrderQuantity=" + fusionOrderQuantity + ", wms_Order_Status=" + wms_Order_Status
				+ ", created_Ts=" + created_Ts + ", seq_nbr=" + seq_nbr + ", itemcode=" + itemcode
				+ ", item_Description=" + item_Description + ", wmsOrderQuantity=" + wmsOrderQuantity + "]";
	}
}
