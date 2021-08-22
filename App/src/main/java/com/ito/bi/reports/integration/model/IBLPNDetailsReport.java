package com.ito.bi.reports.integration.model;

import org.springframework.stereotype.Component;

@Component
public class IBLPNDetailsReport 
{
	private int report_id;
	private String company_Code;
	private String facility_Code;	
	private String lPN_No;
	private String lPN_Status;
	private String item_code;
	private String item_B;
	private String item_Description;
	private String batch_No;
	private String expiry_Date;
	private String lock_Code;
	private String item_Life;
	private String location;
	private String allocation_Zone;
	private String lPN_Current_Qty;
	private String lPN_Allocated_Qty;
	private String lPN_Avilable_Qty;
	private String ref_Shipment_No;
	private String lPN_Create_Time;
	private String lPN_received_Time;
	private String lPN_modified_Time;
	private String lPN_received_User;
	private String shipment_Type;
	private String shipment_Status;
	private String allow_Reserve_Partial_pick;
	
	
	public IBLPNDetailsReport() {
		super();
	}

	public IBLPNDetailsReport(int report_id, String company_Code, String facility_Code, String lPN_No,
			String lPN_Status, String item_code, String item_B, String item_Description, String batch_No,
			String expiry_Date, String lock_Code, String item_Life, String location, String allocation_Zone,
			String lPN_Current_Qty, String lPN_Allocated_Qty, String lPN_Avilable_Qty, String ref_Shipment_No,
			String lPN_Create_Time, String lPN_received_Time, String lPN_modified_Time, String lPN_received_User,
			String shipment_Type, String shipment_Status, String allow_Reserve_Partial_pick) {
		super();
		this.report_id = report_id;
		this.company_Code = company_Code;
		this.facility_Code = facility_Code;
		this.lPN_No = lPN_No;
		this.lPN_Status = lPN_Status;
		this.item_code = item_code;
		this.item_B = item_B;
		this.item_Description = item_Description;
		this.batch_No = batch_No;
		this.expiry_Date = expiry_Date;
		this.lock_Code = lock_Code;
		this.item_Life = item_Life;
		this.location = location;
		this.allocation_Zone = allocation_Zone;
		this.lPN_Current_Qty = lPN_Current_Qty;
		this.lPN_Allocated_Qty = lPN_Allocated_Qty;
		this.lPN_Avilable_Qty = lPN_Avilable_Qty;
		this.ref_Shipment_No = ref_Shipment_No;
		this.lPN_Create_Time = lPN_Create_Time;
		this.lPN_received_Time = lPN_received_Time;
		this.lPN_modified_Time = lPN_modified_Time;
		this.lPN_received_User = lPN_received_User;
		this.shipment_Type = shipment_Type;
		this.shipment_Status = shipment_Status;
		this.allow_Reserve_Partial_pick = allow_Reserve_Partial_pick;
	}

	public int getReport_id() {
		return report_id;
	}

	public void setReport_id(int report_id) {
		this.report_id = report_id;
	}

	public String getCompany_Code() {
		return company_Code;
	}

	public void setCompany_Code(String company_Code) {
		this.company_Code = company_Code;
	}

	public String getFacility_Code() {
		return facility_Code;
	}

	public void setFacility_Code(String facility_Code) {
		this.facility_Code = facility_Code;
	}

	public String getlPN_No() {
		return lPN_No;
	}

	public void setlPN_No(String lPN_No) {
		this.lPN_No = lPN_No;
	}

	public String getlPN_Status() {
		return lPN_Status;
	}

	public void setlPN_Status(String lPN_Status) {
		this.lPN_Status = lPN_Status;
	}

	public String getItem_code() {
		return item_code;
	}

	public void setItem_code(String item_code) {
		this.item_code = item_code;
	}

	public String getItem_B() {
		return item_B;
	}

	public void setItem_B(String item_B) {
		this.item_B = item_B;
	}

