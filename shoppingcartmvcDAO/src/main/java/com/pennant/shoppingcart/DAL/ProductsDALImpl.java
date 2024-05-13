package com.pennant.shoppingcart.DAL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.pennant.shoppingcart.models.ProductListModel;
import com.pennant.shoppingcart.models.ProductModel;

import JDBCUTILITIES.JdbcUtil;

public class ProductsDALImpl implements IProductsDAL {
	private Connection con;
	private PreparedStatement psmt;
	private ResultSet rs;

	@Override
	public ProductListModel getProducts() {
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement(
					"select pd.*,ps.prod_price from i213_productsdata pd,i213_ProductStock ps where pd.prod_id=ps.prod_id");

		} catch (SQLException e) {
			System.out.println(e.getLocalizedMessage());
		}
		return doProcess();
	}

	@Override
	public ProductListModel getProductById(Integer id) {
		new ProductListModel();
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement(
					"select pd.*,ps.prod_price from i213_productsdata pd,i213_ProductStock ps where pd.prod_id=ps.prod_id and prod_prct_id=?");
			psmt.setInt(1, id);

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return doProcess();
	}

	private ProductListModel doProcess() {
		ProductListModel products = new ProductListModel();
		try {
			rs = psmt.executeQuery();
			while (rs.next()) {
				ProductModel product = new ProductModel();
				product.setProd_Cat_Id(rs.getInt("prod_prct_id"));
				product.setProd_Id(rs.getInt("prod_id"));
				product.setProd_Name(rs.getString("prod_title"));
				product.setProd_Price(rs.getDouble("prod_price"));
				product.setProd_Image(rs.getString("prod_image"));
				products.add(product);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return products;
	}

	@Override
	public Boolean searchService(Integer id, Integer pincode) {
		con = JdbcUtil.getConnection();
		try {
			psmt = con.prepareStatement(
					"select prod_id,prod_prct_id from i213_productsdata where prod_prct_id in(select prct_id  from i213_ProductCategoryWiseServiceableRegions where srrg_id in(select srrg_id from i213_ServiceableRegions where srrg_pinfrom<=? and srrg_pinto>=?))and prod_prct_id not in (select prct_id from i213_unservicablelocation where locn_id=?)");
			psmt.setInt(1, pincode);
			psmt.setInt(2, pincode);
			psmt.setInt(3, pincode);
			rs = psmt.executeQuery();
			while (rs.next()) {
				
				if (rs.getInt("prod_id") == id) {
					return true;
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
}
