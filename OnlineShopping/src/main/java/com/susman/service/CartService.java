package com.susman.service;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.susman.dao.CartLineDao;
import com.susman.dao.ProductDao;
import com.susman.dto.Cart;
import com.susman.dto.CartLine;
import com.susman.dto.Product;
import com.susman.model.UserModel;

@Service("cartService")
public class CartService {
	@Autowired
	private CartLineDao cartLineDao;
	@Autowired
	private HttpSession session;
	@Autowired
	private ProductDao productDao;

	// return cart for the user
	private Cart getCart() {
		return ((UserModel) session.getAttribute("userModel")).getCart();
	}
	// return  all cart for the user
	public List<CartLine> getCartLines() {
		return cartLineDao.list(this.getCart().getId());
	}
	public String updateCartLine(int cartLineId, int count) {
		//fetch the cartLine
		CartLine cartLine=cartLineDao.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}else{
			Product product=cartLine.getProduct();
			
			double oldTotal = cartLine.getTotal();
			
			if(product.getQuantity() <= count){
				count=product.getQuantity();
			} 
			cartLine.setProductCount(count);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setTotal(product.getUnitPrice() * count);
			
			cartLineDao.update(cartLine);
			
			Cart cart=this.getCart();
			cart.setGrandTotal(cart.getGrandTotal() - oldTotal + cartLine.getTotal());
			cartLineDao.updateCart(cart);
			
			return "result=updated";
		}

	}
	public String deleteCartLine(int cartLineId) {
		//fetch the cartLine
		CartLine cartLine=cartLineDao.get(cartLineId);
		if(cartLine == null){
			return "result=error";
		}else{
			//update the cart
			Cart cart=this.getCart();
			
			cart.setGrandTotal(cart.getGrandTotal() -  cartLine.getTotal());
			cart.setCartLines(cart.getCartLines() - 1);
			cartLineDao.updateCart(cart);
			
			//remove the cart line
			cartLineDao.delete(cartLine);
			
			return "result=deleted";
		}
		
	
	}
	public String addCartLine(int productId) {
		String response=null;
		Cart cart=this.getCart();
		CartLine cartLine=cartLineDao.getByCartAndProduct(cart.getId(), productId);
		
		if(cartLine == null){
			//add the new cartLine
			cartLine= new CartLine();
			//fetch the product
			Product product=productDao.get(productId);
			cartLine.setCartId(cart.getId());
			
			cartLine.setProduct(product);
			cartLine.setBuyingPrice(product.getUnitPrice());
			cartLine.setProductCount(1);
			cartLine.setTotal(product.getUnitPrice());
			cartLine.setAvailable(1);
			
			cartLineDao.add(cartLine);
			
			cart.setCartLines(cart.getCartLines() + 1);
			cart.setGrandTotal(cart.getGrandTotal() + cartLine.getTotal());
			
			cartLineDao.update(cartLine);
			response="result=added";

		}
		
		
		return response;
	}

}
