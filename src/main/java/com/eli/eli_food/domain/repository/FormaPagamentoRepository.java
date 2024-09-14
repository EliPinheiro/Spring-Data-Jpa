package com.eli.eli_food.domain.repository;

import java.util.List;

import com.eli.eli_food.domain.model.FormaPagamento;

public interface FormaPagamentoRepository {

    List<FormaPagamento> listar();
    FormaPagamento buscar(Long id);
    FormaPagamento salvar(FormaPagamento formaPagamento);
    void remover(FormaPagamento formaPagamento);
    
}  
