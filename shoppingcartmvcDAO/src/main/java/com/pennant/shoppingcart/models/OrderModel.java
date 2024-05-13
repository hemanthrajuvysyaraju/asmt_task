package com.pennant.shoppingcart.models;

import java.util.ArrayList;

public class OrderModel {
	private ArrayList<Integer> products_Id= new ArrayList<>();
	private Integer order_Id;
	private Double orderTotal = 0.00;
	private Double gst = 0.00;
	private Double shipping_charges = 0.00;
	private Double gstforshipping = 0.00;
	private Double totalInclAllTax = 0.00;
	private String couponCode = null;

	public double getTotalInclAllTax() {
		return totalInclAllTax;
	}

	public void setTotalInclAllTax(double totalInclAllTax) {
		this.totalInclAllTax = totalInclAllTax;
	}

	public Double getShipping_charges() {
		return shipping_charges;
	}

	public void setShipping_charges(Double shipping_charges) {
		this.shipping_charges = shipping_charges;
	}

	public double getGstforshipping() {
		return gstforshipping;
	}

	public void setGstforshipping(double gstforshipping) {
		this.gstforshipping = gstforshipping;
	}

	public Double getGst() {
		return gst;
	}

	public void setGst(Double gst) {
		this.gst = gst;
	}

	public Double getOrderTotal() {
		return orderTotal;
	}

	public void setOrderTotal(Double orderTotal) {
		this.orderTotal = orderTotal;
	}

	public ArrayList<Integer> getProducts_Id() {
		return products_Id;
	}

	public void setProducts_Id(Integer product_Id) {
		this.products_Id.add(product_Id);
	}

	public Integer getOrder_Id() {
		return order_Id;
	}

	public void setOrder_Id(Integer order_Id) {
		this.order_Id = order_Id;
	}

	public String getCouponCode() {
		return couponCode;
	}

	public void setCouponCode(String couponCode) {
		this.couponCode = couponCode;
	}
}