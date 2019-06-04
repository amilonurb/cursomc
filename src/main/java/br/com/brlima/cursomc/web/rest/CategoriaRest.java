package br.com.brlima.cursomc.web.rest;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.Categoria;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaRest {

	@RequestMapping(method = RequestMethod.GET)
	public List<Categoria> listar() {
		Categoria cat1 = new Categoria(1L, "Informática");
		Categoria cat2 = new Categoria(2L, "Escritório");
		return Arrays.asList(cat1, cat2);
	}
}
