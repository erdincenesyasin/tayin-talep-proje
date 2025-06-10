package com.eparlak.personeltayintalebi;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication
@EnableAspectJAutoProxy
public class PersonelTayinTalebiApplication {

	public static void main(String[] args) {



		SpringApplication.run(PersonelTayinTalebiApplication.class, args);
		
	}

}
