package com.pennant.shoppingcart.models;

public class ProductModel {
	private Integer prod_Id;
	private String prod_Name;
	private Integer prod_Cat_Id;
	private Double prod_Price;
	private String prod_Image;
	private Integer prod_Qty;
	private Double prod_TotalFare;
	private Double prod_Gst;
	private Double prod_Shippingcharge;
	private Double prod_ShippingCharge_Gst;
	private Double gst_Percent;
	public Double getGst_Percent() {
		return gst_Percent;
	}

	public void setGst_Percent(Double gst_Percent) {
		this.gst_Percent = gst_Percent;
	}

	public Double getProd_Gst() {
		return prod_Gst;
	}

	public void setProd_Gst(Double prod_Gst) {
		this.prod_Gst = prod_Gst;
	}


	public Integer getProd_Cat_Id() {
		return prod_Cat_Id;
	}

	public void setProd_Cat_Id(Integer prod_Cat_Id) {
		this.prod_Cat_Id = prod_Cat_Id;
	}

	public Integer getProd_Id() {
		return prod_Id;
	}

	public void setProd_Id(Integer prod_Id) {
		this.prod_Id = prod_Id;
	}

	public String getProd_Name() {
		return prod_Name;
	}

	public void setProd_Name(String prod_Name) {
		this.prod_Name = prod_Name;
	}

	public Double getProd_Price() {
		return prod_Price;
	}

	public void setProd_Price(Double prod_Price) {
		this.prod_Price = prod_Price;
	}

	public String getProd_Image() {
		return prod_Image;
	}

	public void setProd_Image(String prod_Image) {
		this.prod_Image = prod_Image;
	}

	public Integer getProd_Qty() {
		return prod_Qty;
	}

	public void setProd_Qty(Integer prod_Qty) {
		this.prod_Qty = prod_Qty;
	}

	public Double getProd_TotalFare() {
		return prod_TotalFare;
	}

	public void setProd_TotalFare(Double prod_TotalFare) {
		this.prod_TotalFare = prod_TotalFare;
	}

	public Double getProd_Shippingcharge() {
		return prod_Shippingcharge;
	}

	public void setProd_Shippingcharge(Double prod_Shippingcharge) {
		this.prod_Shippingcharge = prod_Shippingcharge;
	}

	public Double getProd_ShippingCharge_Gst() {
		return prod_ShippingCharge_Gst;
	}

	public void setProd_ShippingCharge_Gst(Double prod_ShippingCharge_Gst) {
		this.prod_ShippingCharge_Gst = prod_ShippingCharge_Gst;
	}

}
