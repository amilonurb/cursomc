package br.com.brlima.cursomc.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.pedido.Pedido;
import br.com.brlima.cursomc.service.PedidoService;

@RestController
@RequestMapping("/pedidos")
public class PedidoRest {

    @Autowired
    private PedidoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Pedido> find(@PathVariable("id") Long id) {
        Pedido pedido = service.find(id);
        return ResponseEntity.ok().body(pedido);
    }
}
