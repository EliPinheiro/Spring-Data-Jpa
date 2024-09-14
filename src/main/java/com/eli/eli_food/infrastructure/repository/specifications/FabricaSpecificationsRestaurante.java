package com.eli.eli_food.infrastructure.repository.specifications;

import java.math.BigDecimal;

import org.springframework.data.jpa.domain.Specification;

import com.eli.eli_food.domain.model.Restaurante;


// Essa classe funciona como uma fabrica de specs pois seus metodos retornam predicates que seram passados 
public class FabricaSpecificationsRestaurante {

	public static Specification<Restaurante> comFreteGratis() {
		return (root, query, builder) -> 
			builder.equal(root.get("taxaFrete"), BigDecimal.ZERO);
	}
	
	public static Specification<Restaurante> comNomeSemelhante(String nome) {
		return (root, query, builder) ->
			builder.like(root.get("nome"), "%" + nome + "%");
	}
	
}
