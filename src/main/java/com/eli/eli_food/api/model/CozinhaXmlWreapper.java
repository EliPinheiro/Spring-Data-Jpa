package com.eli.eli_food.api.model;

import java.util.List;

import com.eli.eli_food.domain.model.Cozinha;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlElementWrapper;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;

import lombok.Data;
import lombok.NonNull;

@JacksonXmlRootElement(localName = "Lista de cozinhas")
@Data
public class CozinhaXmlWreapper {

	@JsonProperty("cozinha unidade")
	@JacksonXmlElementWrapper(useWrapping = false)
	@NonNull // O lombok gera construtores serm parametros, porem se declararmos esse nonnull o construtor gera um com ele. Pois agora ele é obrigatorio
	private List<Cozinha> cozinhas;
	
	
}
