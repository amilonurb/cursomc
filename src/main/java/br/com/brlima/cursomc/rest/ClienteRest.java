package br.com.brlima.cursomc.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.cliente.dto.ClienteDTO;
import br.com.brlima.cursomc.model.cliente.dto.ClienteNewDTO;
import br.com.brlima.cursomc.service.ClienteService;

@RestController
@RequestMapping(value = "clientes")
public class ClienteRest {

    @Autowired
    private ClienteService service;

    @GetMapping("/{id}")
    public ResponseEntity<Cliente> find(@PathVariable("id") Long id) {
        Cliente cliente = service.find(id);
        return ResponseEntity.ok().body(cliente);
    }

    @GetMapping
    public ResponseEntity<List<ClienteDTO>> findAll() {
        List<ClienteDTO> clientesDTO = service.findAll().stream().map(cliente -> new ClienteDTO(cliente)).collect(Collectors.toList());
        return ResponseEntity.ok().body(clientesDTO);
    }

    /*@PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteDTO clienteDTO) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }*/

    @PostMapping
    public ResponseEntity<Void> insert(@Valid @RequestBody ClienteNewDTO clienteDTO) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente = service.insert(cliente);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(cliente.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@Valid @RequestBody ClienteDTO clienteDTO, @PathVariable("id") Long id) {
        Cliente cliente = service.fromDTO(clienteDTO);
        cliente.setId(id);
        cliente = service.update(cliente);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ClienteDTO>> findPage(//
            @RequestParam(value = "page", defaultValue = "0") Integer page, //
            @RequestParam(value = "linePerPage", defaultValue = "24") Integer linesPerPage, //
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy, //
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection) {
        Page<ClienteDTO> clientesDTO = service.findPage(page, linesPerPage, orderBy, sortDirection).map(cliente -> new ClienteDTO(cliente));
        return ResponseEntity.ok().body(clientesDTO);
    }
}
