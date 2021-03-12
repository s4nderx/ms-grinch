package com.s4nderx.serviceproduto.dtos.request;

import java.math.BigDecimal;

public class ProdutoPersistDTO {

    private final String descricao;

    private final BigDecimal valor;

    public ProdutoPersistDTO(String descricao, BigDecimal valor) {
        this.descricao = descricao;
        this.valor = valor;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }
}
