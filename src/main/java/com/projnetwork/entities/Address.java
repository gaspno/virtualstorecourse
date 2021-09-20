package com.projnetwork.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "endereco")
public class Address implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(name = "numero")
	private String number;
	@Column(name = "complemento")
	private String complementinfo;
	@Column(name = "bairro")
	private String district;
	@Column(name = "codigo_postal")
	private String zip_code;
	@Column(name = "logradouro")
	private String public_place;
	@ManyToOne
	@JoinColumn(name = "client_id")
	private Client client;
	@ManyToOne
	@JoinColumn(name = "city_id")
	private City city;
	@OneToMany(mappedBy = "address")
	private List<Order>  orders=new ArrayList<>();
	

	public Address() {
		
	}
	
	public Address(Integer id, String number, String complementinfo, String district, String zip_code,
			String public_place, Client client, City city) {
		super();
		this.id = id;
		this.number = number;
		this.complementinfo = complementinfo;
		this.district = district;
		this.zip_code = zip_code;
		this.public_place = public_place;
		this.client = client;
		this.city = city;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getNumber() {
		return number;
	}
	public void setNumber(String number) {
		this.number = number;
	}
	public String getComplementinfo() {
		return complementinfo;
	}
	public void setComplementinfo(String complementinfo) {
		this.complementinfo = complementinfo;
	}
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public String getZip_code() {
		return zip_code;
	}
	public void setZip_code(String zip_code) {
		this.zip_code = zip_code;
	}
	public String getPublic_place() {
		return public_place;
	}
	public void setPublic_place(String public_place) {
		this.public_place = public_place;
	}
	@JsonIgnore
	public Client getClient() {
		return client;
	}
	public void setClient(Client client) {
		this.client = client;
	}
	public City getCity() {
		return city;
	}
	
	public void setCity(City city) {
		this.city = city;
	}
	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
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
		Address other = (Address) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	

	
	

}
