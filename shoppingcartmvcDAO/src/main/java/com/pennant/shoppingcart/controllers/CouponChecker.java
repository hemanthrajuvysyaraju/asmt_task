package com.pennant.shoppingcart.controllers;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import org.json.JSONObject;

import com.pennant.shoppingcart.ServiceFactory.ServiceFactory;
import com.pennant.shoppingcart.models.CouponModel;

public class CouponChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setHeader("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
		response.setHeader("Access-Control-Allow-Headers", "Content-Type, Authorization");
		response.addHeader("Access-Control-Allow-Origin", "*");
		CouponModel coupon = new CouponModel();
		String cpncode = request.getParameter("cpncode");
		coupon.setCode(cpncode);
		coupon = ServiceFactory.getOrderImpl().checkAndGetCoupon(coupon);
		response.setContentType("application/json");
		JSONObject obj = new JSONObject();
		obj.put("validity", coupon.getValid());
		obj.put("value", coupon.getValue());
		obj.put("min_value", coupon.getMin_Value());
		response.getWriter().println(obj);
	}
	@Override
	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doPost(req, resp);
	}
}
