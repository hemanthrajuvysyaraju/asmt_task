package com.pennant.shoppingcart.models;

public class CouponModel {
	private Integer cpn_Id;
	private Double value;
	private Boolean valid;
	private String code;
	private Double min_Value;
	public Double getMin_Value() {
		return min_Value;
	}

	public void setMin_Value(Double min_Value) {
		this.min_Value = min_Value;
	}

	public Double getValue() {
		return value;
	}

	public void setValue(Double value) {
		this.value = value;
	}

	public Boolean getValid() {
		return valid;
	}

	public void setValid(Boolean valid) {
		this.valid = valid;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getCpn_Id() {
		return cpn_Id;
	}

	public void setCpn_Id(Integer cpn_Id) {
		this.cpn_Id = cpn_Id;
	}

}
