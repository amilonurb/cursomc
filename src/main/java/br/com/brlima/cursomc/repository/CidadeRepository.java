package br.com.brlima.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brlima.cursomc.model.Cidade;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
}
