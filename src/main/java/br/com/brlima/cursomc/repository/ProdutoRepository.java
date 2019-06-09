package br.com.brlima.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brlima.cursomc.model.produto.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
}
