package com.projnetwork.entities.DTO;

import java.io.Serializable;

import com.projnetwork.entities.Product;




public class ProductDTO implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	private String name;	
	private Double price;
	
	public ProductDTO() {
		
	}
	public ProductDTO(Product prooduct) {
		this.Id=prooduct.getId();
		this.name=prooduct.getName();
		this.price=prooduct.getPrice();
	}
	
	
	public Integer getId() {
		return Id;
	}
	public void setId(Integer id) {
		Id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	

}
