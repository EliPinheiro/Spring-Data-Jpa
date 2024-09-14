package com.eli.eli_food.domain.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.eli.eli_food.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Long>, RestauranteRepositoryQueries,
	JpaSpecificationExecutor<Restaurante>{ // esse interface JpaSpecificationExecutor e adicionado pois precisado de um findAll que está sendo usada la no controller teste. pois ele aceita predicates

	//https://docs.spring.io/spring-data/jpa/reference/jpa/query-methods.html   link para metodos 
	
	List<Restaurante> findByTaxaFreteBetween(BigDecimal taxaInicial, BigDecimal taxaFinal);


	
	
/////////////////////////////////////////////////////////////////////////////////////////////////	
	
	//aqui o foi criada uma consulta jpql dentro da pasta meta vide com tem que ser feita a implemtação
	List<Restaurante> nomeEid (String nome, @Param("id") Long cozinhaId);
	
	
	// Aqui estamos declarando a jpql dentro da query
//	@Query("from Restaurante where nome like %:nome% and cozinha.id = :id")
//	List<Restaurante> nomeEid (String nome, @Param("id") Long cozinhaId);
	
	
	// aqui é um jeito de fazer com o Jpa Respository
//	List<Restaurante> findByNomeContainingAndCozinhaId (String nome, Long cozinhaId); //Aqui a consulta é feita com mais de um critério, ela ta buscando por um like do nome e o id da classe cozinha

	

/////////////////////////////////////////////////////////////////////////////////////////////////	

	
	// Esse metodo é de uma classe (RestauranteRepositoryImpl) que implemeta um metodo da interface (RestauranteRepositoryQueries) e como essa interface aqui tambem implementa a RestauranteRepositoryQueries  o spring sabe onde buscar essa implenmtação
	public List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);
	
	
	
	
	Optional<Restaurante> findFirstByNomeContaining (String nome); //Aqui ele so vai retornar o primeiro encontrado

	
	List<Restaurante> findTop2ByNomeContaining (String nome); // aqui com o profixo top a gente pode delimitar quantos será retornado

	boolean existsByNome(String nome);
	
	int countByCozinhaId(Long id);




	
	
	

}