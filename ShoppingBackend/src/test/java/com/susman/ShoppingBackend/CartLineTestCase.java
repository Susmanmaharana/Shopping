package com.susman.ShoppingBackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.susman.dao.CartLineDao;
import com.susman.dao.ProductDao;
import com.susman.dao.UserDao;
import com.susman.dto.Address;
import com.susman.dto.Cart;
import com.susman.dto.CartLine;
import com.susman.dto.Product;
import com.susman.dto.User;

public class CartLineTestCase {
	private static AnnotationConfigApplicationContext context;
	private static ProductDao pdao;
	private static UserDao udao;
	private static CartLineDao cldao;
	
	private User user = null;
	private Address address = null;
	private Cart cart = null;
	private Product product=null;
	private CartLine cartLine=null;
	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.susman");
		context.refresh();
		pdao = context.getBean("productDao", ProductDao.class);
		udao=context.getBean("userDao",UserDao.class);
		cldao=context.getBean("cartLineDao",CartLineDao.class);
	}

	@Test
	public void testAddNewCartLine(){
		//get the user
		user=udao.getByEmail("rinku268@gmail.com");
		//fetch the cart
		cart=user.getCart();
		//get the product
		product=pdao.get(3);
		//create a new cartLIne
		cartLine=new CartLine();
		cartLine.setBuyingPrice(product.getUnitPrice());
		cartLine.setProductCount(cartLine.getProductCount() + 1);
		cartLine.setTotal(cartLine.getProductCount()* product.getUnitPrice());
		cartLine.setAvailable(1);
		cartLine.setCartId(cart.getId());
		cartLine.setProduct(product);
		assertEquals("sucssfully add to db table", true, cldao.add(cartLine));
		//update the cart
		cart.setGrandTotal(cart.getGrandTotal()+cartLine.getTotal());
		cart.setCartLines(cart.getCartLines()+1);
		assertEquals("sucssfully add to db table", true, cldao.updateCart(cart));
		
		
	}

}
