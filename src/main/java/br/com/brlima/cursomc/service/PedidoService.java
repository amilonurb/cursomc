package br.com.brlima.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.pedido.Pedido;
import br.com.brlima.cursomc.repository.PedidoRepository;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class PedidoService {

	@Autowired
	private PedidoRepository repository;

	public Pedido find(Long id) {
		Optional<Pedido> Pedido = repository.findById(id);
		return Pedido.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! ID: " + id + ", Tipo: " + Pedido.class.getName()));
	}
}
