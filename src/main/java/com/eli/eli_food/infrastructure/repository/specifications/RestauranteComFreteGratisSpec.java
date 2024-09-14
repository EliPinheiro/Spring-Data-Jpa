package com.eli.eli_food.infrastructure.repository.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.eli.eli_food.domain.model.Restaurante;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

public class RestauranteComFreteGratisSpec implements Specification<Restaurante>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	// Esse é um metodo que retornará um predicate que compara o valor da taxaFrete com o BigDecimal 0
	@Override
	public Predicate toPredicate(Root<Restaurante> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		return builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}

}
