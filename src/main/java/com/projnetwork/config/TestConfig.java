package com.projnetwork.config;

import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.projnetwork.services.DatabaseCreateService;

@Configuration
@Profile( "test" )
public class TestConfig {
	
	@Autowired
	private DatabaseCreateService databaseService;
	
	@Bean
	public boolean intantiateDatabase() throws ParseException {
		
		databaseService.intantiateDatabase();
		return true;
		
	}

}
