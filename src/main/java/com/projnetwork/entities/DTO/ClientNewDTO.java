package com.projnetwork.entities.DTO;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.projnetwork.services.validation.ClientInsert;


@ClientInsert
public class ClientNewDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message = "Preenchumento obrigatório")
	@Email(message = "email invalido")
	private String email;
	@NotEmpty(message = "Preenchumento obrigatório")
	@Length(min = 5,max = 120,message ="Nome invalido favor preencher com nome valido" )
	private String name;
	@NotEmpty(message = "Preenchumento obrigatório")
	private String identification_id;
	private Integer type;
	
	@NotEmpty(message = "Preenchumento obrigatório")
	private String number;	
	private String complementinfo;	
	@NotEmpty(message = "Preenchumento obrigatório")
	private String district;
	@NotEmpty(message = "Preenchumento obrigatório")
	private String zip_code;	
	private String public_place;
	@NotEmpty(message = "Preenchumento obrigatório")
	private List<String> phones=new ArrayList<>();
	
	private Integer city_id;
	
	public ClientNewDTO() {
		
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

	public void setName(String name) {
		this.name = name;
	}

	public String getIdentification_id() {
		return identification_id;
	}

	public void setIdentification_id(String identification_id) {
		this.identification_id = identification_id;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public List<String> getPhones() {
		return phones;
	}

	
	public void setPhones(List<String> phones) {
		this.phones = phones;
	}

	public Integer getCity_id() {
		return city_id;
	}

	public void setCity_id(Integer city_id) {
		this.city_id = city_id;
	}
	
	

}
