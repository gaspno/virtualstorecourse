package com.projnetwork.resources;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import com.projnetwork.entities.Category;
import com.projnetwork.entities.DTO.CategoryDTO;
import com.projnetwork.services.CategoryService;


@RestController
@RequestMapping(value = "/categories")
public class CategoryResource {
	
	@Autowired
	private CategoryService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CategoryDTO>> findAll() {
		List <CategoryDTO> categories=service.findAll();
		return ResponseEntity.ok().body(categories);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Category> findById(@PathVariable Integer Id) {
		
		Category category=service.findById(Id);		
		return ResponseEntity.ok().body(category);
			
	}
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody CategoryDTO obj) {
		
		service.insert(service.categoryFromDTO(obj));
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();	
		return ResponseEntity.created(uri).build();
	
	}
	@RequestMapping(value ="/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer Id,@Valid @RequestBody CategoryDTO obj) {
		
		obj.setId(Id);
		service.update(service.categoryFromDTO(obj));	
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value ="/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer Id) {
		
		service.deleteById(Id);
		
		return ResponseEntity.noContent().build();
			
	}
	@RequestMapping(value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<CategoryDTO>> findPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "lines",defaultValue = "24") Integer lines,
			@RequestParam(value = "order",defaultValue = "name") String orderBy,
			@RequestParam(value = "classification",defaultValue = "ASC") String orderClassification) {
		Page <CategoryDTO> categories=service.findforPage(page,lines,orderBy,orderClassification);
		return ResponseEntity.ok().body(categories);
	}
}
