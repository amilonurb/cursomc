package br.com.brlima.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.model.produto.Produto;
import br.com.brlima.cursomc.repository.CategoriaRepository;
import br.com.brlima.cursomc.repository.ProdutoRepository;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Produto find(Long id) {
        Optional<Produto> produto = this.repository.findById(id);
        return produto.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto não encontrado! ID: %s, Tipo: %s", id, Produto.class.getSimpleName())));
    }

    public Page<Produto> search(String nome, List<Long> ids, Integer page, Integer linesPerPage, String orderBy, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(sortDirection), orderBy);
        List<Categoria> categorias = categoriaRepository.findAllById(ids);
        return repository.findDistinctByNomeContainingAndCategoriasIn(nome, categorias, pageRequest);
    }
}
