 package com.susman.dao;

import java.util.List;

import com.susman.dto.Address;
import com.susman.dto.Cart;
import com.susman.dto.User;

public interface UserDao {
boolean addUser(User user);
boolean addAddress(Address address);
boolean updateCart(Cart cart);
User getByEmail(String email);
//alternative
//Address getBillingAddress(User user);
//List<Address> listShippingAddress(User user);
Address getBillingAddress(User user);
List<Address> listShippingAddress(User user);

}
