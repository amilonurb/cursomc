package br.com.brlima.cursomc.model.produto.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import br.com.brlima.cursomc.model.produto.Produto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class ProdutoDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    private Long id;
    private String nome;
    private BigDecimal preco;

    public ProdutoDTO(Produto produto) {
        this.id = produto.getId();
        this.nome = produto.getNome();
        this.preco = produto.getPreco();
    }
}
