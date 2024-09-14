package com.eli.eli_food.domain.model;

import java.math.BigDecimal;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Restaurante {

	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(nullable= false)
	private String nome;
	
	@Column(name = "taxa_frete", nullable =false)
	private BigDecimal taxaFrete;

	@JoinColumn(name = "cozinha_id", nullable= false) //Esse joinColumn é para nomear foreingKey de relacionamento entre entidades
	@ManyToOne //Muito restaurante tem uma cozinha. O many é o resturante pois é a classe que estamos
	private Cozinha cozinha;

	
}
