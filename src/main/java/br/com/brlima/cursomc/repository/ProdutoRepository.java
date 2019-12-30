package br.com.brlima.cursomc.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.model.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {

    // @Transactional(readOnly = true)
    // @Query("SELECT DISTINCT p FROM PRODUTO p INNER JOIN p.categorias c WHERE p.nome LIKE %:nome% AND c IN :categorias")
    // Page<Produto> search(@Param("nome") String nome, @Param("categorias") List<Categoria> categorias, Pageable pageRequest);
    
    // A mesma buscar anterior mas usando o padrão de nomes do jpa
    @Transactional(readOnly = true)
    Page<Produto> findDistinctByNomeContainingAndCategoriasIn(String nome, List<Categoria> categorias, Pageable pageRequest);
}
