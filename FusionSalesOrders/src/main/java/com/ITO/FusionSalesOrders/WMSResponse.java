package com.ITO.FusionSalesOrders;

import java.util.ArrayList;

import org.springframework.stereotype.Component;
/**
 * This class will help us to receive response from WMS
 *
 */
@Component
public class WMSResponse {
	private int result_count;
	private int page_count;
	private int page_nbr;
	private String next_page = null;
	private String previous_page = null;
	ArrayList<Object> results = new ArrayList<Object>();
	public WMSResponse(int result_count, int page_count, int page_nbr, String next_page,
			String previous_page, ArrayList<Object> results) {
		super();
		this.result_count = result_count;
		this.page_count = page_count;
		this.page_nbr = page_nbr;
		this.next_page = next_page;
		this.previous_page = previous_page;
		this.results = results;
	}
	
	public WMSResponse() {
		super();
	}

	public int getResult_count() {
		return result_count;
	}
	public void setResult_count(int result_count) {
		this.result_count = result_count;
	}
	public int getPage_count() {
		return page_count;
	}
	public void setPage_count(int page_count) {
		this.page_count = page_count;
	}
	public int getPage_nbr() {
		return page_nbr;
	}
	public void setPage_nbr(int page_nbr) {
		this.page_nbr = page_nbr;
	}
	public String getNext_page() {
		return next_page;
	}
	public void setNext_page(String next_page) {
		this.next_page = next_page;
	}
	public String getPrevious_page() {
		return previous_page;
	}
	public void setPrevious_page(String previous_page) {
		this.previous_page = previous_page;
	}
	public ArrayList<Object> getResults() {
		return results;
	}
	public void setResults(ArrayList<Object> results) {
		this.results = results;
	}
	@Override
	public String toString() {
		return "WMSResponse [result_count=" + result_count + ", page_count=" + page_count + ", page_nbr="
				+ page_nbr + ", next_page=" + next_page + ", previous_page=" + previous_page + ", results=" + results
				+ "]";
	}
}
