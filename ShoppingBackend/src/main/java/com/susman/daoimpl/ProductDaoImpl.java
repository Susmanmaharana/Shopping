package com.susman.daoimpl;

import java.util.List;

import org.apache.taglibs.standard.lang.jstl.test.beans.Factory;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.susman.dao.ProductDao;
import com.susman.dto.Product;

@Repository("productDao")
@Transactional
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory factory;

	// single
	@Override
	public Product get(int productId) {
		try {
			return factory.getCurrentSession().get(Product.class, Integer.valueOf(productId));
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	@Override
	public List<Product> list() {
		return factory.getCurrentSession().createQuery("FROM Product", Product.class).getResultList();
	}

	@Override
	public int add(Product product) {
		try {
			factory.getCurrentSession().save(product);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Product product) {
		try {
			factory.getCurrentSession().update(product);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Product product) {
		try {
			product.setActive(0);
			return this.update(product);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}

	@Override
	public List<Product> listActiveProduct() {
		String selectActiveProduct = "FROM Product WHERE active=:active";
		return factory.getCurrentSession()
				.createQuery(selectActiveProduct, Product.class)
				.setParameter("active", 1)
				.getResultList();

	}

	@Override
	public List<Product> listActiveProductByCategoryId(int categoryId) {
		String selectListActiveProduct = "FROM Product WHERE active=:active AND categoryId=:categoryId";
		return factory.getCurrentSession()
				.createQuery(selectListActiveProduct, Product.class)
				.setParameter("active", 1)
				.setParameter("categoryId", categoryId)
				.getResultList();
	}

	@Override
	public List<Product> getLatestActiveProducts(int count) {
		return factory.getCurrentSession()
				.createQuery("FROM Product WHERE active=:active ORDER BY id", Product.class)
				.setParameter("active", 1)
				.setFirstResult(0)
				.setMaxResults(7)
				.getResultList();
	}

}
