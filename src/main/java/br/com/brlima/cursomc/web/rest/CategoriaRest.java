package br.com.brlima.cursomc.web.rest;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaRest {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "REST works!";
	}
}
