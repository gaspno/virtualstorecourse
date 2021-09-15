package com.projnetwork.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Category;
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

}
