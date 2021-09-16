package com.projnetwork;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projnetwork.entities.Category;
import com.projnetwork.entities.Product;
import com.projnetwork.repositories.CategoryRepository;
import com.projnetwork.repositories.ProductRepository;




@SpringBootApplication
public class Virtualstorecourse1Application implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository repoAuxCategory;
	@Autowired
	private ProductRepository repoAuxProduct;
	

	public static void main(String[] args) {
		SpringApplication.run(Virtualstorecourse1Application.class, args);
	}

	//Comando para alimentat o banco para testes 
	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
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
		
		
	}

}
