package com.susman.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "category")
public class Category implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	  @GenericGenerator(name="gen1",strategy="increment")
	  @GeneratedValue(generator="gen1")
	private int id;
	@Column(name="name")
	private String name;
	@Column(name="description")
	private String description;
	@Column(name="image_url")
	private String imageUrl;
	@Column(name="isActive")
	private int active = 1;

	@Override
	public String toString() {
		return "Category [id=" + id + ", name=" + name + ", description=" + description + ", imageUrl=" + imageUrl
				+ ", active=" + active + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUrl() {
		return imageUrl;
	}

	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}

	public int isActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

}
