package com.susman.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.susman.dao.ProductDao;
import com.susman.dto.Product;

@Controller("jsonDataController")
@RequestMapping("/json/data")
public class JsonDataController {
	@Autowired
	private ProductDao dao;

	@RequestMapping(value = "/all/products", method = RequestMethod.GET)
	@ResponseBody
	List<Product> getAllProducts() {
		return dao.listActiveProduct();
	}
	@RequestMapping(value = "/category/{id}/products", method = RequestMethod.GET)
	@ResponseBody
	List<Product> getProductsByCategory(@PathVariable("id") int id) {
		return dao.listActiveProductByCategoryId(id);
	}
	@RequestMapping(value = "/admin/all/products", method = RequestMethod.GET)
	@ResponseBody
	List<Product> getAllProductsForAdmin() {
		return dao.list();
	}
	
}
