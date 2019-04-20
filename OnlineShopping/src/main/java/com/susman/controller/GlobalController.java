package com.susman.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.susman.dao.UserDao;
import com.susman.dto.User;
import com.susman.model.UserModel;

@ControllerAdvice
public class GlobalController {
	@Autowired
	private UserDao dao;

	@Autowired
	private HttpSession session;

	private UserModel userModel=null;

	@ModelAttribute("userModel")
	public UserModel getUserModel() {
		if (session.getAttribute("userModel") == null) {
			// add the user model
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			User user = dao.getByEmail(authentication.getName());
			if (user != null) {
				// create a new UserModel object to pass the user details
				userModel=new UserModel();
				userModel.setId(user.getId());
				userModel.setEmail(user.getEmail());
				userModel.setRole(user.getRole());
				userModel.setFullName(user.getFirstName() + "  " + user.getLastName());
				if (userModel.getRole().equals("USER")) {
					// set the cart only for the buyer
					userModel.setCart(user.getCart());
				}
				// set the user model in the session
				session.setAttribute("userModel", userModel);
				return userModel;
			}
		}
		return (UserModel) session.getAttribute("userModel");
	}
}
