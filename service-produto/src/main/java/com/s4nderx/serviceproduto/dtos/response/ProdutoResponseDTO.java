package com.s4nderx.serviceproduto.dtos.response;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private final Long id;
    private final String descricao;
    private final BigDecimal valor;

    public ProdutoResponseDTO(Long id, String descricao, BigDecimal valor) {
        this.id = id;
        this.descricao = descricao;
        this.valor = valor;
    }

    public Long getId() {
        return id;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
