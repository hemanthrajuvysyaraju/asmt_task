package com.pennant.shoppingcart.DAL;

import com.pennant.shoppingcart.models.ProductListModel;

public interface IProductsDAL {

	public ProductListModel getProducts();

	public ProductListModel getProductById(Integer id);

	public Boolean searchService(Integer id, Integer pincode);
}
