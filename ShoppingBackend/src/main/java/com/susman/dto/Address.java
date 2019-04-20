package com.susman.dto;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.validator.constraints.NotBlank;
@Entity
@Table(name="address_details")
public class Address implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name = "id")
	@GenericGenerator(name = "gen1", strategy = "increment")
	@GeneratedValue(generator = "gen1")
	private int id;
	@ManyToOne
	private User user;
	@Column(name = "address_line_one")
	@NotBlank(message="Address1 can not be blank")
	private String addressLineOne;
	@Column(name = "address_line_two")
	@NotBlank(message="Address2 can not be blank")
	private String addressLineTwo;
	@Column(name = "city")
	@NotBlank(message="City can not be blank")
	private String city;
	@Column(name = "state")
	@NotBlank(message="State can not be blank")
	private String state;
	@Column(name = "country")
	@NotBlank(message="Country can not be blank")
	private String country;
	@Column(name = "postal_code")
	@NotBlank(message="Pin code can not be blank")
	private String postalCode;
	@Column(name = "shipping")
	private int shipping;
	@Column(name = "billing")
	private int billing;
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	public String getAddressLineOne() {
		return addressLineOne;
	}
	public void setAddressLineOne(String addressLineOne) {
		this.addressLineOne = addressLineOne;
	}
	public String getAddressLineTwo() {
		return addressLineTwo;
	}
	public void setAddressLineTwo(String addressLineTwo) {
		this.addressLineTwo = addressLineTwo;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public int getShipping() {
		return shipping;
	}
	public void setShipping(int shipping) {
		this.shipping = shipping;
	}
	public int getBilling() {
		return billing;
	}
	public void setBilling(int billing) {
		this.billing = billing;
	}
	@Override
	public String toString() {
		return "Address [id=" + id + ", user=" + user + ", addressLineOne=" + addressLineOne + ", addressLineTwo="
				+ addressLineTwo + ", city=" + city + ", state=" + state + ", country=" + country + ", postalCode="
				+ postalCode + ", shipping=" + shipping + ", billing=" + billing + "]";
	}
	
	
	
}
