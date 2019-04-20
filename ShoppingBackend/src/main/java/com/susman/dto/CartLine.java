package com.susman.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "cart_line")
public class CartLine implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "gen1", strategy = "increment")
	@GeneratedValue(generator = "gen1")
	private int id;
	@OneToOne
	private Product product;
	@Column(name = "cart_id")
	private int cartId;
	@Column(name = "product_count")
	private int productCount;
	@Column(name = "total")
	private double total;
	@Column(name = "buying_price")
	private double buyingPrice;
	@Column(name = "is_available")
	private int available=1;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Product getProduct() {
		return product;
	}
	public void setProduct(Product product) {
		this.product = product;
	}
	public int getCartId() {
		return cartId;
	}
	public void setCartId(int cartId) {
		this.cartId = cartId;
	}
	public int getProductCount() {
		return productCount;
	}
	public void setProductCount(int productCount) {
		this.productCount = productCount;
	}
	public double getTotal() {
		return total;
	}
	public void setTotal(double total) {
		this.total = total;
	}
	public double getBuyingPrice() {
		return buyingPrice;
	}
	public void setBuyingPrice(double buyingPrice) {
		this.buyingPrice = buyingPrice;
	}
	public int getAvailable() {
		return available;
	}
	public void setAvailable(int available) {
		this.available = available;
	}
	@Override
	public String toString() {
		return "CartLine [id=" + id + ", product=" + product + ", cartId=" + cartId + ", productCount=" + productCount
				+ ", total=" + total + ", buyingPrice=" + buyingPrice + ", available=" + available + "]";
	}

}
