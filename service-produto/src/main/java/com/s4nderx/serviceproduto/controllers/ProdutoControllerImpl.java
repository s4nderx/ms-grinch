package com.s4nderx.serviceproduto.controllers;

import com.github.fge.jsonpatch.JsonPatch;
import com.s4nderx.serviceproduto.dtos.request.ProdutoPersistDTO;
import com.s4nderx.serviceproduto.dtos.response.ProdutoResponseDTO;
import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.services.ProdutoService;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping("api/produtos")
public class ProdutoControllerImpl implements ProdutoController{

    private final ProdutoService produtoService;

    public ProdutoControllerImpl(ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @Override
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Produto> inserir(@Valid @RequestBody ProdutoPersistDTO dto) {

        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        produto = produtoService.inserir(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @Override
    @GetMapping("{id}")
    public Produto one(@PathVariable("id") Long id){
        return produtoService.one(id);
    }

//    @PatchMapping("id")
//    public Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch jsonPatch){
//
//    }


}
