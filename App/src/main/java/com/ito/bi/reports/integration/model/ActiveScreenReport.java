package com.ito.bi.reports.integration.model;

import org.springframework.stereotype.Component;

@Component
public class ActiveScreenReport 
{
	private int report_id;
	private String facility;
	private String location;
	private String location_Barcode;
	private String location_Type;
	private String location_Size_Type;
	private String area;
	private String item_Assignment_Type;
	private String hierarchy_Code_3;
	private String item_Code;
	private String is_Parent;
	private String alternative_Item_Code;
	private String item_Description;
	private String current_Qty;
	private String allocated_Qty;
	private String batch_Nbr;
	private String expiry_date;
	private String orig_Qty;
	private String last_Counted_At;
	private String mod_Time_Stamp;
	private String to_Be_Counted_Flag;
	private String to_Be_Counted_TS;
	private String location_Lock_Code;
	private String mod_User;
	private String last_Counted_By;
	private String max_Units;
	private String max_Lpns;
	private String min_Units;
	private String batch_Nbr_Lock;
	private String in_Transit;
	private String brand_Code;
	private String priority_date;
	private String maximum_Volume;
	private String putaway_Type;
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
	private String replenishment_Zone;
	private String company;

	public ActiveScreenReport()
	{
		super();
	}

