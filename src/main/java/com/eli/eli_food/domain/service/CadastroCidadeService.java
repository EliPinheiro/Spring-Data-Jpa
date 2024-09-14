package com.eli.eli_food.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.eli.eli_food.domain.exception.EntidadeNaoEncontradaException;
import com.eli.eli_food.domain.model.Cidade;
import com.eli.eli_food.domain.model.Estado;
import com.eli.eli_food.domain.repository.CidadeRepository;
import com.eli.eli_food.domain.repository.EstadoRepository;

@Service
public class CadastroCidadeService {

	
	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	public Cidade salvar(Cidade cidade) {
		Long estadoId = cidade.getEstado().getId();
		Optional<Estado> estado = estadoRepository.findById(estadoId);
		
		if (estado.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe estado com o id %d", estadoId));
		}
		cidade.setEstado(estado.get());
		
		
		
		return cidadeRepository.save(cidade);
	}

	public void deletar(Long id) {
		Optional<Cidade> cidade = cidadeRepository.findById(id);
		if (cidade.isEmpty()) {
			throw new EntidadeNaoEncontradaException(String.format("Não existe cidade cadastrada com esse o id %d.", id));
		}
		cidadeRepository.delete(cidade.get());;
		
	}
	
	
	
}
