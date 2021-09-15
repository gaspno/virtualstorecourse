package com.projnetwork;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.projnetwork.entities.Category;
import com.projnetwork.repositories.CategoryRepository;




@SpringBootApplication
public class Virtualstorecourse1Application implements CommandLineRunner{
	
	@Autowired
	private CategoryRepository repoAux;

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
		repoAux.saveAll(Arrays.asList(c1,c2,c3));
		
		
	}

}
