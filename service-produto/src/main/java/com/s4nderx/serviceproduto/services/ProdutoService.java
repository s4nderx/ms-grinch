package com.s4nderx.serviceproduto.services;

import com.s4nderx.serviceproduto.entities.Produto;

public interface ProdutoService {

    Produto one(Long id);

    Produto save(Produto produto);

    void delete(Long id);

    Produto update(Produto produto);
}
