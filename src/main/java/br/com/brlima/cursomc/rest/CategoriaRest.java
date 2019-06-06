package br.com.brlima.cursomc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.Categoria;
import br.com.brlima.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaRest {

	@Autowired
	private CategoriaService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Categoria> find(@PathVariable("id") Long id) {
		Categoria categoria = service.find(id);
		return ResponseEntity.ok().body(categoria);
	}
}