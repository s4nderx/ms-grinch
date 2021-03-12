package com.s4nderx.serviceproduto.listener;

import com.s4nderx.serviceproduto.entities.Produto;
import com.s4nderx.serviceproduto.event.ProdutoPersistEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
@Order(2)
public class ProdutoPersistLogListener implements ApplicationListener<ProdutoPersistEvent> {

    @Override
    public void onApplicationEvent(ProdutoPersistEvent produtoPersistEvent) {
        Produto produto = produtoPersistEvent.getProduto();
        System.out.println(produto.getDescricao());
    }

}
