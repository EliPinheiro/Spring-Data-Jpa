package com.eli.eli_food.domain.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;


// Essa clase vai implemtar mais uma interfaca na hierarquia dos JpaRepository agora posso trocar as interfaces que estão herdando o JpaRepository usando o CustomJpaRepository e agora vai mais um metodo para ser implemtadno que o o buscar primeiro
@NoRepositoryBean //É comum usar @NoRepositoryBean em interfaces ou classes base que definem métodos genéricos ou comuns que são compartilhados entre diferentes repositórios específicos.
public interface CustomJpaRepository<T ,ID> extends JpaRepository<T ,ID>{

	Optional<T> buscarPrimeiro();
}
