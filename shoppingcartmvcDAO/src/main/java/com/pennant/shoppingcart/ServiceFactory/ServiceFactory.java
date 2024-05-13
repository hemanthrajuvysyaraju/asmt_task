package com.pennant.shoppingcart.ServiceFactory;

import com.pennant.shoppingcart.DAL.CategoryDalImpl;
import com.pennant.shoppingcart.DAL.ICategoryDAL;
import com.pennant.shoppingcart.DAL.IOrderDAL;
import com.pennant.shoppingcart.DAL.IProductsDAL;
import com.pennant.shoppingcart.DAL.OrderDALImpl;
import com.pennant.shoppingcart.DAL.ProductsDALImpl;

public class ServiceFactory {
	private static ICategoryDAL catDal = null;
	private static IOrderDAL ordDal = null;
	private static IProductsDAL prodDal = null;

	public static ICategoryDAL getCategoryImpl() {
		if (catDal == null) {
			catDal = new CategoryDalImpl();
		}
		return catDal;
	}

	public static IOrderDAL getOrderImpl() {
		if (ordDal == null) {
			ordDal = new OrderDALImpl();
		}
		return ordDal;
	}

	public static IProductsDAL getProductsImpl() {
		if (prodDal == null) {
			prodDal = new ProductsDALImpl();
		}
		return prodDal;
	}
}
