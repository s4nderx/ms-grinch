package com.s4nderx.serviceproduto.dtos.request;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class ProdutoPersistDTO {

    @NotEmpty
    private final String descricao;

    @NotNull
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
