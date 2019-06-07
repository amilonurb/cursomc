package br.com.brlima.cursomc.model;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoCartao extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Integer numeroParcelas;

	public PagamentoCartao(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Integer numeroParcelas) {
		super(id, estadoPagamento, pedido);
		this.numeroParcelas = numeroParcelas;
	}
}
