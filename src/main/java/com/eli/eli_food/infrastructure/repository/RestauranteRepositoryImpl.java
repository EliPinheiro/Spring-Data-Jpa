package com.eli.eli_food.infrastructure.repository;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;
import org.springframework.util.StringUtils;

import com.eli.eli_food.domain.model.Restaurante;
import com.eli.eli_food.domain.repository.RestauranteRepositoryQueries;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

@Repository
public class RestauranteRepositoryImpl implements RestauranteRepositoryQueries {

	@PersistenceContext
	private EntityManager manager;
	
	
	
	// Dessa forma todos os parametros devem ser passados, ela não é dinâmica
	
//	@Override
//	public List<Restaurante> find(String nome, 
//			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//
//
//		
//		var jpql = "from Restaurante where nome like :nome "
//				+ "and taxaFrete between :taxaInicial and :taxaFinal";
//		
//		return manager.createQuery(jpql, Restaurante.class)
//				.setParameter("nome", "%" + nome + "%")
//				.setParameter("taxaInicial", taxaFreteInicial)
//				.setParameter("taxaFinal", taxaFreteFinal)
//				.getResultList();
//		
		
		
	//Dessa forma ela é maid dinamica
		
//		@Override
//		public List<Restaurante> find(String nome, 
//				BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
//			
//			var jpql = new StringBuilder();
//			jpql.append("from Restaurante where 0 = 0 ");
//			
//			var parametros = new HashMap<String, Object>();
//			
//			if (StringUtils.hasLength(nome)) {
//				jpql.append("and nome like :nome ");
//				parametros.put("nome", "%" + nome + "%");
//			}
//			
//			if (taxaFreteInicial != null) {
//				jpql.append("and taxaFrete >= :taxaInicial ");
//				parametros.put("taxaInicial", taxaFreteInicial);
//			}
//			
//			if (taxaFreteFinal != null) {
//				jpql.append("and taxaFrete <= :taxaFinal ");
//				parametros.put("taxaFinal", taxaFreteFinal);
//			}
//			
//			TypedQuery<Restaurante> query = manager.createQuery(jpql.toString(), Restaurante.class);
//			
//			parametros.forEach((chave, valor) -> query.setParameter(chave, valor));
//
//			return query.getResultList();
//		}
	
	
	// Fazendo a consulta com Criteria
	
	

	@Override
	public List<Restaurante> find(String nome, 
			BigDecimal taxaFreteInicial, BigDecimal taxaFreteFinal) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();   //Esse criteriaBuilder funciona com uma fabrica e é a partir dele que montaremos a query
		
		CriteriaQuery<Restaurante> criteria = builder.createQuery(Restaurante.class); // Aqui é para tipar a criteria builder criada
		Root<Restaurante> root = criteria.from(Restaurante.class); // Esse root é vai ser usado para termos acesso às propriedade da classe tipada.

		var predicates = new ArrayList<Predicate>(); //Vamos precisar de um array para adicionar os predicates que seram usados na construção da querry
		
		
		// No contexto do JPA Criteria API, um Predicate é usado para construir consultas dinâmicas. O Predicate define a condição de filtragem na consulta.
		if (StringUtils.hasText(nome)) {
			predicates.add(builder.like(root.get("nome"), "%" + nome + "%")); // Adição desse predicate no array
		}
		
		if (taxaFreteInicial != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("taxaFrete"), taxaFreteInicial));  // Adição desse predicate no array
		}
		
		if (taxaFreteFinal != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("taxaFrete"), taxaFreteFinal));  // Adição desse predicate no array
		}
		
		criteria.where(predicates.toArray(new Predicate[0])); // o clausula where do criteria não aceita um arrayList , mas sim um varargs ou seja um array. Então devemos tranformar o arrayList em array.
		
		var query = manager.createQuery(criteria);
		return query.getResultList();
	}
}
		
		
	
	
