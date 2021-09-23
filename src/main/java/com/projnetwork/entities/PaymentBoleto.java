package com.projnetwork.entities;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.projnetwork.entities.enums.PaymentStatus;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PaymentBoleto extends Payment{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Column(name = "date_de_expiração")
	private Date dateExpired;
	@Column(name = "numero_de_emissão")
	private Date dateEmited;
	
	public PaymentBoleto() {
		
	}
	
	public PaymentBoleto(Integer id, PaymentStatus status, Order order,Date dateExpired, Date dateEmited) {
		super(id, status, order);
		this.dateExpired = dateExpired;
		this.dateEmited = dateEmited;		
	}
	@JsonFormat(pattern = "dd/MM/yyyy")
	public Date getDateExpired() {
		return dateExpired;
	}

	public void setDateExpired(Date dateExpired) {
		this.dateExpired = dateExpired;
	}
	@JsonFormat(pattern = "dd/MM/yyyy")
	public Date getDateEmited() {
		return dateEmited;
	}

	public void setDateEmited(Date dateEmited) {
		this.dateEmited = dateEmited;
	}


	
	
}
