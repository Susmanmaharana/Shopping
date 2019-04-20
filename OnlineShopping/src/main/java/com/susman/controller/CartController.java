package com.susman.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.susman.service.CartService;

@Controller
@RequestMapping("/cart")
public class CartController {
	@Autowired
	private CartService service;
	@RequestMapping(value = "/show")
	public ModelAndView showCart(@RequestParam(name="result",required=false)String result) {
		ModelAndView mav = new ModelAndView("page");
		if(result!=null){
			switch (result) {
			case "updated":
				mav.addObject("message","CartLine has been Successfully updated");
				break;
			case "deleted":
				mav.addObject("message","CartLine has been Successfully deleted");
				break;
			case "added":
				mav.addObject("message","Product has been Successfully Added to Cart");
				break;
			case "error":
				mav.addObject("message","Something went Wrong");
				break;
			}
		}
		
		mav.addObject("title", "User Cart");
		mav.addObject("userClickShowCart", true);
		mav.addObject("cartLines", service.getCartLines());
		return mav;
	}
	@RequestMapping(value = "/{cartLineId}/update")
	public String updateCart(@PathVariable int cartLineId,@RequestParam int count) {
		String response=service.updateCartLine(cartLineId, count);
		return "redirect:/cart/show?"+response;
	}
	
	@RequestMapping(value = "/{cartLineId}/delete")
	public String deleteCartLine(@PathVariable int cartLineId) {
		String response=service.deleteCartLine(cartLineId);
		return "redirect:/cart/show?"+response;
	}
	@RequestMapping(value = "/add/{productId}/product")
	public String addCart(@PathVariable int productId) {
		String response=service.addCartLine(productId);
		return "redirect:/cart/show?"+response;
	}
	
	
	
}
