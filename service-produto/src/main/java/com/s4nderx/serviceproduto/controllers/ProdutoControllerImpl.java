package com.s4nderx.serviceproduto.controllers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.github.fge.jsonpatch.JsonPatch;
import com.github.fge.jsonpatch.JsonPatchException;
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
        produto = produtoService.save(produto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @Override
    @GetMapping("{id}")
    public Produto one(@PathVariable("id") Long id){
        return produtoService.one(id);
    }

    @Override
    @PatchMapping("id")
    public Produto update(@PathVariable("id") Long id, @RequestBody JsonPatch jsonPatch) throws JsonPatchException, JsonProcessingException {

        Produto produto = produtoService.one(id);

        ObjectMapper objectMapper = new ObjectMapper()
                .disable(DeserializationFeature.USE_BIG_DECIMAL_FOR_FLOATS)
                .enable(JsonGenerator.Feature.WRITE_BIGDECIMAL_AS_PLAIN)
                .setNodeFactory(JsonNodeFactory.withExactBigDecimals(true));

        JsonNode jsonNode = objectMapper.convertValue(produto, JsonNode.class);
        JsonNode patchJsonNode = jsonPatch.apply(jsonNode);
        Produto produtoPersist = objectMapper.treeToValue(patchJsonNode, Produto.class);
        return produtoService.save(produtoPersist);

    }

    @Override
    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id){
        produtoService.delete(id);
    }

    @Override
    @PutMapping("id")
    @ResponseStatus(HttpStatus.OK)
    public Produto updtateAll(@PathVariable("id") Long id, @RequestBody ProdutoPersistDTO dto) {
        Produto produto = new Produto(id, dto.getDescricao(), dto.getValor());
        return produtoService.update(produto);
    }

}
