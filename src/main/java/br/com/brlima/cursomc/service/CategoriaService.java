package br.com.brlima.cursomc.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.produto.Categoria;
import br.com.brlima.cursomc.repository.CategoriaRepository;
import br.com.brlima.cursomc.service.exception.DataIntegrityException;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class CategoriaService {

    @Autowired
    private CategoriaRepository repository;

    public Categoria find(Long id) {
        Optional<Categoria> categoria = this.repository.findById(id);
        return categoria.orElseThrow(() -> new ObjectNotFoundException(String.format("Objeto não encontrado! ID: %s, Tipo: %s", id, Categoria.class.getSimpleName())));
    }

    public Categoria insert(Categoria categoria) {
        categoria.setId(null);
        return this.repository.save(categoria);
    }

    public Categoria update(Categoria categoria) {
        this.find(categoria.getId());
        return this.repository.save(categoria);
    }

    public void delete(Long id) {
        this.find(id);
        try {
            this.repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir uma categoria que possui produtos");
        }
    }
}
