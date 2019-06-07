package br.com.brlima.cursomc.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido implements Serializable {

	private static final long serialVersionUID = 1L;

	@EmbeddedId
	@EqualsAndHashCode.Include
	@JsonIgnore
	private ItemPedidoPK id = new ItemPedidoPK();

	private Integer quantidade;
	private BigDecimal desconto;
	private BigDecimal preco;

	public ItemPedido(Pedido pedido, Produto produto, Integer quantidade, BigDecimal desconto, BigDecimal preco) {
		this.id.setPedido(pedido);
		this.id.setProduto(produto);
		this.quantidade = quantidade;
		this.desconto = desconto;
		this.preco = preco;
	}

	@JsonIgnore
	public Pedido getPedido() {
		return this.id.getPedido();
	}

	public Produto getProduto() {
		return this.id.getProduto();
	}
}
