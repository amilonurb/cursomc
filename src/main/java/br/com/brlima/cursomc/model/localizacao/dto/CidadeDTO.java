package br.com.brlima.cursomc.model.localizacao.dto;

import java.io.Serializable;

import br.com.brlima.cursomc.model.localizacao.Cidade;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class CidadeDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;

    public CidadeDTO(Cidade cidade) {
        this.id = cidade.getId();
        this.nome = cidade.getNome();
    }
}
