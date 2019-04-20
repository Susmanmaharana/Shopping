package com.susman.dao;

import java.util.List;

import com.susman.dto.Category;

public interface CategoryDao {
	public List<Category> list();

	public Category get(int id);

	public int add(Category category);

	public int update(Category category);

	public int delete(Category category);
}
