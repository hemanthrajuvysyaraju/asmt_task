package com.pennant.shoppingcart.controllers;

import java.io.IOException;

import org.json.JSONObject;

import com.pennant.shoppingcart.ServiceFactory.ServiceFactory;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class PincodeChecker
 */
@WebServlet("/checkpincode")
public class PincodeChecker extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Integer pincode = Integer.parseInt(request.getParameter("pincode"));
		Integer prodid = Integer.parseInt(request.getParameter("productid"));
		Boolean isServicable = ServiceFactory.getProductsImpl().searchService(prodid, pincode);
		JSONObject obj = new JSONObject();
		response.setContentType("application/json");
		obj.put("status", isServicable);
		response.getWriter().println(obj);
	}

}
