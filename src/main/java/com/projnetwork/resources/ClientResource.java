package com.projnetwork.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.projnetwork.entities.Client;
import com.projnetwork.services.ClientService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientResource {
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<Client>> findAll() {
		List <Client> clients=service.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer Id) {
		
		Client Client=service.findById(Id);		
		return ResponseEntity.ok().body(Client);
			
	}

}