	public ActiveScreenReport(int report_id, String facility, String location, String location_Barcode,
			String location_Type, String location_Size_Type, String area, String item_Assignment_Type,
			String hierarchy_Code_3, String item_Code, String is_Parent, String alternative_Item_Code,
			String item_Description, String current_Qty, String allocated_Qty, String batch_Nbr, String expiry_date,
			String orig_Qty, String last_Counted_At, String mod_Time_Stamp, String to_Be_Counted_Flag,
			String to_Be_Counted_TS, String location_Lock_Code, String mod_User, String last_Counted_By,
			String max_Units, String max_Lpns, String min_Units, String batch_Nbr_Lock, String in_Transit,
			String brand_Code, String priority_date, String maximum_Volume, String putaway_Type, String attribute_A,
			String attribute_B, String attribute_C, String attribute_D, String attribute_E, String attribute_F,
			String attribute_G, String attribute_H, String attribute_I, String attribute_J, String attribute_K,
			String attribute_L, String attribute_M, String attribute_N, String attribute_O, String replenishment_Zone,
			String company) {
		super();
		this.report_id = report_id;
		this.facility = facility;
		this.location = location;
		this.location_Barcode = location_Barcode;
		this.location_Type = location_Type;
		this.location_Size_Type = location_Size_Type;
		this.area = area;
		this.item_Assignment_Type = item_Assignment_Type;
		this.hierarchy_Code_3 = hierarchy_Code_3;
		this.item_Code = item_Code;
		this.is_Parent = is_Parent;
		this.alternative_Item_Code = alternative_Item_Code;
		this.item_Description = item_Description;
		this.current_Qty = current_Qty;
		this.allocated_Qty = allocated_Qty;
		this.batch_Nbr = batch_Nbr;
		this.expiry_date = expiry_date;
		this.orig_Qty = orig_Qty;
		this.last_Counted_At = last_Counted_At;
		this.mod_Time_Stamp = mod_Time_Stamp;
		this.to_Be_Counted_Flag = to_Be_Counted_Flag;
		this.to_Be_Counted_TS = to_Be_Counted_TS;
		this.location_Lock_Code = location_Lock_Code;
		this.mod_User = mod_User;
		this.last_Counted_By = last_Counted_By;
		this.max_Units = max_Units;
		this.max_Lpns = max_Lpns;
		this.min_Units = min_Units;
		this.batch_Nbr_Lock = batch_Nbr_Lock;
		this.in_Transit = in_Transit;
		this.brand_Code = brand_Code;
		this.priority_date = priority_date;
		this.maximum_Volume = maximum_Volume;
		this.putaway_Type = putaway_Type;
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
		this.replenishment_Zone = replenishment_Zone;
		this.company = company;
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

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation_Barcode() {
		return location_Barcode;
	}

	public void setLocation_Barcode(String location_Barcode) {
		this.location_Barcode = location_Barcode;
	}

	public String getLocation_Type() {
		return location_Type;
	}

	public void setLocation_Type(String location_Type) {
		this.location_Type = location_Type;
	}

	public String getLocation_Size_Type() {
		return location_Size_Type;
	}

	public void setLocation_Size_Type(String location_Size_Type) {
		this.location_Size_Type = location_Size_Type;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getItem_Assignment_Type() {
		return item_Assignment_Type;
	}

	public void setItem_Assignment_Type(String item_Assignment_Type) {
		this.item_Assignment_Type = item_Assignment_Type;
	}

	public String getHierarchy_Code_3() {
		return hierarchy_Code_3;
	}

	public void setHierarchy_Code_3(String hierarchy_Code_3) {
		this.hierarchy_Code_3 = hierarchy_Code_3;
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

	public String getItem_Description() {
		return item_Description;
	}

	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}

	public String getCurrent_Qty() {
		return current_Qty;
	}

	public void setCurrent_Qty(String current_Qty) {
		this.current_Qty = current_Qty;
	}

	public String getAllocated_Qty() {
		return allocated_Qty;
	}

	public void setAllocated_Qty(String allocated_Qty) {
		this.allocated_Qty = allocated_Qty;
	}

	public String getBatch_Nbr() {
		return batch_Nbr;
	}

	public void setBatch_Nbr(String batch_Nbr) {
		this.batch_Nbr = batch_Nbr;
	}

	public String getExpiry_date() {
		return expiry_date;
	}

	public void setExpiry_date(String expiry_date) {
		this.expiry_date = expiry_date;
	}

	public String getOrig_Qty() {
		return orig_Qty;
	}

	public void setOrig_Qty(String orig_Qty) {
		this.orig_Qty = orig_Qty;
	}

	public String getLast_Counted_At() {
		return last_Counted_At;
	}

	public void setLast_Counted_At(String last_Counted_At) {
		this.last_Counted_At = last_Counted_At;
	}

	public String getMod_Time_Stamp() {
		return mod_Time_Stamp;
	}

	public void setMod_Time_Stamp(String mod_Time_Stamp) {
		this.mod_Time_Stamp = mod_Time_Stamp;
	}

	public String getTo_Be_Counted_Flag() {
		return to_Be_Counted_Flag;
	}

	public void setTo_Be_Counted_Flag(String to_Be_Counted_Flag) {
		this.to_Be_Counted_Flag = to_Be_Counted_Flag;
	}

	public String getTo_Be_Counted_TS() {
		return to_Be_Counted_TS;
	}

	public void setTo_Be_Counted_TS(String to_Be_Counted_TS) {
		this.to_Be_Counted_TS = to_Be_Counted_TS;
	}

	public String getLocation_Lock_Code() {
		return location_Lock_Code;
	}

	public void setLocation_Lock_Code(String location_Lock_Code) {
		this.location_Lock_Code = location_Lock_Code;
	}

	public String getMod_User() {
		return mod_User;
	}

	public void setMod_User(String mod_User) {
		this.mod_User = mod_User;
	}

	public String getLast_Counted_By() {
		return last_Counted_By;
	}

	public void setLast_Counted_By(String last_Counted_By) {
		this.last_Counted_By = last_Counted_By;
	}

	public String getMax_Units() {
		return max_Units;
	}

	public void setMax_Units(String max_Units) {
		this.max_Units = max_Units;
	}

	public String getMax_Lpns() {
		return max_Lpns;
	}

	public void setMax_Lpns(String max_Lpns) {
		this.max_Lpns = max_Lpns;
	}

	public String getMin_Units() {
		return min_Units;
	}

	public void setMin_Units(String min_Units) {
		this.min_Units = min_Units;
	}

	public String getBatch_Nbr_Lock() {
		return batch_Nbr_Lock;
	}

	public void setBatch_Nbr_Lock(String batch_Nbr_Lock) {
		this.batch_Nbr_Lock = batch_Nbr_Lock;
	}

	public String getIn_Transit() {
		return in_Transit;
	}

	public void setIn_Transit(String in_Transit) {
		this.in_Transit = in_Transit;
	}

	public String getBrand_Code() {
		return brand_Code;
	}

	public void setBrand_Code(String brand_Code) {
		this.brand_Code = brand_Code;
	}

	public String getPriority_date() {
		return priority_date;
	}

	public void setPriority_date(String priority_date) {
		this.priority_date = priority_date;
	}

	public String getMaximum_Volume() {
		return maximum_Volume;
	}

	public void setMaximum_Volume(String maximum_Volume) {
		this.maximum_Volume = maximum_Volume;
	}

	public String getPutaway_Type() {
		return putaway_Type;
	}

	public void setPutaway_Type(String putaway_Type) {
		this.putaway_Type = putaway_Type;
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

	public String getReplenishment_Zone() {
		return replenishment_Zone;
	}

	public void setReplenishment_Zone(String replenishment_Zone) {
		this.replenishment_Zone = replenishment_Zone;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	@Override
	public String toString() {
		return "ActiveScreenReport [report_id=" + report_id + ", facility=" + facility + ", location=" + location
				+ ", location_Barcode=" + location_Barcode + ", location_Type=" + location_Type
				+ ", location_Size_Type=" + location_Size_Type + ", area=" + area + ", item_Assignment_Type="
				+ item_Assignment_Type + ", hierarchy_Code_3=" + hierarchy_Code_3 + ", item_Code=" + item_Code
				+ ", is_Parent=" + is_Parent + ", alternative_Item_Code=" + alternative_Item_Code
				+ ", item_Description=" + item_Description + ", current_Qty=" + current_Qty + ", allocated_Qty="
				+ allocated_Qty + ", batch_Nbr=" + batch_Nbr + ", expiry_date=" + expiry_date + ", orig_Qty=" + orig_Qty
				+ ", last_Counted_At=" + last_Counted_At + ", mod_Time_Stamp=" + mod_Time_Stamp
				+ ", to_Be_Counted_Flag=" + to_Be_Counted_Flag + ", to_Be_Counted_TS=" + to_Be_Counted_TS
				+ ", location_Lock_Code=" + location_Lock_Code + ", mod_User=" + mod_User + ", last_Counted_By="
				+ last_Counted_By + ", max_Units=" + max_Units + ", max_Lpns=" + max_Lpns + ", min_Units=" + min_Units
				+ ", batch_Nbr_Lock=" + batch_Nbr_Lock + ", in_Transit=" + in_Transit + ", brand_Code=" + brand_Code
				+ ", priority_date=" + priority_date + ", maximum_Volume=" + maximum_Volume + ", putaway_Type="
				+ putaway_Type + ", attribute_A=" + attribute_A + ", attribute_B=" + attribute_B + ", attribute_C="
				+ attribute_C + ", attribute_D=" + attribute_D + ", attribute_E=" + attribute_E + ", attribute_F="
				+ attribute_F + ", attribute_G=" + attribute_G + ", attribute_H=" + attribute_H + ", attribute_I="
				+ attribute_I + ", attribute_J=" + attribute_J + ", attribute_K=" + attribute_K + ", attribute_L="
				+ attribute_L + ", attribute_M=" + attribute_M + ", attribute_N=" + attribute_N + ", attribute_O="
				+ attribute_O + ", replenishment_Zone=" + replenishment_Zone + ", company=" + company + "]";
	}
	
}