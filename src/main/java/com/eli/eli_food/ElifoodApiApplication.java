package com.eli.eli_food;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import com.eli.eli_food.infrastructure.repository.CustomJpaRepositoryImpl;


//Essa anotação tem outra anotação chamada componentScan que analisa todo o projeto na inicialização 
//da aplicação para saber quais classes serão gerenciadas pelo container do Spring.
@SpringBootApplication 
@EnableJpaRepositories(repositoryBaseClass = CustomJpaRepositoryImpl.class) // Essa anotação so dever ser usaso quando alteramos a implentação base do jpa.
public class ElifoodApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ElifoodApiApplication.class, args);
	}

}
