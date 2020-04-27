package br.com.brlima.cursomc.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.produto.Produto;
import br.com.brlima.cursomc.model.produto.dto.ProdutoDTO;
import br.com.brlima.cursomc.service.ProdutoService;
import br.com.brlima.cursomc.util.URLUtils;

@RestController
@RequestMapping("/produtos")
public class ProdutoRest {

    @Autowired
    private ProdutoService service;

    @GetMapping("/{id}")
    public ResponseEntity<Produto> find(@PathVariable("id") Long id) {
        Produto produto = service.find(id);
        return ResponseEntity.ok().body(produto);
    }

    @GetMapping("/page")
    public ResponseEntity<Page<ProdutoDTO>> findPage(
            @RequestParam(value = "nome", defaultValue = "") String nome,
            @RequestParam(value = "categorias", defaultValue = "") String categorias,
            @RequestParam(value = "page", defaultValue = "0") Integer page,
            @RequestParam(value = "linePerPage", defaultValue = "24") Integer linesPerPage,
            @RequestParam(value = "orderBy", defaultValue = "nome") String orderBy,
            @RequestParam(value = "sortDirection", defaultValue = "ASC") String sortDirection) {

        String nomeDecoded = URLUtils.decodeParam(nome);
        List<Long> ids = URLUtils.decodeLongList(categorias);
        Page<ProdutoDTO> produtosDTO = service.search(nomeDecoded, ids, page, linesPerPage, orderBy, sortDirection).map(ProdutoDTO::new);

        return ResponseEntity.ok().body(produtosDTO);
    }
}
