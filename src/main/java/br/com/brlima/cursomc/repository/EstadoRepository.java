package br.com.brlima.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brlima.cursomc.model.Estado;

@Repository
public interface EstadoRepository extends JpaRepository<Estado, Long> {
}
