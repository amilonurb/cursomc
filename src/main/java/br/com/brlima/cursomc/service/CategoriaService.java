package br.com.brlima.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.repository.CategoriaRepository;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria find(Long id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Categoria.class.getName()));
	}

	public Categoria insert(Categoria categoria) {
		categoria.setId(null);
		return repository.save(categoria);
	}

	public Categoria update(Categoria categoria) {
		this.find(categoria.getId());
		return repository.save(categoria);
	}
}
