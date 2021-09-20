package com.projnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Category;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.CategoryRepository;
//informa ao spring que a categoria e um serviço
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	//função para exibir todas as categorias
	public List<Category> findAll(){
		return repositoryCategory.findAll();
	}
	public Category findById(Integer id){
		Optional<Category> category= repositoryCategory.findById(id);
		return category.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}
	public Category insert(Category obj) {
		
		obj.setId(null);	
		return repositoryCategory.save(obj);
	}

}
