package com.susman.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.susman.dao.CategoryDao;
import com.susman.dao.ProductDao;
import com.susman.dto.Category;
import com.susman.dto.Product;
import com.susman.util.FileUploadUtility;
import com.susman.validator.ProductValidator;

@Controller("managementProduct")
@RequestMapping("/manage")
public class ManagementProduct {
	@Autowired
	private CategoryDao dao;
	@Autowired
	private ProductDao pdao;
	
	private static final Logger logger=LoggerFactory.getLogger(ManagementProduct.class);
	
	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public ModelAndView showManageProducts(@RequestParam(name="operation",required=false)String operation) {
		ModelAndView mav = new ModelAndView("page");
		
		mav.addObject("userClickManageProducts", true);
		mav.addObject("title", "Manage Products");
		Product nProduct = new Product();
		nProduct.setSupplierId(1004);
		nProduct.setActive(1);
		mav.addObject("product", nProduct);
		if(operation!=null){
			if(operation.equals("product")){
				mav.addObject("message","Product Submit Successfully !");
			}else if(operation.equals("category")){
				mav.addObject("message","Category Submit Successfully !");
			}
		}
		return mav;
	}
	
	@RequestMapping(value="{id}/product",method=RequestMethod.GET)
	public ModelAndView showEditProduct(@PathVariable("id") int id) {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("userClickManageProducts", true);
		mav.addObject("title", "Manage Products");
		Product nProduct = pdao.get(id);
		mav.addObject("product", nProduct);
	   return mav;
	}
	
	

	@ModelAttribute("categories")
	public List<Category> getCategories() {
		return dao.list();
	}

	@RequestMapping(value = "/products", method = RequestMethod.POST)
	public String handleProductSubmmission(@Valid @ModelAttribute("product")Product mProduct,BindingResult results,Model model,
			HttpServletRequest request) {
		if(mProduct.getId()==0){
		new ProductValidator().validate(mProduct, results);
		}else{
			if(!mProduct.getFile().getOriginalFilename().equals("")){
				new ProductValidator().validate(mProduct, results);
			}
		}
		//check for error
		if(results.hasErrors()){
			model.addAttribute("userClickManageProducts", true);
			model.addAttribute("title", "Manage Products");
			model.addAttribute("message", "Validation failed for product Submission !");
			return  "page";
		}
		//create a new product record
		logger.info(mProduct.toString());
		if(mProduct.getId()==0){
		pdao.add(mProduct);
		}else{
			pdao.update(mProduct);
		}
		if(!mProduct.getFile().getOriginalFilename().equals("")){
			FileUploadUtility.uploadFile(request,mProduct.getFile(),mProduct.getCode());
		}
		return "redirect:/manage/products?operation=product";
	}
	@RequestMapping(value="/product/{id}/activation",method=RequestMethod.POST)
	public String handleProductActivation(@PathVariable("id") int id){
		Product product =pdao.get(id);
		int isActive =product.getActive();
		System.out.println(isActive);
		System.out.println(product);
		boolean flag=true;
		if(isActive==1){
			product.setActive(0);
			flag=false;
			pdao.update(product);
		}else{
			product.setActive(1);
			flag=true;
			pdao.update(product);
		}
		return (flag)?"You have sucssfully deactivate the product with id "+product.getId(): "You have sucssfully activate the product with id "+product.getId();
	}
	
	@ModelAttribute("category")
	public Category getCategory(){
		return new Category();
	}
	
	@RequestMapping(value="/category",method=RequestMethod.POST)
	public String handleCategorySubmission(@ModelAttribute Category category){
		dao.add(category);
		return"redirect:/manage/products/?operation=category";
	}
	
	
	
}