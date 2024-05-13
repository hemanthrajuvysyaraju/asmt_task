package com.pennant.shoppingcart.BLL;

import com.pennant.shoppingcart.DAL.OrderDALImpl;
import com.pennant.shoppingcart.models.OrderModel;
import com.pennant.shoppingcart.models.ProductListModel;

public class CalculateToatalBLL {
	private static Double prods_TotalFare = 0.0;
	private static Double prods_Totalgst = 0.0;

	public static OrderModel doProcess(ProductListModel products) {
		OrderModel order = new OrderModel();
		products.forEach(product -> {
			OrderDALImpl ord = new OrderDALImpl();
			Double gst = ord.getGst(product.getProd_Id());
			double _gst= ((100/gst)+1);
			double prod_Value=product.getProd_TotalFare()/_gst;
			product.setGst_Percent(gst);
			product.setProd_Price(prod_Value);
			double prod_gst= product.getProd_TotalFare()-prod_Value;
			product.setProd_Gst(prod_gst);
			prods_Totalgst += prod_gst;
			prods_TotalFare += product.getProd_TotalFare();
		});
		order.setOrderTotal(prods_TotalFare);
		order.setGst(prods_Totalgst);
		Double shippingCharge = new OrderDALImpl().getShippingCharge(prods_TotalFare);
		products.forEach(product->{
			double prod_share=prods_TotalFare/product.getProd_TotalFare();
			double shipping_charge_for_prod=(prod_share)*shippingCharge;
			double _shippingchargeexclusivegst=shipping_charge_for_prod/(1+product.getGst_Percent());
			double _shippingchargegst=shipping_charge_for_prod-_shippingchargeexclusivegst;
			order.setShipping_charges(order.getShipping_charges()+_shippingchargeexclusivegst);
			order.setGstforshipping(order.getGstforshipping()+_shippingchargegst);
			product.setProd_Shippingcharge(_shippingchargeexclusivegst);
			product.setProd_ShippingCharge_Gst(_shippingchargegst);
			order.setProducts_Id(product.getProd_Id());
		});
		order.setCouponCode(products.getCoupon_Code());
		order.setTotalInclAllTax(
				order.getGstforshipping() + order.getOrderTotal() + order.getGst() + order.getShipping_charges());
		return order;
	}
}
