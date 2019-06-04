package br.com.brlima.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.Categoria;
import br.com.brlima.cursomc.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository repository;

	public Categoria buscar(Long id) {
		Optional<Categoria> categoria = repository.findById(id);
		return categoria.orElse(null);
	}

}
