package com.projnetwork.services.validation;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.projnetwork.entities.Client;
import com.projnetwork.entities.DTO.ClientDTO;
import com.projnetwork.entities.DTO.ClientNewDTO;
import com.projnetwork.entities.enums.TypeClient;
import com.projnetwork.repositories.ClientRepository;
import com.projnetwork.resources.exceptions.FieldMessage;
import com.projnetwork.services.validation.utils.BR;


public class ClientUpdateValidator implements ConstraintValidator<ClientUpdate,ClientDTO> {
	
 @Autowired
 private ClientRepository repo;
 
 @Autowired
private HttpServletRequest httpRequest;
	

	
 @Override
 public void initialize(ClientUpdate ann) {
 }
 @SuppressWarnings("unused")
@Override
 public boolean isValid(ClientDTO objDto, ConstraintValidatorContext context) {
 List<FieldMessage> list = new ArrayList<>();
 
 
@SuppressWarnings("unchecked")
Map<String,String> map= (Map<String,String>)httpRequest.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
Integer uriId=Integer.valueOf( map.get("Id"));

 // inclua os testes aqui, inserindo erros na lista
 Client aux=repo.findByEmail(objDto.getEmail());
 if(aux!=null&&!uriId.equals(aux.getId())) {
	 list.add(new FieldMessage( "email","Email ja existente"));
 }

 for (FieldMessage e : list) {
 context.disableDefaultConstraintViolation();
 context.buildConstraintViolationWithTemplate(e.getMessage())
 .addPropertyNode(e.getFieldName()).addConstraintViolation();
 }
 return list.isEmpty();
 }
}