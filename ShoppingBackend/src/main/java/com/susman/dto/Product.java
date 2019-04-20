package com.susman.dto;

import java.io.Serializable;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.Min;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.web.multipart.MultipartFile;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "product")
public class Product implements Serializable {
	/**
	 * 
	 */

	public Product() {
		this.code = "PRD" + UUID.randomUUID().toString().substring(26).toUpperCase();
	}

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "gen1", strategy = "increment")
	@GeneratedValue(generator = "gen1")
	private int id;
	@Column(name = "code")
	private String code;
	@Column(name = "name")
	@NotBlank(message="Please enter the Product Name !")
	private String name;
	@Column(name = "brand")
	@NotBlank(message="Please enter the Product Brand !")
	private String brand;
	@Column(name = "description")
	@JsonIgnore
	@NotBlank(message="Please enter the Description !")
	private String description;
	@Column(name = "unit_price")
	@Min(value=1,message="the price can not be less than 1")
	private double unitPrice;
	@Column(name = "quantity")
	private int quantity;
	@Column(name = "is_active")
	private int active;
	@Column(name = "category_id")
	@JsonIgnore
	private int categoryId;
	@Column(name = "supplier_id")
	@JsonIgnore
	private int supplierId;
	@Column(name = "purchases")
	private int purchases;
	@Column(name = "views")
	private int views;
	@Transient
	private MultipartFile file;
	



	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public int getActive() {
		return active;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getUnitPrice() {
		return unitPrice;
	}

	public void setUnitPrice(double unitPrice) {
		this.unitPrice = unitPrice;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int isActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public int getCategoryId() {
		return categoryId;
	}

	public void setCategoryId(int categoryId) {
		this.categoryId = categoryId;
	}

	public int getSupplierId() {
		return supplierId;
	}

	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}

	public int getPurchases() {
		return purchases;
	}

	public void setPurchases(int purchases) {
		this.purchases = purchases;
	}

	public int getViews() {
		return views;
	}

	public void setViews(int views) {
		this.views = views;
	}

	@Override
	public String toString() {
		return "Product [id=" + id + ", code=" + code + ", name=" + name + ", brand=" + brand + ", description="
				+ description + ", unitPrice=" + unitPrice + ", quantity=" + quantity + ", active=" + active
				+ ", categoryId=" + categoryId + ", supplierId=" + supplierId + ", purchases=" + purchases + ", views="
				+ views + ", file=" + file + "]";
	}

	

}
