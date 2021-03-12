package com.s4nderx.serviceproduto.dtos.response;

import java.math.BigDecimal;

public class ProdutoResponseDTO {

    private String descricao;
    private BigDecimal valor;

    @Deprecated
    public ProdutoResponseDTO() {
    }


    public String getDescricao() {
        return descricao;
    }

    @Deprecated
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getValor() {
        return valor;
    }

    @Deprecated
    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}
