package com.projnetwork.services;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.Address;
import com.projnetwork.entities.City;
import com.projnetwork.entities.Client;
import com.projnetwork.entities.DTO.ClientDTO;
import com.projnetwork.entities.DTO.ClientNewDTO;
import com.projnetwork.entities.enums.TypeClient;
import com.projnetwork.exceptions.DataIntegrityException;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.AddressRepository;
import com.projnetwork.repositories.CityRepository;
import com.projnetwork.repositories.ClientRepository;

@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repositoryClient;
	@Autowired
	private CityRepository repositoryCity;
	@Autowired
	private AddressRepository repositoryAddress;
	
	
	//função para exibir todas as categorias
	public List<ClientDTO> findAll(){
		return repositoryClient.findAll().stream().map(x->new ClientDTO(x)).collect(Collectors.toList());
	}
	public Client findById(Integer id){
		Optional<Client> Client= repositoryClient.findById(id);
		return Client.orElseThrow(()->new ObjectNotFoundException("Cliente não encontrado"));
	}
	@Transactional
	public Client insert(Client obj) {
		
		obj.setId(null);	
		repositoryAddress.saveAll(obj.getAdresses());
		return repositoryClient.save(obj);
	}
	public Client update(Client obj) {
		
		Client newe=findById(obj.getId());
		newe.setName(obj.getName());
		newe.setEmail(obj.getEmail());
		return repositoryClient.save(newe);
	}
	public void deleteById(Integer id) {
		findById(id);
		try {
		
		repositoryClient.deleteById(id);		
	}catch(DataIntegrityViolationException e) 
	{
		throw new DataIntegrityException("Cliente possui pedidos associados ,não pdoe ser excluido");
	}
		
	}
	public Page<ClientDTO> findforPage(Integer page,Integer lines,String orderBy,String orderClassification){
		
		PageRequest pagerequest=PageRequest.of(page, lines,Direction.valueOf(orderClassification),orderBy);
		return repositoryClient.findAll(pagerequest).map(x->new ClientDTO(x));
	}
	
	public Client ClientFromDTO(ClientDTO dto) {
		return new  Client(dto.getId(),dto.getEmail(),dto.getName(),null,null);
	}
	public Client ClientFromDTO(ClientNewDTO dto) {
		 Client clNew= new  Client(null,dto.getEmail(),dto.getName(),dto.getIdentification_id(),TypeClient.getTypeClient(dto.getType()));
		 Optional<City> optCity=repositoryCity.findById(dto.getCity_id());
		 City newCity=optCity.get();
		 Address addres=new Address(null,dto.getNumber(), dto.getComplementinfo(), dto.getDistrict(), dto.getZip_code(),dto.getPublic_place(),clNew,newCity);		
		 clNew.getAdresses().add(addres);
		 clNew.getPhones_numbers().addAll(dto.getPhones());
		 
		 return clNew;
	}
	
	
	

}
