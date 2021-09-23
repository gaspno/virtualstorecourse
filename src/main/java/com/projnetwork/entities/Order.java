package com.projnetwork.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
@Table(name = "pedido")
public class Order implements Serializable{
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "data")
	private Date instant;	
	@OneToOne(cascade = CascadeType.ALL,mappedBy = "order")
	private Payment payment;
	@ManyToOne
	@JoinColumn(name = "order_address_id")
	private Address address;
	@ManyToOne
	@JoinColumn(name = "order_client_id")
	private Client client;
	@OneToMany(mappedBy = "id.order")
	private Set<ItemOrder> itens=new HashSet<>(); 

	public Order() {
		
	}

	public Order(Integer id, Date instant, Address address, Client client) {
		super();
		this.id = id;
		this.instant = instant;		
		this.address = address;
		this.client = client;
	}
	public double getTotal() {
		double t=0;
		for(ItemOrder v:getItens()) {
			t+=v.getSubTotal();
		}
		return t;
	}
	
	@JsonIgnore
	public List<Product> getProducts() {
		List<Product> products=new ArrayList<>();
		for(ItemOrder x:itens) {
			products.add(x.getProduct());
		}
		return products;
	}


	
	public Integer getId() {
		return id;
	}
		

	public void setId(Integer id) {
		this.id = id;
	}
	@JsonFormat(pattern = "dd/MM/yyyy hh:mm")
	public Date getInstant() {
		return instant;
	}

	public void setInstant(Date instant) {
		this.instant = instant;
	}

	public Payment getPayment() {
		return payment;
	}
	
	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}
	
	public Set<ItemOrder> getItens() {
		return itens;
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
		Order other = (Order) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
	
	
	


}
