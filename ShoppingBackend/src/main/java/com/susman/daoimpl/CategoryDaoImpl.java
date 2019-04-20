package com.susman.daoimpl;


import java.util.List;

import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.susman.dao.CategoryDao;
import com.susman.dto.Category;

@Repository("categoryDao")
@Transactional
public class CategoryDaoImpl implements CategoryDao {

	@Autowired
	private SessionFactory factory;

	/*public static List<Category> listCategory = new ArrayList<Category>();
	static {
		Category cat1 = new Category();
		cat1.setId(1);
		cat1.setName("TV");
		cat1.setDescription("good product");
		cat1.setImageUrl("a.png");
		Category cat2 = new Category();
		cat2.setId(2);
		cat2.setName("MOBILE");
		cat2.setDescription("good product");
		cat2.setImageUrl("c.png");
		Category cat3 = new Category();
		cat3.setId(3);
		cat3.setName("LAPTOP");
		cat3.setDescription("good product");
		cat3.setImageUrl("d.png");
		listCategory.add(cat1);
		listCategory.add(cat2);
		listCategory.add(cat3);
	}*/

	@Override
	public List<Category> list() {
		String selectSctiveCategory="FROM Category WHERE active=:active";
		@SuppressWarnings("rawtypes")
		Query query=factory.getCurrentSession().createQuery(selectSctiveCategory);
		query.setParameter("active", 1);
		return query.getResultList();
	}

	@Override
	public Category get(int id) {
		return factory.getCurrentSession().get(Category.class,Integer.valueOf(id));
	}


	@Override
	public int add(Category category) {

		try {
			factory.getCurrentSession().save(category);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int update(Category category) {
		try {
			factory.getCurrentSession().update(category);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}

	@Override
	public int delete(Category category) {
category.setActive(0);
		try {
			factory.getCurrentSession().update(category);
			return 1;
		} catch (Exception e) {
			e.printStackTrace();
			return 0;
		}
	}
}