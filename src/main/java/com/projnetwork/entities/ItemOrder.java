package com.projnetwork.entities;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ItemOrder implements Serializable{
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private ItemOrderPk id=new ItemOrderPk();
	private Integer quantity;
	private Double price;
	private Double discount;
	
	public ItemOrder() {
		
	}

	public ItemOrder(Order o,Product p, Integer quantity, Double price, Double discount) {
		super();
		id.setOrder(o);
		id.setProduct(p);
		this.quantity = quantity;
		this.price = price;
		this.discount = discount;
	}
	
	public double getSubTotal() {
		return quantity*price;
	}
	
	public void setOrder(Order order){
		id.setOrder(order);
	}
	
	public void  setProduct(Product product) {
		id.setProduct(product);
	}
	
	@JsonIgnore
	public Order getOrder(){
		return id.getOrder();
	}
	
	public Product getProduct() {
		return id.getProduct();
	}
	@JsonIgnore
	public ItemOrderPk getId() {
		return id;
	}

	public void setId(ItemOrderPk id) {
		this.id = id;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public Double getDiscount() {
		return discount;
	}

	public void setDiscount(Double discount) {
		this.discount = discount;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItemOrder other = (ItemOrder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	

}
