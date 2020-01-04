package br.com.brlima.cursomc.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.brlima.cursomc.model.localizacao.Cidade;
import br.com.brlima.cursomc.repository.CidadeRepository;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> findByEstado(Long estadoId) {
        return cidadeRepository.findCidades(estadoId);
    }
}
