package com.s4nderx.serviceproduto.repositories;

import com.s4nderx.serviceproduto.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