	public String getItem_Description() {
		return item_Description;
	}

	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}

	public String getBatch_No() {
		return batch_No;
	}

	public void setBatch_No(String batch_No) {
		this.batch_No = batch_No;
	}

	public String getExpiry_Date() {
		return expiry_Date;
	}

	public void setExpiry_Date(String expiry_Date) {
		this.expiry_Date = expiry_Date;
	}

	public String getLock_Code() {
		return lock_Code;
	}

	public void setLock_Code(String lock_Code) {
		this.lock_Code = lock_Code;
	}

	public String getItem_Life() {
		return item_Life;
	}

	public void setItem_Life(String item_Life) {
		this.item_Life = item_Life;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getAllocation_Zone() {
		return allocation_Zone;
	}

	public void setAllocation_Zone(String allocation_Zone) {
		this.allocation_Zone = allocation_Zone;
	}

	public String getlPN_Current_Qty() {
		return lPN_Current_Qty;
	}

	public void setlPN_Current_Qty(String lPN_Current_Qty) {
		this.lPN_Current_Qty = lPN_Current_Qty;
	}

	public String getlPN_Allocated_Qty() {
		return lPN_Allocated_Qty;
	}

	public void setlPN_Allocated_Qty(String lPN_Allocated_Qty) {
		this.lPN_Allocated_Qty = lPN_Allocated_Qty;
	}

	public String getlPN_Avilable_Qty() {
		return lPN_Avilable_Qty;
	}

	public void setlPN_Avilable_Qty(String lPN_Avilable_Qty) {
		this.lPN_Avilable_Qty = lPN_Avilable_Qty;
	}

	public String getRef_Shipment_No() {
		return ref_Shipment_No;
	}

	public void setRef_Shipment_No(String ref_Shipment_No) {
		this.ref_Shipment_No = ref_Shipment_No;
	}

	public String getlPN_Create_Time() {
		return lPN_Create_Time;
	}

	public void setlPN_Create_Time(String lPN_Create_Time) {
		this.lPN_Create_Time = lPN_Create_Time;
	}

	public String getlPN_received_Time() {
		return lPN_received_Time;
	}

	public void setlPN_received_Time(String lPN_received_Time) {
		this.lPN_received_Time = lPN_received_Time;
	}

	public String getlPN_modified_Time() {
		return lPN_modified_Time;
	}

	public void setlPN_modified_Time(String lPN_modified_Time) {
		this.lPN_modified_Time = lPN_modified_Time;
	}

	public String getlPN_received_User() {
		return lPN_received_User;
	}

	public void setlPN_received_User(String lPN_received_User) {
		this.lPN_received_User = lPN_received_User;
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

	public String getAllow_Reserve_Partial_pick() {
		return allow_Reserve_Partial_pick;
	}

	public void setAllow_Reserve_Partial_pick(String allow_Reserve_Partial_pick) {
		this.allow_Reserve_Partial_pick = allow_Reserve_Partial_pick;
	}

	@Override
	public String toString() {
		return "IBLPNDetailsReport [report_id=" + report_id + ", company_Code=" + company_Code + ", facility_Code="
				+ facility_Code + ", lPN_No=" + lPN_No + ", lPN_Status=" + lPN_Status + ", item_code=" + item_code
				+ ", item_B=" + item_B + ", item_Description=" + item_Description + ", batch_No=" + batch_No
				+ ", expiry_Date=" + expiry_Date + ", lock_Code=" + lock_Code + ", item_Life=" + item_Life
				+ ", location=" + location + ", allocation_Zone=" + allocation_Zone + ", lPN_Current_Qty="
				+ lPN_Current_Qty + ", lPN_Allocated_Qty=" + lPN_Allocated_Qty + ", lPN_Avilable_Qty="
				+ lPN_Avilable_Qty + ", ref_Shipment_No=" + ref_Shipment_No + ", lPN_Create_Time=" + lPN_Create_Time
				+ ", lPN_received_Time=" + lPN_received_Time + ", lPN_modified_Time=" + lPN_modified_Time
				+ ", lPN_received_User=" + lPN_received_User + ", shipment_Type=" + shipment_Type + ", shipment_Status="
				+ shipment_Status + ", allow_Reserve_Partial_pick=" + allow_Reserve_Partial_pick + "]";
	}
}
