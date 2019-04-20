package com.susman.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.susman.dao.CategoryDao;
import com.susman.dao.ProductDao;
import com.susman.dto.Category;
import com.susman.dto.Product;
import com.susman.exception.ProductNotFoundException;

@Controller("pageController")
public class PageController {
private static final Logger logger=LoggerFactory.getLogger(PageController.class); 
	@Autowired
	private CategoryDao dao;

	@Autowired
	private ProductDao pdao;

	@RequestMapping(value = { "/", "/home", "/index" })
	public ModelAndView index() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "HOME");
		logger.info("Inside page controller index method - INFO");
		logger.debug("Inside page controller index method - DEBUG");
		mav.addObject("categories", dao.list());
		mav.addObject("userClickHome", true);
		return mav;
	}

	@RequestMapping(value = { "/about" })
	public ModelAndView about() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "About Us");
		mav.addObject("userClickAbout", true);
		return mav;
	}

	@RequestMapping(value = { "/contact" })
	public ModelAndView contact() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Contact Us");
		mav.addObject("userClickContact", true);
		return mav;
	}

	@RequestMapping(value = { "/listProducts" })
	public ModelAndView listProducts() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("title", "Products Page");
		mav.addObject("userClickProduct", true);
		return mav;
	}

	/*
	 * @RequestMapping(value={"/test"}) public ModelAndView
	 * test(@RequestParam(value="message",required=false) String message) {
	 * ModelAndView mav=new ModelAndView("page"); if(message==null){
	 * message="Welcome to Online Shopping"; } mav.addObject("message",message);
	 * return mav; }
	 */
	@RequestMapping(value = { "/test/{message}" })
	public ModelAndView test(@PathVariable("message") String message) {
		ModelAndView mav = new ModelAndView("page");
		if (message == null) {
			message = "Welcome to Online Shopping";
		}
		mav.addObject("message", message);
		return mav;
	}
	@RequestMapping(value = { "/show/all/products" })
	public ModelAndView showAllProducts() {
		ModelAndView mav = new ModelAndView("page");
		mav.addObject("categories", dao.list());
		mav.addObject("title", "All Products");
		mav.addObject("userClickAllProducts", true);
		return mav;
	}
	@RequestMapping(value = { "/show/category/{id}/products" })
	public ModelAndView index(@PathVariable("id") int id){
		ModelAndView mav = new ModelAndView("page");
		Category c=new Category();
		c=dao.get(id);
		
		
		mav.addObject("title", c.getName());
		mav.addObject("categories", dao.list());
		mav.addObject("category", c);
		mav.addObject("userClickCategoryProducts", true);
		return mav;
	}
	@RequestMapping(value = { "/show/{id}/product" })
	public ModelAndView singleView(@PathVariable("id") int id)throws ProductNotFoundException  {
		ModelAndView mav = new ModelAndView("page");
		Product product=pdao.get(id);
		if(product==null)throw new ProductNotFoundException();
		product.setViews(product.getViews()+1);
		//update the view count
		pdao.update(product);
		mav.addObject("title", product.getName());
		mav.addObject("product", product);
		mav.addObject("userClickShowProduct", true);
		return mav;
	}
	

	@RequestMapping(value = {"/login"},method=RequestMethod.GET)
	public ModelAndView login(@RequestParam(name="error",required=false)String error,
			@RequestParam(name="logout",required=false)String logout) {
		ModelAndView mav = new ModelAndView("login");
		if(error!=null){
			mav.addObject("message","Invalid Email Id and Password");
		}
		if(logout!=null){
			mav.addObject("logout","User Successfully Logged Out");
		}
		mav.addObject("title", "Login");
		return mav;
	}
	//accessed denied page
	@RequestMapping(value = { "/access-denied" })
	public ModelAndView accessDenied() {
		ModelAndView mav = new ModelAndView("error");
		mav.addObject("title", "403- Access Denied");
		mav.addObject("errorTitle", "Aha ! Caught You");
		mav.addObject("errorDescription", "You are not view this page");
		return mav;
	}
	//logout
	@RequestMapping(value = { "/perform-logout"})
	public String  logout(HttpServletRequest request,HttpServletResponse response) {
		//first we are going to fetch the authentication
		Authentication auth=SecurityContextHolder.getContext().getAuthentication();  
		if(auth!=null){
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}
	
}
