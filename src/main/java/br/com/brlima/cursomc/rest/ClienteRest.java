package br.com.brlima.cursomc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.Cliente;
import br.com.brlima.cursomc.service.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteRest {

	@Autowired
	private ClienteService service;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Cliente> find(@PathVariable("id") Long id) {
		Cliente cliente = service.find(id);
		return ResponseEntity.ok().body(cliente);
	}
}
