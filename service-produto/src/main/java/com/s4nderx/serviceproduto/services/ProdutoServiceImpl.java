package com.s4nderx.serviceproduto.services;

import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.repositories.ProdutoRepository;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

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

    @Override
    public Produto one(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }
}
