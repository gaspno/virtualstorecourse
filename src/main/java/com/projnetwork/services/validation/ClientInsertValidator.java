package com.projnetwork.services.validation;

import java.util.ArrayList;
import java.util.List;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.projnetwork.entities.Client;
import com.projnetwork.entities.DTO.ClientNewDTO;
import com.projnetwork.entities.enums.TypeClient;
import com.projnetwork.repositories.ClientRepository;
import com.projnetwork.resources.exceptions.FieldMessage;
import com.projnetwork.services.validation.utils.BR;


public class ClientInsertValidator implements ConstraintValidator<ClientInsert,ClientNewDTO> {
	
 @Autowired
 private ClientRepository repo;

	
 @Override
 public void initialize(ClientInsert ann) {
 }
 @Override
 public boolean isValid(ClientNewDTO objDto, ConstraintValidatorContext context) {
 List<FieldMessage> list = new ArrayList<>();
 
 

 // inclua os testes aqui, inserindo erros na lista
 if(objDto.getType().equals(TypeClient.PESSOAFISICA.getCode())&&!BR.isValidCPF(objDto.getIdentification_id())) {
	 list.add(new FieldMessage( "identification_id","CPF inválido"));
 }
 if(objDto.getType().equals(TypeClient.PESSOAJURIDICA.getCode())&&!BR.isValidCNPJ(objDto.getIdentification_id())) {
	 list.add(new FieldMessage( "identification_id","CNPJ inválido"));
 }
 Client aux=repo.findByEmail(objDto.getEmail());
 if(aux!=null) {
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