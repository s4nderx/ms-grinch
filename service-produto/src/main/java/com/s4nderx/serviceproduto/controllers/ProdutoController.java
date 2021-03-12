package com.s4nderx.serviceproduto.controllers;

import com.s4nderx.serviceproduto.dtos.request.ProdutoPersistDTO;
import com.s4nderx.serviceproduto.dtos.response.ProdutoResponseDTO;
import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.services.ProdutoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("api/produtos")
public class ProdutoController {

    private final ProdutoService produtoService;

    public ProdutoController (ProdutoService produtoService){
        this.produtoService = produtoService;
    }

    @PostMapping
    public ResponseEntity<ProdutoResponseDTO> inserir(@RequestBody ProdutoPersistDTO dto) {

        Produto produto = new Produto(dto.getDescricao(), dto.getValor());
        produto = produtoService.inserir(produto);
        ProdutoResponseDTO responseDTO = new ProdutoResponseDTO(produto.getId(), produto.getDescricao(), produto.getValor());
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(responseDTO.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }
}
