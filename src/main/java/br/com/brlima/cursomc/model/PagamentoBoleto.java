package br.com.brlima.cursomc.model;

import java.util.Date;

import javax.persistence.Entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class PagamentoBoleto extends Pagamento {

	private static final long serialVersionUID = 1L;

	private Date dataPagamento;
	private Date dataVencimento;

	public PagamentoBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, Date dataPagamento, Date dataVencimento) {
		super(id, estadoPagamento, pedido);
		this.dataPagamento = dataPagamento;
		this.dataVencimento = dataVencimento;
	}
}
