package com.projnetwork.entities;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;
import com.projnetwork.entities.enums.PaymentStatus;

@Entity
@JsonTypeName("pagamentoComCartao")
public class PaymentCreditCard extends Payment{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer numberParcels;

	public PaymentCreditCard() {
		
		
	}

	public PaymentCreditCard(Integer id, PaymentStatus status, Order order,Integer numberParcels) {
		super(id, status, order);
		this.numberParcels = numberParcels;
	}

	public Integer getNumberParcels() {
		return numberParcels;
	}

	public void setNumberParcels(Integer numberParcels) {
		this.numberParcels = numberParcels;
	}
	
	

	
}
