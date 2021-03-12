package com.s4nderx.serviceproduto.services;

import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository){
        this.repository = produtoRepository;
    }
    @Override
    public Produto inserir(Produto produto) {
        return repository.save(produto);
    }
}
