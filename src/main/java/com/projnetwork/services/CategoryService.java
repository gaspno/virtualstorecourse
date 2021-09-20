package com.projnetwork.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Category;
import com.projnetwork.entities.DTO.CategoryDTO;
import com.projnetwork.exceptions.DataIntegrityException;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.CategoryRepository;
//informa ao spring que a categoria e um serviço
@Service
public class CategoryService {
	
	@Autowired
	private CategoryRepository repositoryCategory;
	
	//função para exibir todas as categorias
	public List<CategoryDTO> findAll(){
		return repositoryCategory.findAll().stream().map(x->new CategoryDTO(x)).collect(Collectors.toList());
	}
	public Category findById(Integer id){
		Optional<Category> category= repositoryCategory.findById(id);
		return category.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}
	public Category insert(Category obj) {
		
		obj.setId(null);	
		return repositoryCategory.save(obj);
	}
	public Category update(Category obj) {
		
		findById(obj.getId());
		return repositoryCategory.save(obj);
	}
	public void deleteById(Integer id) {
		findById(id);
		try {
		
		repositoryCategory.deleteById(id);		
	}catch(DataIntegrityViolationException e) 
	{
		throw new DataIntegrityException("Categoria não pode ser excluida pois possui produtos vincualdos a ela");
	}
		
	}

}
