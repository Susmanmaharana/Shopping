package com.susman.daoimpl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.susman.dao.CartLineDao;
import com.susman.dto.Cart;
import com.susman.dto.CartLine;

@Repository("cartLineDao")
@Transactional
public class CartLineDaoImpl implements CartLineDao {
	@Autowired
	private SessionFactory factory;

	@Override
	public CartLine get(int id) {
		return factory.getCurrentSession().get(CartLine.class, Integer.valueOf(id));

	}

	@Override
	public boolean add(CartLine cartLine) {
		try {
			factory.getCurrentSession().save(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean update(CartLine cartLine) {
		try {
			factory.getCurrentSession().update(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public boolean delete(CartLine cartLine) {
		cartLine.setAvailable(0);
		try {
			factory.getCurrentSession().delete(cartLine);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

	@Override
	public List<CartLine> list(int cartId) {
		String query = "FROM CartLine WHERE cartId=:newCartId";
		return factory.getCurrentSession().createQuery(query, CartLine.class).setParameter("newCartId", cartId)
				.getResultList();
	}

	@Override
	public List<CartLine> listAvailable(int cartId) {
		String query = "FROM CartLine WHERE cartId=:newCartId AND available=:newAvailable";
		return factory.getCurrentSession().createQuery(query, CartLine.class).setParameter("newCartId", cartId)
				.setParameter("newAvailable", 1).getResultList();
	}

	@Override
	public CartLine getByCartAndProduct(int cartId, int productId) {
		String query = "FROM CartLine WHERE cartId=:newCartId AND product.id=:productId";
		try {
			return factory.getCurrentSession().createQuery(query, CartLine.class).setParameter("newCartId", cartId)
					.setParameter("productId", productId).getSingleResult();
		} catch (Exception e) {
			return null;
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

}
