package br.com.brlima.cursomc.rest;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import br.com.brlima.cursomc.dto.CategoriaDTO;
import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.service.CategoriaService;

@RestController
@RequestMapping(value = "categorias")
public class CategoriaRest {

    @Autowired
    private CategoriaService service;

    @GetMapping("/{id}")
    public ResponseEntity<Categoria> find(@PathVariable("id") Long id) {
        Categoria categoria = service.find(id);
        return ResponseEntity.ok().body(categoria);
    }

    @GetMapping
    public ResponseEntity<List<CategoriaDTO>> findAll() {
        List<CategoriaDTO> categoriasDTO = service.findAll().stream().map(categoria -> new CategoriaDTO(categoria)).collect(Collectors.toList());
        return ResponseEntity.ok().body(categoriasDTO);
    }

    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody Categoria categoria) {
        categoria = service.insert(categoria);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(categoria.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody Categoria categoria, @PathVariable("id") Long id) {
        categoria.setId(id);
        categoria = service.update(categoria);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
    
    @GetMapping("/page")
    public ResponseEntity<Page<CategoriaDTO>> findPage(
            @RequestParam(value = "page", defaultValue="0") Integer page, 
            @RequestParam(value = "linePerPage", defaultValue="24") Integer linesPerPage, 
            @RequestParam(value = "orderBy", defaultValue="nome") String orderBy, 
            @RequestParam(value = "sortDirection", defaultValue="ASC") String sortDirection) {
        Page<CategoriaDTO> categoriasDTO = service.findPage(page, linesPerPage, orderBy, sortDirection).map(categoria -> new CategoriaDTO(categoria));
        return ResponseEntity.ok().body(categoriasDTO);
    }
}
