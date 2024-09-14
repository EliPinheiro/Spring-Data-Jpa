package com.eli.eli_food.api.controler;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.eli.eli_food.domain.model.Cozinha;
import com.eli.eli_food.domain.model.Restaurante;
import com.eli.eli_food.domain.repository.CozinhaRepository;
import com.eli.eli_food.domain.repository.RestauranteRepository;
import com.eli.eli_food.infrastructure.repository.specifications.FabricaSpecificationsRestaurante;
import com.eli.eli_food.infrastructure.repository.specifications.RestauranteComFreteGratisSpec;
import com.eli.eli_food.infrastructure.repository.specifications.comNomeParecidoSpec;

@RestController
@RequestMapping("/teste")
public class TesteClasse {

	@Autowired
	private CozinhaRepository cozinhaRepository;
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@GetMapping("/consultar-por-nome")
	public List<Cozinha> consultarPorNome(@RequestParam String nome){
		return cozinhaRepository.findByNome(nome);
	}
	
	@GetMapping("/containing-por-nome")
	public List<Cozinha> consultarPorNomeContaining(@RequestParam String nome){
		return cozinhaRepository.findByNomeContaining(nome);
	}
	
	@GetMapping("/taxa-frete")
	public List<Restaurante> consultarPorTaxaFrete(BigDecimal taxaInicial, BigDecimal taxaFinal){
		return restauranteRepository.findByTaxaFreteBetween(taxaInicial, taxaFinal);
	}
	
	@GetMapping("restaurante/por-nome-e-frete")
	public List<Restaurante> restaurantesPorNomeFrete(String nome,
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal){
		return restauranteRepository.find(nome, taxaFreteInicial, taxaFreteFinal);
	}

	
//	@GetMapping("/por-nome")
//	public List<Restaurante> consultarPorTaxaFrete(String nome, Long cozinhaId){
//		return restauranteRepository.findByNomeContainingAndCozinhaId(nome, cozinhaId);
//	}
	
	@GetMapping("/por-nome")
	public List<Restaurante> consultarPorTaxaFrete(String nome, Long cozinhaId){
		return restauranteRepository.nomeEid(nome, cozinhaId);
	}
	
	@GetMapping("/primeiro-nome")
	public Optional<Restaurante> consultaPrimeiro(String nome){
		return restauranteRepository.findFirstByNomeContaining(nome);
	}
	
	@GetMapping("/top-dois-nome")
	public List<Restaurante> consultarDoid(String nome){
		return restauranteRepository.findTop2ByNomeContaining(nome);
	}
	
	@GetMapping("/existe-nome")
	public boolean existe(String nome){
		return restauranteRepository.existsByNome(nome);
	}
	
	@GetMapping("/contar")
	public int contarCozinha(Long cozinhaId){
		return restauranteRepository.countByCozinhaId(cozinhaId);
	}
	
	
////////////////////////////////////////////////
	//No Spring Framework, uma Specification é uma interface do pacote org.springframework.data.jpa.domain que fornece uma maneira de criar consultas dinâmicas e complexas para entidades JPA (Java Persistence API). Ela faz parte do módulo Spring Data JPA e permite que você defina critérios de consulta de forma programática.
	
//	@GetMapping("/restaurantes/com-frete-gratis")
//	public List<Restaurante> restauranteComFreteGratis(String nome){
//		var comFreteGratis = new RestauranteComFreteGratisSpec();
//		var comNomeParecido = new comNomeParecidoSpec(nome);
//		// esse metodo findAll vem da interface JpaSpecificationExecutor que esta na interface RestauranteRepository 
//		return restauranteRepository.findAll(comFreteGratis.and(comNomeParecido));
//	}
	
	
	//Essa metodo é igual o de cima, porem ele ta usando os metodos da classe fabricaSpecifications para retorn o predicate e não instanciando classe por classe como a de cima
	@GetMapping("/restaurantes/com-frete-gratis")
	public List<Restaurante> restauranteComFreteGratis(String nome){
		
		return restauranteRepository.findAll(FabricaSpecificationsRestaurante.comFreteGratis()
				.and(FabricaSpecificationsRestaurante.comNomeSemelhante(nome)));
	}
	
//////////////////////////////////////////////////////////////////////////////////////////
	//Esse metodo é para usar a nossa exten~çao da classe de implementaão do jpa, ou seja a extensao do simpleRepository entaõ agora nos temos um nova implementaçaõ de um metodo que criamos 
	// temos agora a classe CustomJpaRepository que adiciona esse metodo e a classe CustomJpaRepositoryImpl que o implementa
	//lembre-se  o restaurante respository esta estendendo o customJpaRepository por isso agora temos aceeso ao metodo buscar primeiro
	@GetMapping("/restaurante/primeiro")
	public Optional<Restaurante> restaurantePrimeiro(){
		return restauranteRepository.buscarPrimeiro();
	}
	@GetMapping("/cozinha/primeiro")
	public Optional<Cozinha> cozinhaPrimeiro(){
		return cozinhaRepository.buscarPrimeiro();
	}
}
