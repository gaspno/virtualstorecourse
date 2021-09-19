package com.projnetwork.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projnetwork.entities.Order;
import com.projnetwork.services.OrderService;

@RestController
@RequestMapping(value = "/orders")
public class OrderResource {
	
	@Autowired
	private OrderService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Order>> findAll() {
		List <Order> Orders=service.findAll();
		return ResponseEntity.ok().body(Orders);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Order> findById(@PathVariable Integer Id) {
		
		Order Order=service.findById(Id);		
		return ResponseEntity.ok().body(Order);
			
	}

}
