package com.eli.eli_food.domain.repository;

import java.util.List;

import com.eli.eli_food.domain.model.Permissao;

public interface PermissaoRepository {

    List<Permissao> listar();
    Permissao buscar(Long id);
    Permissao salvar(Permissao permissao);
    void remover(Permissao permissao);
    
}
