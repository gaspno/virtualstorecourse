package com.projnetwork.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.projnetwork.entities.Product;

import com.projnetwork.entities.DTO.ProductDTO;
import com.projnetwork.resources.utils.URL;
import com.projnetwork.services.ProductService;

@RestController
@RequestMapping(value = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Product>> findAll() {
		List <Product> Products=service.findAll();
		return ResponseEntity.ok().body(Products);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Product> findById(@PathVariable Integer Id) {
		
		Product Product=service.findById(Id);		
		return ResponseEntity.ok().body(Product);
			
	}
	@RequestMapping(value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<ProductDTO>> findPage(
			@RequestParam(value = "name",defaultValue = "") String name,
			@RequestParam(value = "categories",defaultValue = "") String categories,
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "lines",defaultValue = "5") Integer lines,
			@RequestParam(value = "order",defaultValue = "name") String orderBy,
			@RequestParam(value = "classification",defaultValue = "ASC") String orderClassification) {
		System.out.print(URL.decodeParam(name)+URL.decodeList( categories)+page+lines+orderBy+orderClassification);
		Page <Product> products=service.search( URL.decodeParam(name),URL.decodeList( categories),page,lines,orderBy,orderClassification);
		Page <ProductDTO> productsDTO=products.map(x->new ProductDTO(x));
		
		return ResponseEntity.ok().body(productsDTO);
	}

}
