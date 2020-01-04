package br.com.brlima.cursomc.rest;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.brlima.cursomc.model.localizacao.dto.CidadeDTO;
import br.com.brlima.cursomc.model.localizacao.dto.EstadoDTO;
import br.com.brlima.cursomc.service.CidadeService;
import br.com.brlima.cursomc.service.EstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoRest {

    @Autowired
    private EstadoService service;

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public ResponseEntity<List<EstadoDTO>> findAll() {
        List<EstadoDTO> estadosDTO = service.findAll().stream().map(estado -> new EstadoDTO(estado)).collect(Collectors.toList());
        return ResponseEntity.ok().body(estadosDTO);
    }

    @GetMapping("/{estadoId}/cidades")
    public ResponseEntity<List<CidadeDTO>> findCidades(@PathVariable Long estadoId) {
        List<CidadeDTO> cidadesDTO = cidadeService.findByEstado(estadoId).stream().map(cidade -> new CidadeDTO(cidade)).collect(Collectors.toList());
        return ResponseEntity.ok().body(cidadesDTO);
    }
}
