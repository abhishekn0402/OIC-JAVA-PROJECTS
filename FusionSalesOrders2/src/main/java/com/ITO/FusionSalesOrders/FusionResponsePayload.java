package com.ITO.FusionSalesOrders;

import org.springframework.stereotype.Component;
/**
 * This class will help us to append the received response from Fusion
 *
 */
@Component
public class FusionResponsePayload
{
	/*
	 *Fusion order details
	 */
	private String fusionOrderHeaderId;
	private String fusionOrderLineId;
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
	private String orderedQuantity; // Order Line Status
	
	
	public FusionResponsePayload() {
		super();			
	}


	public String getFusionOrderHeaderId() {
		return fusionOrderHeaderId;
	}


	public void setFusionOrderHeaderId(String fusionOrderHeaderId) {
		this.fusionOrderHeaderId = fusionOrderHeaderId;
	}


	public String getFusionOrderLineId() {
		return fusionOrderLineId;
	}


	public void setFusionOrderLineId(String fusionOrderLineId) {
		this.fusionOrderLineId = fusionOrderLineId;
	}


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


	public String getOrderedQuantity() {
		return orderedQuantity;
	}


	public void setOrderedQuantity(String orderedQuantity) {
		this.orderedQuantity = orderedQuantity;
	}


	@Override
	public String toString() {
		return "FusionResponsePayload [fusionOrderHeaderId=" + fusionOrderHeaderId + ", fusionOrderLineId="
				+ fusionOrderLineId + ", orderNumber=" + orderNumber + ", businessUnitName=" + businessUnitName
				+ ", transactionTypeCode=" + transactionTypeCode + ", transactionOn=" + transactionOn
				+ ", sourceTransactionNumber=" + sourceTransactionNumber + ", buyingPartyName=" + buyingPartyName
				+ ", sourceTransactionLineNumber=" + sourceTransactionLineNumber + ", productNumber=" + productNumber
				+ ", productDescription=" + productDescription + ", fusionOrderLineStatus=" + fusionOrderLineStatus
				+ ", orderedQuantity=" + orderedQuantity + "]";
	}
	
}
