package br.com.brlima.cursomc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.cliente.Cliente;
import br.com.brlima.cursomc.model.cliente.dto.ClienteDTO;
import br.com.brlima.cursomc.repository.ClienteRepository;
import br.com.brlima.cursomc.service.exception.DataIntegrityException;
import br.com.brlima.cursomc.service.exception.ObjectNotFoundException;

@Service
public class ClienteService {

    @Autowired
    private ClienteRepository repository;

    public Cliente find(Long id) {
        Optional<Cliente> cliente = repository.findById(id);
        return cliente.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! ID: " + id + ", Tipo: " + Cliente.class.getName()));
    }

    public List<Cliente> findAll() {
        return repository.findAll();
    }

    public Cliente insert(Cliente cliente) {
        cliente.setId(null);
        return this.repository.save(cliente);
    }

    public Cliente update(Cliente cliente) {
        Cliente clienteDB = this.find(cliente.getId());
        updateFromData(clienteDB, cliente);
        return this.repository.save(cliente);
    }

    public void delete(Long id) {
        this.find(id);
        try {
            this.repository.deleteById(id);
        } catch (DataIntegrityViolationException e) {
            throw new DataIntegrityException("Não é possível excluir um Cliente que possui entidades relacionadas");
        }
    }

    public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String sortDirection) {
        PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(sortDirection), orderBy);
        return repository.findAll(pageRequest);
    }

    public Cliente fromDTO(ClienteDTO dto) {
        return new Cliente(dto.getId(), dto.getNome(), dto.getEmail(), null, null);
    }

    private void updateFromData(Cliente current, Cliente data) {
        current.setNome(data.getNome());
        current.setEmail(data.getEmail());
    }
}
