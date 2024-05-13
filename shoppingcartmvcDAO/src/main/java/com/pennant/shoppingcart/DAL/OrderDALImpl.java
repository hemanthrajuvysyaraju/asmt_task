package com.pennant.shoppingcart.DAL;

import java.sql.Array;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;

import com.pennant.shoppingcart.models.CouponModel;
import com.pennant.shoppingcart.models.OrderModel;

import JDBCUTILITIES.JdbcUtil;

public class OrderDALImpl implements IOrderDAL {
	@Override
	public Double getGst(Integer id) {
		Double gst = null;
		Connection con = JdbcUtil.getConnection();
		try {
			PreparedStatement psmt = con.prepareStatement(
					"select hsnc_gstc_percentage from i213_HSNCodes where hsnc_hsncode in(select hsnc_hsncode from i213_HSNCodes where hsnc_id in (select prod_hsnc_id from i213_productsdata where prod_id=?))");
			psmt.setInt(1, id);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				gst = rs.getDouble("hsnc_gstc_percentage");
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
		}
		return gst;
	}

	@Override
	public Double getShippingCharge(Double total) {
		Double charge = null;
		Connection con = JdbcUtil.getConnection();
		try {
			PreparedStatement psmt = con.prepareStatement(
					"SELECT orvl_shippingamount FROM i213_OrderValueWiseShippingCharges WHERE ? BETWEEN orvl_from AND orvl_to");
			psmt.setDouble(1, total);
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				charge = rs.getDouble("orvl_shippingamount");
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
		}
		return charge;
	}

	@Override
	public CouponModel checkAndGetCoupon(CouponModel coupon) {

		Connection con = JdbcUtil.getConnection();
		try {
			PreparedStatement psmt = con.prepareStatement(
					"select dcpn_id,dcpn_value,dcpn_minvalue from i213_discountcoupon where dcpn_code=? and dcpn_expiredate>current_date and dcpn_count>=1");
			psmt.setString(1, coupon.getCode());
			ResultSet rs = psmt.executeQuery();
			if (rs.next()) {
				coupon.setValid(true);
				coupon.setValue(rs.getDouble("dcpn_value"));
				coupon.setMin_Value(rs.getDouble("dcpn_minvalue"));
				coupon.setCpn_Id(rs.getInt("dcpn_id"));
			}
			JdbcUtil.closeConnections(con, psmt, rs);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return coupon;
	}

	@Override
	public OrderModel doOrder(OrderModel order) {
		Connection con = JdbcUtil.getConnection();
		Integer productids[] = order.getProducts_Id().toArray(new Integer[0]);
		try {
			Array prod_Id_Array = con.createArrayOf("int", productids);
			CallableStatement prepcall = con.prepareCall("{call i213_placeorder(?,?,?,?)}");
			prepcall.setDouble(1, order.getTotalInclAllTax());
			prepcall.setArray(2, prod_Id_Array);
			prepcall.setString(3, order.getCouponCode());
			prepcall.registerOutParameter(4,Types.INTEGER);
			prepcall.execute();
			order.setOrder_Id(prepcall.getInt(4));
			JdbcUtil.closeConnections(con,prepcall,null);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return order;
	}

}