package com.susman.dao;

import java.util.List;

import com.susman.dto.Product;

public interface ProductDao {
	Product get(int productId);
	List<Product> list();
	int add(Product product);
	int update (Product product);
	int delete(Product product);
	//bussinee method
	List<Product> listActiveProduct();
	List<Product> listActiveProductByCategoryId(int categoryId);
	List<Product> getLatestActiveProducts(int count);
}
