package com.projnetwork.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projnetwork.entities.Order;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.OrderRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repositoryClient;
	
	//função para exibir todas as categorias
	public List<Order> findAll(){
		return repositoryClient.findAll();
	}
	public Order findById(Integer id){
		Optional<Order> Order= repositoryClient.findById(id);
		return Order.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}

}
