package com.eli.eli_food.domain.repository;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.eli.eli_food.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends CustomJpaRepository<Cozinha, Long> {

	
	List<Cozinha> findByNome(String nome); //O spring data Jpa irá fazer a imprementação em tempo de execução.
//	Optional<Cozinha> findByNome(String nome); // Posso retornar um optional
	
	List<Cozinha> findByNomeContaining(String nome); // a palavra containing é como se fosse o like nas consultas ddl.


}