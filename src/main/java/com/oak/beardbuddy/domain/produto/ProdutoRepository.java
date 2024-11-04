package com.oak.beardbuddy.domain.produto;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;


public interface ProdutoRepository extends JpaRepository<Produto, Long> {
    @Query(value = "SELECT * FROM TB_PRODUTOS WHERE tipo = :tipo", nativeQuery = true)
    Page<Produto> findByTipo(@Param("tipo") String tipo, Pageable pageable);
}
