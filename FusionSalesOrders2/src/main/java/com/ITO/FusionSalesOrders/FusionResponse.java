package com.ITO.FusionSalesOrders;

import java.util.ArrayList;
import org.springframework.stereotype.Component;
/**
 * This class will help us to receive response from Fusion
 *
 */
@Component
public class FusionResponse {
	private ArrayList<Object> items = new ArrayList<Object>();

	public FusionResponse(ArrayList<Object> items) {
		super();
		this.items = items;
	}

	public FusionResponse() {
		super();
	}

	public ArrayList<Object> getItems() {
		return items;
	}

	public void setItems(ArrayList<Object> items) {
		this.items = items;
	}

	@Override
	public String toString() {
		return "FusionResponse [items=" + items + "]";
	}
	
}
