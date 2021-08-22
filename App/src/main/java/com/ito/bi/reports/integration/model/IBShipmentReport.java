package com.ito.bi.reports.integration.model;

import org.springframework.stereotype.Component;

@Component
public class IBShipmentReport
{
	private int report_id;
	private String company;
	private String facility;
	private String shipment_No;	
	private String item_Code;
	private String alternative_Item_Code;
	private String item_Description;
	private String storage_Type;
	private String vendor_Name_or_From_Location;
	private String shipped_Date;
	private String shipped_Qty;
	private String received_Qty;
	private String iblpn_Number;
	private String shipment_Type;
	private String shipment_Status;
	private String uom;
	private String expiry_Date;	
	private String batch_Number;
	private String lock_Code;	
	private String item_Shelf_Life;
	private String original_Create_Ts;
	private String verified_Ts;
	private String create_User;
	public IBShipmentReport()
	{
		super();
	}
	public IBShipmentReport(int report_id, String company, String facility, String shipment_No, String item_Code,
			String alternative_Item_Code, String item_Description, String storage_Type,
			String vendor_Name_or_From_Location, String shipped_Date, String shipped_Qty, String received_Qty,
			String iblpn_Number, String shipment_Type, String shipment_Status, String uom, String expiry_Date,
			String batch_Number, String lock_Code, String item_Shelf_Life, String original_Create_Ts,
			String verified_Ts, String create_User) {
		super();
		this.report_id = report_id;
		this.company = company;
		this.facility = facility;
		this.shipment_No = shipment_No;
		this.item_Code = item_Code;
		this.alternative_Item_Code = alternative_Item_Code;
		this.item_Description = item_Description;
		this.storage_Type = storage_Type;
		this.vendor_Name_or_From_Location = vendor_Name_or_From_Location;
		this.shipped_Date = shipped_Date;
		this.shipped_Qty = shipped_Qty;
		this.received_Qty = received_Qty;
		this.iblpn_Number = iblpn_Number;
		this.shipment_Type = shipment_Type;
		this.shipment_Status = shipment_Status;
		this.uom = uom;
		this.expiry_Date = expiry_Date;
		this.batch_Number = batch_Number;
		this.lock_Code = lock_Code;
		this.item_Shelf_Life = item_Shelf_Life;
		this.original_Create_Ts = original_Create_Ts;
		this.verified_Ts = verified_Ts;
		this.create_User = create_User;
	}
	
	public int getReport_id() {
		return report_id;
	}
	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}
	public String getCompany() {
		return company;
	}
	public void setCompany(String company) {
		this.company = company;
	}
	public String getFacility() {
		return facility;
	}
	public void setFacility(String facility) {
		this.facility = facility;
	}
	public String getShipment_No() {
		return shipment_No;
	}
	public void setShipment_No(String shipment_No) {
		this.shipment_No = shipment_No;
	}
	public String getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(String item_Code) {
		this.item_Code = item_Code;
	}
	public String getAlternative_Item_Code() {
		return alternative_Item_Code;
	}
	public void setAlternative_Item_Code(String alternative_Item_Code) {
		this.alternative_Item_Code = alternative_Item_Code;
	}
	public String getItem_Description() {
		return item_Description;
	}
	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}
	public String getStorage_Type() {
		return storage_Type;
	}
	public void setStorage_Type(String storage_Type) {
		this.storage_Type = storage_Type;
	}
	public String getVendor_Name_or_From_Location() {
		return vendor_Name_or_From_Location;
	}
	public void setVendor_Name_or_From_Location(String vendor_Name_or_From_Location) {
		this.vendor_Name_or_From_Location = vendor_Name_or_From_Location;
	}
	public String getShipped_Date() {
		return shipped_Date;
	}
	public void setShipped_Date(String shipped_Date) {
		this.shipped_Date = shipped_Date;
	}
	public String getShipped_Qty() {
		return shipped_Qty;
	}
	public void setShipped_Qty(String shipped_Qty) {
		this.shipped_Qty = shipped_Qty;
	}
	public String getReceived_Qty() {
		return received_Qty;
	}
	public void setReceived_Qty(String received_Qty) {
		this.received_Qty = received_Qty;
	}
	public String getIblpn_Number() {
		return iblpn_Number;
	}
	public void setIblpn_Number(String iblpn_Number) {
		this.iblpn_Number = iblpn_Number;
	}
	public String getShipment_Type() {
		return shipment_Type;
	}
	public void setShipment_Type(String shipment_Type) {
		this.shipment_Type = shipment_Type;
	}
	public String getShipment_Status() {
		return shipment_Status;
	}
	public void setShipment_Status(String shipment_Status) {
		this.shipment_Status = shipment_Status;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getExpiry_Date() {
		return expiry_Date;
	}
	public void setExpiry_Date(String expiry_Date) {
		this.expiry_Date = expiry_Date;
	}
	public String getBatch_Number() {
		return batch_Number;
	}
	public void setBatch_Number(String batch_Number) {
		this.batch_Number = batch_Number;
	}
	public String getLock_Code() {
		return lock_Code;
	}
	public void setLock_Code(String lock_Code) {
		this.lock_Code = lock_Code;
	}
	public String getItem_Shelf_Life() {
		return item_Shelf_Life;
	}
	public void setItem_Shelf_Life(String item_Shelf_Life) {
		this.item_Shelf_Life = item_Shelf_Life;
	}
	public String getOriginal_Create_Ts() {
		return original_Create_Ts;
	}
	public void setOriginal_Create_Ts(String original_Create_Ts) {
		this.original_Create_Ts = original_Create_Ts;
	}
	public String getVerified_Ts() {
		return verified_Ts;
	}
	public void setVerified_Ts(String verified_Ts) {
		this.verified_Ts = verified_Ts;
	}
	public String getCreate_User() {
		return create_User;
	}
	public void setCreate_User(String create_User) {
		this.create_User = create_User;
	}
	@Override
	public String toString() {
		return "IBShipment_Report [report_id=" + report_id + ", company=" + company + ", facility=" + facility
				+ ", shipment_No=" + shipment_No + ", item_Code=" + item_Code + ", alternative_Item_Code="
				+ alternative_Item_Code + ", item_Description=" + item_Description + ", storage_Type=" + storage_Type
				+ ", vendor_Name_or_From_Location=" + vendor_Name_or_From_Location + ", shipped_Date=" + shipped_Date
				+ ", shipped_Qty=" + shipped_Qty + ", received_Qty=" + received_Qty + ", iblpn_Number=" + iblpn_Number
				+ ", shipment_Type=" + shipment_Type + ", shipment_Status=" + shipment_Status + ", uom=" + uom
				+ ", expiry_Date=" + expiry_Date + ", batch_Number=" + batch_Number + ", lock_Code=" + lock_Code
				+ ", item_Shelf_Life=" + item_Shelf_Life + ", original_Create_Ts=" + original_Create_Ts
				+ ", verified_Ts=" + verified_Ts + ", create_User=" + create_User + "]";
	}	
}
