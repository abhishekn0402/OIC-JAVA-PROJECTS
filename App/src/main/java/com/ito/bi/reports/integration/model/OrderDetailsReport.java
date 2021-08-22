package com.ito.bi.reports.integration.model;

import org.springframework.stereotype.Component;

@Component
public class OrderDetailsReport
{
	private int report_id;
	private String company_Code;
	private String facility_Code;
	private String order_No;
	private String customer_Name;
	private String ship_To;
	private String customer_Po;
	private String order_Type;
	private String item_Code;
	private String item_Description;
	private String product_Life;
	private String storage_Type;
	private String sales_Channel;
	private String orig_Order_Qty;
	private String allocated_Qty;
	private String shipped_Qty;
	private String uom;
	private String integrated_Wave_Perc;
	private String detail_Line_Status;
	private String order_Status;
	private String create_Time;
	private String ship_Time;
	private String required_Ship_Date;
	private String seq_No;
	private String allocation_Ts;
	private String expiry_Date;
	private String batch_Number;
	private String destination_Facility_Code;
	private String picked_Time;
	private String load_Number;
	public OrderDetailsReport() {
		super();
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
	public String getOrder_No() {
		return order_No;
	}
	public void setOrder_No(String order_No) {
		this.order_No = order_No;
	}
	public String getCustomer_Name() {
		return customer_Name;
	}
	public void setCustomer_Name(String customer_Name) {
		this.customer_Name = customer_Name;
	}
	public String getShip_To() {
		return ship_To;
	}
	public void setShip_To(String ship_To) {
		this.ship_To = ship_To;
	}
	public String getCustomer_Po() {
		return customer_Po;
	}
	public void setCustomer_Po(String customer_Po) {
		this.customer_Po = customer_Po;
	}
	public String getOrder_Type() {
		return order_Type;
	}
	public void setOrder_Type(String order_Type) {
		this.order_Type = order_Type;
	}
	public String getItem_Code() {
		return item_Code;
	}
	public void setItem_Code(String item_Code) {
		this.item_Code = item_Code;
	}
	public String getItem_Description() {
		return item_Description;
	}
	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}
	public String getProduct_Life() {
		return product_Life;
	}
	public void setProduct_Life(String product_Life) {
		this.product_Life = product_Life;
	}
	public String getStorage_Type() {
		return storage_Type;
	}
	public void setStorage_Type(String storage_Type) {
		this.storage_Type = storage_Type;
	}
	public String getSales_Channel() {
		return sales_Channel;
	}
	public void setSales_Channel(String sales_Channel) {
		this.sales_Channel = sales_Channel;
	}
	public String getOrig_Order_Qty() {
		return orig_Order_Qty;
	}
	public void setOrig_Order_Qty(String orig_Order_Qty) {
		this.orig_Order_Qty = orig_Order_Qty;
	}
	public String getAllocated_Qty() {
		return allocated_Qty;
	}
	public void setAllocated_Qty(String allocated_Qty) {
		this.allocated_Qty = allocated_Qty;
	}
	public String getShipped_Qty() {
		return shipped_Qty;
	}
	public void setShipped_Qty(String shipped_Qty) {
		this.shipped_Qty = shipped_Qty;
	}
	public String getUom() {
		return uom;
	}
	public void setUom(String uom) {
		this.uom = uom;
	}
	public String getIntegrated_Wave_Perc() {
		return integrated_Wave_Perc;
	}
	public void setIntegrated_Wave_Perc(String integrated_Wave_Perc) {
		this.integrated_Wave_Perc = integrated_Wave_Perc;
	}
	public String getDetail_Line_Status() {
		return detail_Line_Status;
	}
	public void setDetail_Line_Status(String detail_Line_Status) {
		this.detail_Line_Status = detail_Line_Status;
	}
	public String getOrder_Status() {
		return order_Status;
	}
	public void setOrder_Status(String order_Status) {
		this.order_Status = order_Status;
	}
	public String getCreate_Time() {
		return create_Time;
	}
	public void setCreate_Time(String create_Time) {
		this.create_Time = create_Time;
	}
	public String getShip_Time() {
		return ship_Time;
	}
	public void setShip_Time(String ship_Time) {
		this.ship_Time = ship_Time;
	}
	public String getRequired_Ship_Date() {
		return required_Ship_Date;
	}
	public void setRequired_Ship_Date(String required_Ship_Date) {
		this.required_Ship_Date = required_Ship_Date;
	}
	public String getSeq_No() {
		return seq_No;
	}
	public void setSeq_No(String seq_No) {
		this.seq_No = seq_No;
	}
	public String getAllocation_Ts() {
		return allocation_Ts;
	}
	public void setAllocation_Ts(String allocation_Ts) {
		this.allocation_Ts = allocation_Ts;
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
	public String getDestination_Facility_Code() {
		return destination_Facility_Code;
	}
	public void setDestination_Facility_Code(String destination_Facility_Code) {
		this.destination_Facility_Code = destination_Facility_Code;
	}
	
	public String getPicked_Time() {
		return picked_Time;
	}

	public void setPicked_Time(String picked_Time) {
		this.picked_Time = picked_Time;
	}

	public String getLoad_Number() {
		return load_Number;
	}

	public void setLoad_Number(String load_Number) {
		this.load_Number = load_Number;
	}

	@Override
	public String toString() {
		return "OrderDetailsReport [report_id=" + report_id + ", company_Code=" + company_Code + ", facility_Code="
				+ facility_Code + ", order_No=" + order_No + ", customer_Name=" + customer_Name + ", ship_To=" + ship_To
				+ ", customer_Po=" + customer_Po + ", order_Type=" + order_Type + ", item_Code=" + item_Code
				+ ", item_Description=" + item_Description + ", product_Life=" + product_Life + ", storage_Type="
				+ storage_Type + ", sales_Channel=" + sales_Channel + ", orig_Order_Qty=" + orig_Order_Qty
				+ ", allocated_Qty=" + allocated_Qty + ", shipped_Qty=" + shipped_Qty + ", uom=" + uom
				+ ", integrated_Wave_Perc=" + integrated_Wave_Perc + ", detail_Line_Status=" + detail_Line_Status
				+ ", order_Status=" + order_Status + ", create_Time=" + create_Time + ", ship_Time=" + ship_Time
				+ ", required_Ship_Date=" + required_Ship_Date + ", seq_No=" + seq_No + ", allocation_Ts="
				+ allocation_Ts + ", expiry_Date=" + expiry_Date + ", batch_Number=" + batch_Number
				+ ", destination_Facility_Code=" + destination_Facility_Code + ", picked_Time=" + picked_Time
				+ ", load_Number=" + load_Number + "]";
	}
	}
