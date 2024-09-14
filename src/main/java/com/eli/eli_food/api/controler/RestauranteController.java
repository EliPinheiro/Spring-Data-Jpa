package com.eli.eli_food.api.controler;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ReflectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.eli.eli_food.domain.exception.EntidadeNaoEncontradaException;
import com.eli.eli_food.domain.model.Restaurante;
import com.eli.eli_food.domain.repository.RestauranteRepository;
import com.eli.eli_food.domain.service.CadastroRestauranteService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {
	
	@Autowired
	private RestauranteRepository restauranteRepository;
	
	@Autowired
	private CadastroRestauranteService cadastroRestaurante;
	
	@GetMapping
	public List<Restaurante> listar(){
		return restauranteRepository.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Restaurante> burcar(@PathVariable Long id){
		Optional<Restaurante> restaurante = restauranteRepository.findById(id);
		
		if ( restaurante.isEmpty()) {
			return  ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(restaurante.get());
	}

	
	@PostMapping
	public ResponseEntity<?> adicionar(@RequestBody Restaurante restaurante){
		try {
			restaurante = cadastroRestaurante.salvar(restaurante);
			return ResponseEntity.status(HttpStatus.CREATED).body(restaurante);
			
		} catch(EntidadeNaoEncontradaException e) {
			return ResponseEntity.badRequest().body(e.getMessage());
		}
		
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<?> atualizar(@PathVariable Long id, @RequestBody Restaurante restaurante){
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);
		
		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		try {
			BeanUtils.copyProperties(restaurante, restauranteAtual.get(), "id");
			Restaurante novoRestaurante = cadastroRestaurante.salvar(restauranteAtual.get());
			return ResponseEntity.status(HttpStatus.CREATED).body(novoRestaurante);
		}catch (EntidadeNaoEncontradaException e) {
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
		}
		
	}
	
	@PatchMapping("/{id}")
	public ResponseEntity<?> atualizarParcial(@PathVariable Long id,  @RequestBody Map<String, Object> campos){
		
		Optional<Restaurante> restauranteAtual = restauranteRepository.findById(id);

		if (restauranteAtual.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		merge(campos, restauranteAtual.get());
		
		return atualizar(id, restauranteAtual.get());
		
	}

	private void merge(Map<String, Object> camposOrigem, Restaurante restauranteDestino) {
		ObjectMapper objectMapper = new ObjectMapper();    //Cria uma instância de ObjectMapper, uma classe da biblioteca Jackson que facilita a conversão entre objetos Java e representações JSON.
		Restaurante restauranteOrigem = objectMapper.convertValue(camposOrigem, Restaurante.class); //Essa linha converte o Map<String, Object> camposOrigem em uma instância do tipo Restaurante usando a biblioteca Jackson. O ObjectMapper é responsável por mapear os valores do Map para os campos correspondentes no objeto Restaurante.
		
		camposOrigem.forEach((nomePropriedade, valorPropriedade) -> {
			Field field = ReflectionUtils.findField(Restaurante.class, nomePropriedade); //Essa linha usa a classe ReflectionUtils para localizar um campo específico na classe Restaurante com base no nome da propriedade (nomePropriedade)
			field.setAccessible(true); //Esse campo consegue acessar a propriedade da classe mesmo ela sendo privada
			
			Object novoValor = ReflectionUtils.getField(field, restauranteOrigem); //Essa linha obtém o valor atual do campo especificado no objeto restauranteOrigem usando o objeto Field encontrado anteriormente. 
			ReflectionUtils.setField(field, restauranteDestino, novoValor); //Essa linha define o valor de um campo específico no objeto restauranteDestino usando o valor obtido do campo no objeto restauranteOrigem
		});
		
		
		
	}
}

















