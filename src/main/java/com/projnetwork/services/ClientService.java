package com.projnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Client;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repositoryClient;
	
	//função para exibir todas as categorias
	public List<Client> findAll(){
		return repositoryClient.findAll();
	}
	public Client findById(Integer id){
		Optional<Client> Client= repositoryClient.findById(id);
		return Client.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}

}
