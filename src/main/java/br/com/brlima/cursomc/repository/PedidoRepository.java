package br.com.brlima.cursomc.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.brlima.cursomc.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
}