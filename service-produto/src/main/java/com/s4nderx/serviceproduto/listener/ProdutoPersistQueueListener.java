package com.s4nderx.serviceproduto.listener;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.s4nderx.serviceproduto.event.ProdutoPersistEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.annotation.Order;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;

@Component
@Order(1)
public class ProdutoPersistQueueListener implements ApplicationListener<ProdutoPersistEvent> {

    private static final Logger log = Logger.getLogger(ProdutoPersistQueueListener.class.getName());

    private final ObjectMapper objectMapper;

    private final JmsTemplate jmsTemplate;

    public ProdutoPersistQueueListener(ObjectMapper objectMapper, JmsTemplate jmsTemplate) {
        this.objectMapper = objectMapper;
        this.jmsTemplate = jmsTemplate;
    }

    @Override
    public void onApplicationEvent(ProdutoPersistEvent produtoPersistEvent) {
        try {

            var produto = produtoPersistEvent.getProduto();
            String json = objectMapper.writer().writeValueAsString(produto);
            jmsTemplate.convertAndSend("produto.persist.queue", json);
        } catch (JsonProcessingException e) {
            log.finer(e.getMessage());
            e.printStackTrace();
        }
    }

}
