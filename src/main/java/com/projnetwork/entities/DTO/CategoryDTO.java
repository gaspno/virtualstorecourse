package com.projnetwork.entities.DTO;

import java.io.Serializable;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import com.projnetwork.entities.Category;





public class CategoryDTO implements Serializable{
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer Id;
	@NotEmpty(message = "Preenchimento obrigatório")
	@Length(min = 5,max = 80,message = "O tamanho deve ser entre 5 e 80 caractres")
	private String name;
	
	public CategoryDTO() {
		
	}
	
	public CategoryDTO(Category category) {
		Id=category.getId();
		name=category.getName();
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
	
	

}
