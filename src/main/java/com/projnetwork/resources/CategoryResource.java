package com.projnetwork.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.projnetwork.entities.Category;
import com.projnetwork.services.CategoryService;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Category>> findAll() {
		List <Category> categories=service.findAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable Integer Id) {
		
		Category category=service.findById(Id);		
		return ResponseEntity.ok().body(category);
			
	}
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@RequestBody Category obj) {
		
		obj=service.insert(obj);
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	
		return ResponseEntity.created(uri).build();
	
	}
	
	

}
