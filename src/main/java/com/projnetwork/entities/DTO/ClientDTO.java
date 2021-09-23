package com.projnetwork.entities.DTO;

import java.io.Serializable;


import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.projnetwork.entities.Client;
import com.projnetwork.services.validation.ClientUpdate;
@ClientUpdate
public class ClientDTO implements Serializable{
	
	
	/**
	 * 
	 */
	
	private static final long serialVersionUID = 1L;
	
	private Integer Id;
	@NotEmpty(message = "Preenchumento obrigatório")
	@Length(min = 5,max = 120,message ="Nome invalido favor preencher com nome valido" )
	private String name;
	@NotEmpty(message = "Preenchumento obrigatório")
	@Email(message = "email invalido")
	private String email;
	
	public ClientDTO() {
		
	}
	public ClientDTO(Client client) {
		Id=client.getId();
		name=client.getName();
		email=client.getEmail();
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	
	

}
