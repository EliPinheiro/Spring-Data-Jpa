package com.eli.eli_food.infrastructure.repository.specifications;

import org.springframework.data.jpa.domain.Specification;

import com.eli.eli_food.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;
// Essa anotação do lombok cria um construtor com todos os propriedades de instacia
@AllArgsConstructor
public class comNomeParecidoSpec implements Specification<Restaurante>{/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String nome;

	
	//Esse metodo vai retornar um predicate que faz um like usando o nome passado
	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		
		return builder.like(root.get("nome"), "%" + nome + "%"); // Lembre-se esse nome que está sendo concatenado vem do contrutor
	}
	
	

}
