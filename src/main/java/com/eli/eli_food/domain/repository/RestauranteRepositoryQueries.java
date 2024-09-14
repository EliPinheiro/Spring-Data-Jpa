package com.eli.eli_food.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import com.eli.eli_food.domain.model.Restaurante;

public interface RestauranteRepositoryQueries {

	List<Restaurante> find(String nome, BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal);

}