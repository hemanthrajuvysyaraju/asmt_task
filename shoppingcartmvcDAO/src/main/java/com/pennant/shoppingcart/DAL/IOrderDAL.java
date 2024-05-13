package com.pennant.shoppingcart.DAL;

import com.pennant.shoppingcart.models.CouponModel;
import com.pennant.shoppingcart.models.OrderModel;

public interface IOrderDAL {
	public Double getGst(Integer id);

	public Double getShippingCharge(Double total);

	public CouponModel checkAndGetCoupon(CouponModel coupon);
	
	public OrderModel doOrder(OrderModel order);
}
