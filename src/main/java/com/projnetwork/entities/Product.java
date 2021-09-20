package com.projnetwork.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;


import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "produto")
public class Product implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer Id;
	@Column(name = "nome_produto")
	private String name;
	@Column(name = "preço")
	private Double price;
	@ManyToMany
	@JoinTable(name = "produto_categoria", joinColumns =
	@JoinColumn(name ="product_id" ),
	inverseJoinColumns = @JoinColumn(name = "categoria_id") )
	private List<Category> categories=new ArrayList<>();
	@OneToMany(mappedBy = "id.product")
	private Set<ItemOrder> itens=new HashSet<>(); 
	

	public Product() {
		
	}

	public Product(Integer id, String name, Double price) {
		super();
		Id = id;
		this.name = name;
		this.price = price;	
	}
	@JsonIgnore	
	public List<Order> getOrders() {
		List<Order> orders=new ArrayList<>();
		for(ItemOrder x:itens) {
			orders.add(x.getOrder());
		}
		return orders;
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
	@JsonIgnore
	public List<Category> getCategories() {
		return categories;
	}
	@JsonIgnore
	public Set<ItemOrder> getItens() {
		return itens;
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((Id == null) ? 0 : Id.hashCode());
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
		Product other = (Product) obj;
		if (Id == null) {
			if (other.Id != null)
				return false;
		} else if (!Id.equals(other.Id))
			return false;
		return true;
	}

	
}
