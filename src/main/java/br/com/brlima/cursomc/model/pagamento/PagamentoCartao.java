package br.com.brlima.cursomc.model.pagamento;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonTypeName;

import br.com.brlima.cursomc.model.enums.EstadoPagamento;
import br.com.brlima.cursomc.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@JsonTypeName("pagamentoCartao")
public class PagamentoCartao extends Pagamento {

    private static final long serialVersionUID = 1L;

    private Integer numeroParcelas;

    public PagamentoCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
        super(id, estadoPagamento, pedido);
        this.numeroParcelas = numeroParcelas;
    }
}
