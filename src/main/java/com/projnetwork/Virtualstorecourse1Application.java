package com.projnetwork;

import java.text.SimpleDateFormat;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projnetwork.entities.Address;
import com.projnetwork.entities.Category;
import com.projnetwork.entities.City;
import com.projnetwork.entities.Client;
import com.projnetwork.entities.ItemOrder;
import com.projnetwork.entities.Order;
import com.projnetwork.entities.Payment;
import com.projnetwork.entities.PaymentBoleto;
import com.projnetwork.entities.PaymentCreditCard;
import com.projnetwork.entities.Product;
import com.projnetwork.entities.Province;
import com.projnetwork.entities.enums.PaymentStatus;
import com.projnetwork.entities.enums.TypeClient;
import com.projnetwork.repositories.AddressRepository;
import com.projnetwork.repositories.CategoryRepository;
import com.projnetwork.repositories.CityRepository;
import com.projnetwork.repositories.ClientRepository;
import com.projnetwork.repositories.ItemOrderRepository;
import com.projnetwork.repositories.OrderRepository;
import com.projnetwork.repositories.PaymentRepository;
import com.projnetwork.repositories.ProductRepository;
import com.projnetwork.repositories.ProvinceRepository;




@SpringBootApplication
public class Virtualstorecourse1Application implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository repoAuxCategory;
	@Autowired
	private ProductRepository repoAuxProduct;
	@Autowired
	private CityRepository repoAuxCity;
	@Autowired
	private ProvinceRepository repoAuxProvince;
	@Autowired
	private ClientRepository repoAuxClient;
	@Autowired
	private AddressRepository repoAuxAddress;
	@Autowired
	private PaymentRepository repoAuxPayment;
	@Autowired
	private OrderRepository repoAuxOrder;
	@Autowired
	private ItemOrderRepository repoAuxItemOrder;
	

	public static void main(String[] args) {
		SpringApplication.run(Virtualstorecourse1Application.class, args);
	}

	//Comando para alimentat o banco para testes 
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		SimpleDateFormat sdfm=new SimpleDateFormat("dd/MM/yyyy hh:mm");	
		
		Category c1=new Category(null,"informatica");
		Category c2=new Category(null,"software");
		Category c3=new Category(null,"bebidas");
		Product p1=new Product(null,"computador",2000.00);
		Product p2=new Product(null, "impressora", 800.00);
		Product p3=new Product(null, "mouse", 80.00);
		Product p4=new Product(null, "teclado", 120.00);
		Product p5=new Product(null,"placa de video", 1550.00);
		Product p6=new Product(null,"vodka", 105.00);		
		Product p7=new Product(null,"vinnho", 200.00);
		Product p8=new Product(null,"software de edição de imagens um ano licença", 4550.00);
		
	/*	c1.getProducts().addAll(Arrays.asList(p1,p2,p3,p4,p5,p8));
		c2.getProducts().addAll(Arrays.asList(p8));
		c3.getProducts().addAll(Arrays.asList(p6,p7));*/
		
		repoAuxCategory.saveAll(Arrays.asList(c1,c2,c3));
		
		p1.getCategories().add(c1);
		p2.getCategories().add(c1);
		p3.getCategories().add(c1);
		p4.getCategories().add(c1);
		p5.getCategories().add(c1);
		p6.getCategories().add(c3);
		p7.getCategories().add(c3);
		p8.getCategories().add(c2);
		p8.getCategories().add(c1);
				
		repoAuxProduct.saveAll(Arrays.asList(p1,p2,p3,p4,p5,p6,p7,p8));
		
		Province pro1=new Province(null,"Ghuozou");
		Province pro2=new Province(null, "Zitzang");
		Province pro3=new Province(null, "york new");
		
		repoAuxProvince.saveAll(Arrays.asList(pro1,pro2,pro3));
				
		City cit1=new City(null, "choniang", pro1);
		City cit2=new City(null, "xanghey", pro2);
		City cit3=new City(null, "xintsu", pro1);
		City cit4=new City(null, "york new city", pro3);
		
		repoAuxCity.saveAll(Arrays.asList(cit1,cit2,cit3,cit4));
		
		
		
		Client cli1=new Client(null, "rosana@tmail.com","Rosana Oliveira Fernandes de Augustus","51274964255" ,TypeClient.PESSOAFISICA);	
		
		cli1.getPhones_numbers().addAll(Arrays.asList("55 14 714515588","55 13 381461455"));
		
		Address addr1=new Address(null, "81", "apartment", "new broklins", "51240130","Avenue new passdela",cli1, cit4);
		Address addr2=new Address(null, "1214", "house","Xanghey zone 1124", "81401021","Xanghey zone 1124 Avenue 5", cli1, cit3);
		
		cli1.getAdresses().addAll(Arrays.asList(addr1,addr2));
		repoAuxClient.save(cli1);
		repoAuxAddress.saveAll(Arrays.asList(addr1,addr2));
		
		Order ped1=new Order(null,sdfm.parse("30/09/2017 10:32"),addr1, cli1);
		Order ped2=new Order(null,sdfm.parse("30/09/2017 10:32"),addr2, cli1);			
		
		Payment pay1=new PaymentCreditCard(null, PaymentStatus.SETTLED, ped1, 6);
		ped1.setPayment(pay1);
		Payment pay2=new PaymentBoleto(null, PaymentStatus.PENDENT, ped2, sdfm.parse("27/10/2017 00:00"), sdfm.parse("20/10/2017 00:00"));
		ped2.setPayment(pay2);

		repoAuxOrder.saveAll(Arrays.asList(ped1,ped2));			
		repoAuxPayment.saveAll(Arrays.asList(pay1,pay2));
		cli1.getOrders().addAll(Arrays.asList(ped1,ped2));
		repoAuxClient.save(cli1);
		
		ItemOrder ito1=new ItemOrder(ped1, p1, 1,2000.00,0.00);
		ItemOrder ito2=new ItemOrder(ped1, p3, 2,500.00,20.00);
		ItemOrder ito3=new ItemOrder(ped2, p4, 4,1.200,0.00);
		ItemOrder ito4=new ItemOrder(ped2, p5, 1,222.00,00.00);
		ItemOrder ito5=new ItemOrder(ped2, p6, 2,100.05,4.15);
		ItemOrder ito6=new ItemOrder(ped2, p7, 3,150.00,2.00);
		
	
		
		ped1.getItens().addAll(Arrays.asList(ito1,ito2));
		ped2.getItens().addAll(Arrays.asList(ito3,ito4,ito5,ito6));
		
		repoAuxItemOrder.saveAll(Arrays.asList(ito1,ito2,ito3,ito4,ito5,ito6));
		
		
	}

}
