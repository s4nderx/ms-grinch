package com.s4nderx.serviceproduto.services;

import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.event.ProdutoPersistEvent;
import com.s4nderx.serviceproduto.repositories.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import javax.persistence.NoResultException;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final ProdutoRepository repository;
    private final ApplicationEventPublisher eventPublisher;

    public ProdutoServiceImpl(ProdutoRepository produtoRepository, ApplicationEventPublisher eventPublisher){
        this.repository = produtoRepository;
        this.eventPublisher = eventPublisher;
    }

    @Override
    public Produto one(Long id) {
        return repository.findById(id).orElseThrow(() -> new NoResultException(String.format("Produto de código %d não encontrado", id)));
    }

    @Override
    public Produto save(Produto produto) {
        Produto produtoPersist = repository.save(produto);
        eventPublisher.publishEvent(new ProdutoPersistEvent(this, produto));
        return produtoPersist;
    }

    @Override
    public void delete(Long id) {
        if(!repository.existsById(id)){
            throw new NoResultException(String.format("Produto de código %d não encontrado", id));
        }
        repository.deleteById(id);
    }

    @Override
    public Produto update(Produto produto) {
        if(!repository.existsById(produto.getId())){
            throw new NoResultException(String.format("Produto de código %d não encontrado", produto.getId()));
        }

        return this.save(produto);

    }
}
