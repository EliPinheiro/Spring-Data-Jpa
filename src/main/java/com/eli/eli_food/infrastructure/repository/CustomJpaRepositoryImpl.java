package com.eli.eli_food.infrastructure.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import com.eli.eli_food.domain.repository.CustomJpaRepository;

import jakarta.persistence.EntityManager;



//Quando fazxemos isso temos que adicionar a anotação la na classe principal com a anotação @EnableJpaRepositories
// SimpleJpaRepository é a implemetação padrao do jpa 
public class CustomJpaRepositoryImpl<T, ID> extends SimpleJpaRepository<T, ID>
	implements CustomJpaRepository<T, ID>{

	private EntityManager manager;

	//Injeção de Dependências: Spring Data JPA fornece o EntityManager e JpaEntityInformation ao construtor da CustomJpaRepositoryImpl.
	public CustomJpaRepositoryImpl(JpaEntityInformation<T, ?> entityInformation, EntityManager entityManager) {
		super(entityInformation, entityManager); // esta chamando o construtor da simpleJpaRepository
		this.manager = entityManager;
	}

	@Override
	public Optional<T> buscarPrimeiro(){
		
		var  jpql = "from " + getDomainClass().getName(); // esse metodo getDomain é um metodo do simple e serve para pergarmos o nome da classe que esta extenddo ela
		T entity = manager.createQuery(jpql, getDomainClass())
				.setMaxResults(1) // o maximo que essa consulta retornar vai ser 1
				.getSingleResult(); // so pode retornar um resultado ou 0 ou 1 nesse caso
		
		return Optional.ofNullable(entity);// vai retornar um optional ou com o valor do retorn da entity ou ser nullo
	}
}
