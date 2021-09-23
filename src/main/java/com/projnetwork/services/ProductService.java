package com.projnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Category;
import com.projnetwork.entities.Product;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.CategoryRepository;
import com.projnetwork.repositories.ProductRepository;

@Service
public class ProductService {
	
	@Autowired
	private ProductRepository repositoryProduct;	

	@Autowired
	private CategoryRepository repositoryCategory;
	//função para exibir todas as categorias
	public List<Product> findAll(){
		return repositoryProduct.findAll();
	}
	public Product findById(Integer id){
		Optional<Product> Product= repositoryProduct.findById(id);
		return Product.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}
	
	public Page<Product>search(String name,List<Integer>ids,Integer page,Integer lines,String orderBy,String orderClassification){
		
		PageRequest pageRequest=PageRequest.of(page, lines,Direction.valueOf(orderClassification),orderBy);
		List<Category>categories=repositoryCategory.findAllById(ids);
		return repositoryProduct.search(name,categories,pageRequest);		
	}

}
