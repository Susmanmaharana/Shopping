package com.susman.handler;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.binding.message.MessageBuilder;
import org.springframework.binding.message.MessageContext;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import com.susman.dao.UserDao;
import com.susman.dto.Address;
import com.susman.dto.Cart;
import com.susman.dto.User;
import com.susman.model.RegisterModel;

@Component("registerHandler")
public class RegisterHandler {
@Autowired
private UserDao dao;
@Autowired
private BCryptPasswordEncoder passwordEncoder;
	public RegisterModel init(){
		return new RegisterModel();
	}
	public void addUser(RegisterModel model,User user){
		model.setUser(user);
	}
	public void addBilling(RegisterModel model,Address address){
		model.setBilling(address);
	}
	public String saveAll(RegisterModel model){
		String transitionValue="success";
		//fetch the user
		User user=model.getUser();
		if(user.getRole().equals("USER")){
			Cart cart=new Cart();
			cart.setUser(user);
			user.setCart(cart);
		}
		//encode the pass
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		//save the user
		dao.addUser(user);
		
		//get the address
		Address billing =model.getBilling();
		billing.setUser(user);
		billing.setBilling(1);
		//save address
		dao.addAddress(billing);
		
		return transitionValue;
	}
	//checking password
	public String validateUser(User user,MessageContext error){
		String transitionValue="success";
		 if(!(user.getPassword().equals(user.getConfirmPassword()))){
			 error.addMessage(new MessageBuilder().error().source("confirmPassword").defaultText("Password Does not Match").build());
			 transitionValue="failure";
		 }
		//check email is available or not
		if(dao.getByEmail(user.getEmail())!=null){
			 error.addMessage(new MessageBuilder().error().source("email").defaultText("Email address is already used").build());
			 transitionValue="failure";
		}
		return transitionValue;
	}
	
	
}
