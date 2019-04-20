package com.susman.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.susman.dao.UserDao;
import com.susman.dto.Address;
import com.susman.dto.Cart;
import com.susman.dto.User;

@Repository("userDao")
@Transactional
public class UserDaoImpl implements UserDao {

	@Autowired
	private SessionFactory factory;

	@Override
	public boolean addUser(User user) {
		try {
			factory.getCurrentSession().save(user);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean addAddress(Address address) {
		try {
			factory.getCurrentSession().save(address);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean updateCart(Cart cart) {
		try {
			factory.getCurrentSession().update(cart);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public User getByEmail(String email) {
		String selectQuery = "FROM User WHERE email=:NewEmail";
		try {
			return factory.getCurrentSession()
					.createQuery(selectQuery, User.class)
					.setParameter("NewEmail", email)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public Address getBillingAddress(User user) {
		String selectQuery = "FROM Address WHERE user=:NewUser AND billing=:NewBilling";
		try {
			return factory.getCurrentSession()
					.createQuery(selectQuery, Address.class)
					.setParameter("NewUser", user)
					.setParameter("NewBilling", 1)
					.getSingleResult();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	@Override
	public List<Address> listShippingAddress(User user) {
		String selectQuery = "FROM Address WHERE user=:NewUser AND shipping=:NewSipping";
		try {
			return factory.getCurrentSession()
					.createQuery(selectQuery, Address.class)
					.setParameter("NewUser", user)
					.setParameter("NewSipping", 1)
					.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
			return null;
	}
		}
}
