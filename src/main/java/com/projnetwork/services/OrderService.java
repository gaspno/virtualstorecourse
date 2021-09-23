package com.projnetwork.services;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projnetwork.entities.ItemOrder;
import com.projnetwork.entities.Order;
import com.projnetwork.entities.PaymentBoleto;
import com.projnetwork.entities.enums.PaymentStatus;
import com.projnetwork.exceptions.ObjectNotFoundException;
import com.projnetwork.repositories.ItemOrderRepository;
import com.projnetwork.repositories.OrderRepository;
import com.projnetwork.repositories.PaymentRepository;
import com.projnetwork.repositories.ProductRepository;

@Service
public class OrderService {
	
	@Autowired
	private OrderRepository repositoryOrder;
	@Autowired
	private PaymentRepository repositoryPayment;
	@Autowired
	private ProductRepository repositoryProduct;
	@Autowired
	private ItemOrderRepository repositoryItemOrder;
	
	//função para exibir todas as categorias
	public List<Order> findAll(){
		return repositoryOrder.findAll();
	}
	public Order findById(Integer id){
		Optional<Order> Order= repositoryOrder.findById(id);
		return Order.orElseThrow(()->new ObjectNotFoundException("Categoria não encontrada"));
	}
	public Order insert( Order obj) {
		obj.setId(null);
		obj.setInstant(new Date());
		obj.getPayment().setStatus(PaymentStatus.PENDENT);
		obj.getPayment().setOrder(obj);
		if(obj.getPayment() instanceof PaymentBoleto) {
			PaymentBoleto pag=(PaymentBoleto) obj.getPayment();
			Calendar cal=Calendar.getInstance();
			cal.setTime(new Date());
			cal.add(Calendar.DATE, 7);			
			pag.setDateExpired(cal.getTime());
			
		}
		repositoryOrder.save(obj);
		repositoryPayment.save(obj.getPayment());
		for(ItemOrder x : obj.getItens()) {
			x.setDiscount(0.0);
			x.setPrice(repositoryProduct.getById(x.getProduct().getId()).getPrice());
			x.setOrder(obj);
		}
		repositoryItemOrder.saveAll(obj.getItens());
		return obj;
		
	}

}
