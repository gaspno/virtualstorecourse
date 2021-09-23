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

import com.projnetwork.entities.Client;
import com.projnetwork.entities.DTO.ClientDTO;
import com.projnetwork.entities.DTO.ClientNewDTO;
import com.projnetwork.services.ClientService;

@RestController
@RequestMapping(value = "/clientes")
public class ClientResource {
	
	
	@Autowired
	private ClientService service;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<ClientDTO>> findAll() {
		List <ClientDTO> clients=service.findAll();
		return ResponseEntity.ok().body(clients);
	}
	
	@RequestMapping(value ="/{Id}", method = RequestMethod.GET)
	public ResponseEntity<Client> findById(@PathVariable Integer Id) {
		
		Client Client=service.findById(Id);		
		return ResponseEntity.ok().body(Client);
			
	}
	@RequestMapping( method = RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody ClientNewDTO obj) {
		
		Client cli=service.insert(service.ClientFromDTO(obj));
		URI uri=ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cli.getId()).toUri();	
		return ResponseEntity.created(uri).build();
	
	}
	@RequestMapping(value ="/{Id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> update(@PathVariable Integer Id,@Valid @RequestBody ClientDTO obj) {
		
		obj.setId(Id);
		service.update(service.ClientFromDTO(obj));	
		return ResponseEntity.noContent().build();
	}
	@RequestMapping(value ="/{Id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deleteById(@PathVariable Integer Id) {
		
		service.deleteById(Id);
		
		return ResponseEntity.noContent().build();
			
	}
	@RequestMapping(value = "/page" ,method = RequestMethod.GET)
	public ResponseEntity<Page<ClientDTO>> findPage(
			@RequestParam(value = "page",defaultValue = "0") Integer page,
			@RequestParam(value = "lines",defaultValue = "24") Integer lines,
			@RequestParam(value = "order",defaultValue = "name") String orderBy,
			@RequestParam(value = "classification",defaultValue = "ASC") String orderClassification) {
		Page <ClientDTO> categories=service.findforPage(page,lines,orderBy,orderClassification);
		return ResponseEntity.ok().body(categories);
	}

}
