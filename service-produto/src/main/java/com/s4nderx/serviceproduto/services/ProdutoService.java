package com.s4nderx.serviceproduto.services;

import com.s4nderx.serviceproduto.entities.Produto;

public interface ProdutoService {

    Produto inserir(Produto produto);

    Produto one(Long id);
}
