package com.naveen.ReddyPOS.model;

import java.util.List;

public class Receipt {

	private String storeName;
	private String storeAddress;
	private String storePhone;
	private List<Products> products;
	private Double subtotal;
	private Double taxAmountOnSubtotal;
	private Double total;

	public List<Products> getProducts() {
		return products;
	}

	public void setProducts(List<Products> products) {
		this.products = products;
	}

	public Double getSubtotal() {
		return subtotal;
	}

	public Double setSubtotal(Double subtotal) {
		return this.subtotal = subtotal;
	}

	public String getStoreAddress() {
		return storeAddress;
	}

	public void setStoreAddress(String storeAddress) {
		this.storeAddress = storeAddress;
	}

	public String getStorePhone() {
		return storePhone;
	}

	public void setStorePhone(String storePhone) {
		this.storePhone = storePhone;
	}

	public String getStoreName() {
		return storeName;
	}

	public void setStoreName(String storeName) {
		this.storeName = storeName;
	}

	public Double getTotal() {
		return total;
	}

	public void setTotal(Double total) {
		this.total = total;
	}

	public Double getTaxAmountOnSubtotal() {
		return taxAmountOnSubtotal;
	}

	public Double setTaxAmountOnSubtotal(Double taxAmountOnSubtotal) {
		return this.taxAmountOnSubtotal = taxAmountOnSubtotal;
	}
}
