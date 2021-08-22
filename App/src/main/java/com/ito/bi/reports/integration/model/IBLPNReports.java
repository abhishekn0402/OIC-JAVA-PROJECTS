package com.ito.bi.reports.integration.model;

public class IBLPNReports
{
private int report_id;
private String facility;
private String company;	
private String lPN_Nbr	;
private String status;
private String qC_Status;
private String vAS;
private String current_Location;
private String prev_Location;
private String item_Code;
private String is_Parent;
private String alternative_Item_Code;
private String description;
private String orig_Qty;
private String received_Qty;
private String current_Qty;
private String pack_Qty;
private String case_Qty;
private String nbr_Lock;
private String create_TimeStamp;
private String received_TimeStamp;
private String priority_Date;
private String putaway_Type;
private String batch_Nbr;
private String expiry_Date;
private String mod_Time_Stamp;
private String received_Shipment_Nbr;
private String shipment_Type;
private String ref_PO_Nbr;
private String pallet_Nbr;
private String allocated_Qty;
private String attribute_A;
private String attribute_B;
private String attribute_C;
private String attribute_D;
private String attribute_E;
private String attribute_F;
private String attribute_G;
private String attribute_H;
private String attribute_I;
private String attribute_J;	
private String attribute_K;
private String attribute_L;	
private String attribute_M;
private String attribute_N;
private String attribute_O;
private String received_User;
private String mod_User;
private String mod_TimeStamp;
private String manufacture_Date;
private String lPN_is_Pallet;
private String item_Hierarchy_1_Code;
private String item_Hierarchy_1;
private String item_hierarchy_2;
private String item_Hierarchy_3_Code;
private String item_hierarchy_3;
private String item_Hierarchy_4_Code;
private String item_hierarchy_4;
private String item_Hierarchy_5_Code;
private String item_hierarchy_5;
private String weight;
private String volume;
private String length;
private String width;
private String height;
private String brand_Code;
public IBLPNReports() {
	super();
}
public IBLPNReports(int report_id, String facility, String company, String lPN_Nbr, String status, String qC_Status,
		String vAS, String current_Location, String prev_Location, String item_Code, String is_Parent,
		String alternative_Item_Code, String description, String orig_Qty, String received_Qty, String current_Qty,
		String pack_Qty, String case_Qty, String nbr_Lock, String create_TimeStamp, String received_TimeStamp,
		String priority_Date, String putaway_Type, String batch_Nbr, String expiry_Date, String mod_Time_Stamp,
		String received_Shipment_Nbr, String shipment_Type, String ref_PO_Nbr, String pallet_Nbr, String allocated_Qty,
		String attribute_A, String attribute_B, String attribute_C, String attribute_D, String attribute_E,
		String attribute_F, String attribute_G, String attribute_H, String attribute_I, String attribute_J,
		String attribute_K, String attribute_L, String attribute_M, String attribute_N, String attribute_O,
		String received_User, String mod_User, String mod_TimeStamp, String manufacture_Date, String lPN_is_Pallet,
		String item_Hierarchy_1_Code, String item_Hierarchy_1, String item_hierarchy_2, String item_Hierarchy_3_Code,
		String item_hierarchy_3, String item_Hierarchy_4_Code, String item_hierarchy_4, String item_Hierarchy_5_Code,
		String item_hierarchy_5, String weight, String volume, String length, String width, String height,
		String brand_Code) {
	super();
	this.report_id = report_id;
	this.facility = facility;
	this.company = company;
	this.lPN_Nbr = lPN_Nbr;
	this.status = status;
	this.qC_Status = qC_Status;
	this.vAS = vAS;
	this.current_Location = current_Location;
	this.prev_Location = prev_Location;
	this.item_Code = item_Code;
	this.is_Parent = is_Parent;
	this.alternative_Item_Code = alternative_Item_Code;
	this.description = description;
	this.orig_Qty = orig_Qty;
	this.received_Qty = received_Qty;
	this.current_Qty = current_Qty;
	this.pack_Qty = pack_Qty;
	this.case_Qty = case_Qty;
	this.nbr_Lock = nbr_Lock;
	this.create_TimeStamp = create_TimeStamp;
	this.received_TimeStamp = received_TimeStamp;
	this.priority_Date = priority_Date;
	this.putaway_Type = putaway_Type;
	this.batch_Nbr = batch_Nbr;
	this.expiry_Date = expiry_Date;
	this.mod_Time_Stamp = mod_Time_Stamp;
	this.received_Shipment_Nbr = received_Shipment_Nbr;
	this.shipment_Type = shipment_Type;
	this.ref_PO_Nbr = ref_PO_Nbr;
	this.pallet_Nbr = pallet_Nbr;
	this.allocated_Qty = allocated_Qty;
	this.attribute_A = attribute_A;
	this.attribute_B = attribute_B;
	this.attribute_C = attribute_C;
	this.attribute_D = attribute_D;
	this.attribute_E = attribute_E;
	this.attribute_F = attribute_F;
	this.attribute_G = attribute_G;
	this.attribute_H = attribute_H;
	this.attribute_I = attribute_I;
	this.attribute_J = attribute_J;
	this.attribute_K = attribute_K;
	this.attribute_L = attribute_L;
	this.attribute_M = attribute_M;
	this.attribute_N = attribute_N;
	this.attribute_O = attribute_O;
	this.received_User = received_User;
	this.mod_User = mod_User;
	this.mod_TimeStamp = mod_TimeStamp;
	this.manufacture_Date = manufacture_Date;
	this.lPN_is_Pallet = lPN_is_Pallet;
	this.item_Hierarchy_1_Code = item_Hierarchy_1_Code;
	this.item_Hierarchy_1 = item_Hierarchy_1;
	this.item_hierarchy_2 = item_hierarchy_2;
	this.item_Hierarchy_3_Code = item_Hierarchy_3_Code;
	this.item_hierarchy_3 = item_hierarchy_3;
	this.item_Hierarchy_4_Code = item_Hierarchy_4_Code;
	this.item_hierarchy_4 = item_hierarchy_4;
	this.item_Hierarchy_5_Code = item_Hierarchy_5_Code;
	this.item_hierarchy_5 = item_hierarchy_5;
	this.weight = weight;
	this.volume = volume;
	this.length = length;
	this.width = width;
	this.height = height;
	this.brand_Code = brand_Code;
}
public int getReport_id() {
	return report_id;
}
public void setReport_id(int report_id) {
	this.report_id = report_id;
}
public String getFacility() {
	return facility;
}
public void setFacility(String facility) {
	this.facility = facility;
}
public String getCompany() {
	return company;
}
public void setCompany(String company) {
	this.company = company;
}
public String getlPN_Nbr() {
	return lPN_Nbr;
}
public void setlPN_Nbr(String lPN_Nbr) {
	this.lPN_Nbr = lPN_Nbr;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getqC_Status() {
	return qC_Status;
}
public void setqC_Status(String qC_Status) {
	this.qC_Status = qC_Status;
}
public String getvAS() {
	return vAS;
}
public void setvAS(String vAS) {
	this.vAS = vAS;
}
public String getCurrent_Location() {
	return current_Location;
}
public void setCurrent_Location(String current_Location) {
	this.current_Location = current_Location;
}
public String getPrev_Location() {
	return prev_Location;
}
public void setPrev_Location(String prev_Location) {
	this.prev_Location = prev_Location;
}
public String getItem_Code() {
	return item_Code;
}
public void setItem_Code(String item_Code) {
	this.item_Code = item_Code;
}
public String getIs_Parent() {
	return is_Parent;
}
public void setIs_Parent(String is_Parent) {
	this.is_Parent = is_Parent;
}
public String getAlternative_Item_Code() {
	return alternative_Item_Code;
}
public void setAlternative_Item_Code(String alternative_Item_Code) {
	this.alternative_Item_Code = alternative_Item_Code;
}
public String getDescription() {
	return description;
}
public void setDescription(String description) {
	this.description = description;
}
public String getOrig_Qty() {
	return orig_Qty;
}
public void setOrig_Qty(String orig_Qty) {
	this.orig_Qty = orig_Qty;
}
public String getReceived_Qty() {
	return received_Qty;
}
public void setReceived_Qty(String received_Qty) {
	this.received_Qty = received_Qty;
}
public String getCurrent_Qty() {
	return current_Qty;
}
public void setCurrent_Qty(String current_Qty) {
	this.current_Qty = current_Qty;
}
public String getPack_Qty() {
	return pack_Qty;
}
public void setPack_Qty(String pack_Qty) {
	this.pack_Qty = pack_Qty;
}
public String getCase_Qty() {
	return case_Qty;
}
public void setCase_Qty(String case_Qty) {
	this.case_Qty = case_Qty;
}
public String getNbr_Lock() {
	return nbr_Lock;
}
public void setNbr_Lock(String nbr_Lock) {
	this.nbr_Lock = nbr_Lock;
}
public String getCreate_TimeStamp() {
	return create_TimeStamp;
}
public void setCreate_TimeStamp(String create_TimeStamp) {
	this.create_TimeStamp = create_TimeStamp;
}
public String getReceived_TimeStamp() {
	return received_TimeStamp;
}
public void setReceived_TimeStamp(String received_TimeStamp) {
	this.received_TimeStamp = received_TimeStamp;
}
public String getPriority_Date() {
	return priority_Date;
}
public void setPriority_Date(String priority_Date) {
	this.priority_Date = priority_Date;
}
public String getPutaway_Type() {
	return putaway_Type;
}
public void setPutaway_Type(String putaway_Type) {
	this.putaway_Type = putaway_Type;
}
public String getBatch_Nbr() {
	return batch_Nbr;
}
public void setBatch_Nbr(String batch_Nbr) {
	this.batch_Nbr = batch_Nbr;
}
public String getExpiry_Date() {
	return expiry_Date;
}
public void setExpiry_Date(String expiry_Date) {
	this.expiry_Date = expiry_Date;
}
public String getMod_Time_Stamp() {
	return mod_Time_Stamp;
}
public void setMod_Time_Stamp(String mod_Time_Stamp) {
	this.mod_Time_Stamp = mod_Time_Stamp;
}
public String getReceived_Shipment_Nbr() {
	return received_Shipment_Nbr;
}
public void setReceived_Shipment_Nbr(String received_Shipment_Nbr) {
	this.received_Shipment_Nbr = received_Shipment_Nbr;
}
public String getShipment_Type() {
	return shipment_Type;
}
public void setShipment_Type(String shipment_Type) {
	this.shipment_Type = shipment_Type;
}
public String getRef_PO_Nbr() {
	return ref_PO_Nbr;
}
public void setRef_PO_Nbr(String ref_PO_Nbr) {
	this.ref_PO_Nbr = ref_PO_Nbr;
}
public String getPallet_Nbr() {
	return pallet_Nbr;
}
public void setPallet_Nbr(String pallet_Nbr) {
	this.pallet_Nbr = pallet_Nbr;
}
public String getAllocated_Qty() {
	return allocated_Qty;
}
public void setAllocated_Qty(String allocated_Qty) {
	this.allocated_Qty = allocated_Qty;
}
public String getAttribute_A() {
	return attribute_A;
}
public void setAttribute_A(String attribute_A) {
	this.attribute_A = attribute_A;
}
public String getAttribute_B() {
	return attribute_B;
}
public void setAttribute_B(String attribute_B) {
	this.attribute_B = attribute_B;
}
public String getAttribute_C() {
	return attribute_C;
}
public void setAttribute_C(String attribute_C) {
	this.attribute_C = attribute_C;
}
public String getAttribute_D() {
	return attribute_D;
}
public void setAttribute_D(String attribute_D) {
	this.attribute_D = attribute_D;
}
public String getAttribute_E() {
	return attribute_E;
}
public void setAttribute_E(String attribute_E) {
	this.attribute_E = attribute_E;
}
public String getAttribute_F() {
	return attribute_F;
}
public void setAttribute_F(String attribute_F) {
	this.attribute_F = attribute_F;
}
public String getAttribute_G() {
	return attribute_G;
}
public void setAttribute_G(String attribute_G) {
	this.attribute_G = attribute_G;
}
public String getAttribute_H() {
	return attribute_H;
}
public void setAttribute_H(String attribute_H) {
	this.attribute_H = attribute_H;
}
public String getAttribute_I() {
	return attribute_I;
}
public void setAttribute_I(String attribute_I) {
	this.attribute_I = attribute_I;
}
public String getAttribute_J() {
	return attribute_J;
}
public void setAttribute_J(String attribute_J) {
	this.attribute_J = attribute_J;
}
public String getAttribute_K() {
	return attribute_K;
}
public void setAttribute_K(String attribute_K) {
	this.attribute_K = attribute_K;
}
public String getAttribute_L() {
	return attribute_L;
}
public void setAttribute_L(String attribute_L) {
	this.attribute_L = attribute_L;
}
public String getAttribute_M() {
	return attribute_M;
}
public void setAttribute_M(String attribute_M) {
	this.attribute_M = attribute_M;
}
public String getAttribute_N() {
	return attribute_N;
}
public void setAttribute_N(String attribute_N) {
	this.attribute_N = attribute_N;
}
public String getAttribute_O() {
	return attribute_O;
}
public void setAttribute_O(String attribute_O) {
	this.attribute_O = attribute_O;
}
public String getReceived_User() {
	return received_User;
}
public void setReceived_User(String received_User) {
	this.received_User = received_User;
}
public String getMod_User() {
	return mod_User;
}
public void setMod_User(String mod_User) {
	this.mod_User = mod_User;
}
public String getMod_TimeStamp() {
	return mod_TimeStamp;
}
public void setMod_TimeStamp(String mod_TimeStamp) {
	this.mod_TimeStamp = mod_TimeStamp;
}
public String getManufacture_Date() {
	return manufacture_Date;
}
public void setManufacture_Date(String manufacture_Date) {
	this.manufacture_Date = manufacture_Date;
}
public String getlPN_is_Pallet() {
	return lPN_is_Pallet;
}
public void setlPN_is_Pallet(String lPN_is_Pallet) {
	this.lPN_is_Pallet = lPN_is_Pallet;
}
public String getItem_Hierarchy_1_Code() {
	return item_Hierarchy_1_Code;
}
public void setItem_Hierarchy_1_Code(String item_Hierarchy_1_Code) {
	this.item_Hierarchy_1_Code = item_Hierarchy_1_Code;
}
public String getItem_Hierarchy_1() {
	return item_Hierarchy_1;
}
public void setItem_Hierarchy_1(String item_Hierarchy_1) {
	this.item_Hierarchy_1 = item_Hierarchy_1;
}
public String getItem_hierarchy_2() {
	return item_hierarchy_2;
}
public void setItem_hierarchy_2(String item_hierarchy_2) {
	this.item_hierarchy_2 = item_hierarchy_2;
}
public String getItem_Hierarchy_3_Code() {
	return item_Hierarchy_3_Code;
}
public void setItem_Hierarchy_3_Code(String item_Hierarchy_3_Code) {
	this.item_Hierarchy_3_Code = item_Hierarchy_3_Code;
}
public String getItem_hierarchy_3() {
	return item_hierarchy_3;
}
public void setItem_hierarchy_3(String item_hierarchy_3) {
	this.item_hierarchy_3 = item_hierarchy_3;
}
public String getItem_Hierarchy_4_Code() {
	return item_Hierarchy_4_Code;
}
public void setItem_Hierarchy_4_Code(String item_Hierarchy_4_Code) {
	this.item_Hierarchy_4_Code = item_Hierarchy_4_Code;
}
public String getItem_hierarchy_4() {
	return item_hierarchy_4;
}
public void setItem_hierarchy_4(String item_hierarchy_4) {
	this.item_hierarchy_4 = item_hierarchy_4;
}
public String getItem_Hierarchy_5_Code() {
	return item_Hierarchy_5_Code;
}
public void setItem_Hierarchy_5_Code(String item_Hierarchy_5_Code) {
	this.item_Hierarchy_5_Code = item_Hierarchy_5_Code;
}
public String getItem_hierarchy_5() {
	return item_hierarchy_5;
}
public void setItem_hierarchy_5(String item_hierarchy_5) {
	this.item_hierarchy_5 = item_hierarchy_5;
}
public String getWeight() {
	return weight;
}
public void setWeight(String weight) {
	this.weight = weight;
}
public String getVolume() {
	return volume;
}
public void setVolume(String volume) {
	this.volume = volume;
}
public String getLength() {
	return length;
}
public void setLength(String length) {
	this.length = length;
}
public String getWidth() {
	return width;
}
public void setWidth(String width) {
	this.width = width;
}
public String getHeight() {
	return height;
}
public void setHeight(String height) {
	this.height = height;
}
public String getBrand_Code() {
	return brand_Code;
}
public void setBrand_Code(String brand_Code) {
	this.brand_Code = brand_Code;
}
@Override
public String toString() {
	return "IBLPNReports [report_id=" + report_id + ", facility=" + facility + ", company=" + company + ", lPN_Nbr="
			+ lPN_Nbr + ", status=" + status + ", qC_Status=" + qC_Status + ", vAS=" + vAS + ", current_Location="
			+ current_Location + ", prev_Location=" + prev_Location + ", item_Code=" + item_Code + ", is_Parent="
			+ is_Parent + ", alternative_Item_Code=" + alternative_Item_Code + ", description=" + description
			+ ", orig_Qty=" + orig_Qty + ", received_Qty=" + received_Qty + ", current_Qty=" + current_Qty
			+ ", pack_Qty=" + pack_Qty + ", case_Qty=" + case_Qty + ", nbr_Lock=" + nbr_Lock + ", create_TimeStamp="
			+ create_TimeStamp + ", received_TimeStamp=" + received_TimeStamp + ", priority_Date=" + priority_Date
			+ ", putaway_Type=" + putaway_Type + ", batch_Nbr=" + batch_Nbr + ", expiry_Date=" + expiry_Date
			+ ", mod_Time_Stamp=" + mod_Time_Stamp + ", received_Shipment_Nbr=" + received_Shipment_Nbr
			+ ", shipment_Type=" + shipment_Type + ", ref_PO_Nbr=" + ref_PO_Nbr + ", pallet_Nbr=" + pallet_Nbr
			+ ", allocated_Qty=" + allocated_Qty + ", attribute_A=" + attribute_A + ", attribute_B=" + attribute_B
			+ ", attribute_C=" + attribute_C + ", attribute_D=" + attribute_D + ", attribute_E=" + attribute_E
			+ ", attribute_F=" + attribute_F + ", attribute_G=" + attribute_G + ", attribute_H=" + attribute_H
			+ ", attribute_I=" + attribute_I + ", attribute_J=" + attribute_J + ", attribute_K=" + attribute_K
			+ ", attribute_L=" + attribute_L + ", attribute_M=" + attribute_M + ", attribute_N=" + attribute_N
			+ ", attribute_O=" + attribute_O + ", received_User=" + received_User + ", mod_User=" + mod_User
			+ ", mod_TimeStamp=" + mod_TimeStamp + ", manufacture_Date=" + manufacture_Date + ", lPN_is_Pallet="
			+ lPN_is_Pallet + ", item_Hierarchy_1_Code=" + item_Hierarchy_1_Code + ", item_Hierarchy_1="
			+ item_Hierarchy_1 + ", item_hierarchy_2=" + item_hierarchy_2 + ", item_Hierarchy_3_Code="
			+ item_Hierarchy_3_Code + ", item_hierarchy_3=" + item_hierarchy_3 + ", item_Hierarchy_4_Code="
			+ item_Hierarchy_4_Code + ", item_hierarchy_4=" + item_hierarchy_4 + ", item_Hierarchy_5_Code="
			+ item_Hierarchy_5_Code + ", item_hierarchy_5=" + item_hierarchy_5 + ", weight=" + weight + ", volume="
			+ volume + ", length=" + length + ", width=" + width + ", height=" + height + ", brand_Code=" + brand_Code
			+ "]";
}
}
