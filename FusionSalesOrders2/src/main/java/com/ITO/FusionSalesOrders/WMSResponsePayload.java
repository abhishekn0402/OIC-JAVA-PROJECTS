package com.ITO.FusionSalesOrders;

import org.springframework.stereotype.Component;
/**
 * This class will help us to append the received response from WMS
 *
 */
@Component
public class WMSResponsePayload {
	/*
	 *WMS Order details  
	 */
	private String orderNumber;
	private String wmsOrderHeaderId;
	private String wmsOrderDetailId;
	private String wmsOrderDetailItemId;
	private String wms_Order_Status; // WMS Order details Status --it is there order hdr api
	private String created_Ts; // WMS_Create Timestamps -- it is there order dtl api
	private String seq_nbr; // Order Line -- it is there order dtl api
	private String itemcode; // Item Name  -- this is not there in order dtl api
	private String itemId;
	private String item_Description; //Item Description -- this is not there in order dtl api
	private String order_Qty;
	
	public WMSResponsePayload() {
		super();
	}

	public WMSResponsePayload(String orderNumber, String wmsOrderHeaderId, String wmsOrderDetailId,
			String wmsOrderDetailItemId, String wms_Order_Status, String created_Ts, String seq_nbr, String itemcode,
			String itemId, String item_Description, String order_Qty) {
		super();
		this.orderNumber = orderNumber;
		this.wmsOrderHeaderId = wmsOrderHeaderId;
		this.wmsOrderDetailId = wmsOrderDetailId;
		this.wmsOrderDetailItemId = wmsOrderDetailItemId;
		this.wms_Order_Status = wms_Order_Status;
		this.created_Ts = created_Ts;
		this.seq_nbr = seq_nbr;
		this.itemcode = itemcode;
		this.itemId = itemId;
		this.item_Description = item_Description;
		this.order_Qty = order_Qty;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getWmsOrderHeaderId() {
		return wmsOrderHeaderId;
	}

	public void setWmsOrderHeaderId(String wmsOrderHeaderId) {
		this.wmsOrderHeaderId = wmsOrderHeaderId;
	}

	public String getWmsOrderDetailId() {
		return wmsOrderDetailId;
	}

	public void setWmsOrderDetailId(String wmsOrderDetailId) {
		this.wmsOrderDetailId = wmsOrderDetailId;
	}

	public String getWmsOrderDetailItemId() {
		return wmsOrderDetailItemId;
	}

	public void setWmsOrderDetailItemId(String wmsOrderDetailItemId) {
		this.wmsOrderDetailItemId = wmsOrderDetailItemId;
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

	public String getItemId() {
		return itemId;
	}

	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	public String getItem_Description() {
		return item_Description;
	}

	public void setItem_Description(String item_Description) {
		this.item_Description = item_Description;
	}

	public String getOrder_Qty() {
		return order_Qty;
	}

	public void setOrder_Qty(String order_Qty) {
		this.order_Qty = order_Qty;
	}

	@Override
	public String toString() {
		return "WMSResponsePayload [orderNumber=" + orderNumber + ", wmsOrderHeaderId=" + wmsOrderHeaderId
				+ ", wmsOrderDetailId=" + wmsOrderDetailId + ", wmsOrderDetailItemId=" + wmsOrderDetailItemId
				+ ", wms_Order_Status=" + wms_Order_Status + ", created_Ts=" + created_Ts + ", seq_nbr=" + seq_nbr
				+ ", itemcode=" + itemcode + ", itemId=" + itemId + ", item_Description=" + item_Description
				+ ", order_Qty=" + order_Qty + "]";
	}
}
