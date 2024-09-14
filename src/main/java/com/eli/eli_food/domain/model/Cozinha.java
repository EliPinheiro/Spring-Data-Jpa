package com.eli.eli_food.domain.model;

import java.util.Objects;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;


@JsonRootName("gastronomia") //muda o nome do root na serialização em XML. Ou seja, não aparecerá cozinha e sim gastronomia
@Data
@Entity
@Table(name = "cozinha")
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cozinha {
	
	@EqualsAndHashCode.Include
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
//	@JsonIgnore //Na serialização essa propriedade sera ignorada
//	@JsonProperty("Titulo")  //Na serialização essa propriedade terá esse nome
	@Column(name = "nome",  length=30)
	private String nome;


}
