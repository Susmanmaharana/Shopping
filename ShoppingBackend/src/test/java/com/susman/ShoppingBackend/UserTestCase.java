package com.susman.ShoppingBackend;

import static org.junit.Assert.assertEquals;

import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.susman.dao.UserDao;
import com.susman.dto.Address;
import com.susman.dto.Cart;
import com.susman.dto.User;

public class UserTestCase {
	private static AnnotationConfigApplicationContext context;
	private static UserDao dao;
	private User user = null;
	private Address address = null;
	private Cart cart = null;

	@BeforeClass
	public static void init() {
		context = new AnnotationConfigApplicationContext();
		context.scan("com.susman");
		context.refresh();
		dao = context.getBean("userDao", UserDao.class);
	}

/*	@Test
	public void testAdd() {
		user = new User();
		user.setFirstName("susman");
		user.setLastName("maharana");
		user.setEmail("rinku268@gmail.com");
		user.setContactNumber("8908881760");
		user.setRole("USER");
		user.setPassword("susman");
		if (user.getRole().equals("USER")) {
			cart = new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		assertEquals("sucssfully add to db table", true, dao.addUser(user));
	}*/

	
	/*  @Test 
	  public void testUpdateCart() {
	 user=dao.getByEmail("rinku268@gmail.com"); 
	 cart=user.getCart();
	
	 cart.setCartLines(667); 
	 cart.setGrandTotal(3456);
	  assertEquals("sucessfully add to db table",true, dao.updateCart(cart)); }
	 
}*/
	/*@Test 
	  public void testAddAddress() {
		user = new User();
		user.setFirstName("susman");
		user.setLastName("maharana");
		user.setEmail("rinku268@gmail.com");
		user.setContactNumber("8908881760");
		user.setRole("USER");
		user.setPassword("susman");
		assertEquals("sucssfully fetch from db table",true, dao.addUser(user));
		address= new Address();
		address.setAddressLineOne("101/D Jadoo society");
		address.setAddressLineTwo("near pritam honda");
		address.setCity("keonjhr");
		address.setState("odisha");
		address.setCountry("India");
		address.setPostalCode("758002");
		address.setBilling(1);
		address.setUser(user);
		assertEquals("sucssfully add address to db table",true, dao.addAddress(address));
		
		
		address= new Address();
		address.setAddressLineOne("456/D OM bihar");
		address.setAddressLineTwo("near rajdoot");
		address.setCity("cuttack");
		address.setState("odisha");
		address.setCountry("India");
		address.setPostalCode("345438");
		address.setShipping(1);
		address.setUser(user);
		assertEquals("sucssfully add address to db table",true, dao.addAddress(address));
		
		

	}*/
	/*@Test 
	  public void testAddAddress() {
		user=dao.getByEmail("rinku268@gmail.com");
	
		address= new Address();
		address.setAddressLineOne("459/D Mg road");
		address.setAddressLineTwo("near rajdoot");
		address.setCity("Chennai");
		address.setState("TN");
		address.setCountry("India");
		address.setPostalCode("987655");
		address.setShipping(1);
		address.setUser(user);
		assertEquals("sucssfully add address to db table",true, dao.addAddress(address));

	}*/
	@Test
	public void testGetAddress(){
		user=dao.getByEmail("rinku268@gmail.com");
		assertEquals("sucssfully add address to db table",2, dao.listShippingAddress(user).size());
		assertEquals("sucssfully add address to db table","keonjhr", dao.getBillingAddress(user).getCity());
		
	}
	 
	}
	
