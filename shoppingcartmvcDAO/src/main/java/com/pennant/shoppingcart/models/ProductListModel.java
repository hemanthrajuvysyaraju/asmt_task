package com.pennant.shoppingcart.models;

import java.util.ArrayList;
import java.util.Comparator;

public class ProductListModel extends ArrayList<ProductModel> {
	private static final long serialVersionUID = -935838833218226171L;
	private Double orderTotal;
	private Double gst;
	private String coupon_Code;
	public String getCoupon_Code() {
		return coupon_Code;
	}

	public void setCoupon_Code(String coupon_Code) {
		this.coupon_Code = coupon_Code;
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

	public void sortByPriceASC() {
		this.sort(Comparator.comparingDouble(ProductModel::getProd_Price));
	}

	public void sortByPriceDESC() {
		this.sort(Comparator.comparingDouble(ProductModel::getProd_Price).reversed());
	}
}
