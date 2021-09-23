package com.projnetwork.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.projnetwork.entities.enums.TypeClient;

@Entity
@Table(name = "cliente")
public class Client implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String email;
	@Column(name = "nome")
	private String name;
	@Column(name = "numero_de_identificaçao")
	private String identification_id;
	@Column(name = "tipo")
	private Integer type;
	@OneToMany(mappedBy = "client",cascade = CascadeType.ALL)
	private List<Address> adresses=new ArrayList<>();
	@ElementCollection
	@CollectionTable(name = "telefone")
	@Column(name = "numero")
	private Set<String> phones_numbers=new HashSet<>();
	@OneToMany(mappedBy = "client")
	private List<Order> orders=new ArrayList<>(); 
	
	


	public Client() {
		
	}
	
	
	public Client(Integer id, String email, String name, String identification_id, TypeClient type) {
		super();
		this.id = id;
		this.email = email;
		this.name = name;
		this.identification_id = identification_id;
		this.type =(type==null) ?null : type.getCode();
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public String getName() {
		return name;
	}
	@JsonIgnore
	public List<Order> getOrders() {
		return orders;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getIdentification_id() {
		return identification_id;
	}


	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}


	public TypeClient getType() {
		return TypeClient.getTypeClient(type);
	}


	public void setType(TypeClient type) {
		this.type = type.getCode();
	}


	public List<Address> getAdresses() {
		return adresses;
	}


	public Set<String> getPhones_numbers() {
		return phones_numbers;
	}
	
	
	
}
