package br.com.brlima.cursomc.model.pedido;

import java.io.Serializable;
import java.math.BigDecimal;
import java.text.NumberFormat;
import java.util.Locale;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.brlima.cursomc.model.produto.Produto;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class ItemPedido implements Serializable {

    private static final long serialVersionUID = 1L;

    @JsonIgnore
    @EmbeddedId
    @EqualsAndHashCode.Include
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

    public void setPedido(Pedido pedido) {
        this.id.setPedido(pedido);
    }

    public Produto getProduto() {
        return this.id.getProduto();
    }

    public void setProduto(Produto produto) {
        this.id.setProduto(produto);
    }

    public BigDecimal getSubtotal() {
        // quantidade * (preco - desconto)
        return BigDecimal.valueOf(this.quantidade).multiply(this.preco.subtract(this.desconto));
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        final NumberFormat nf = NumberFormat.getCurrencyInstance(new Locale("pt", "BR"));

        builder.append("Produto: ");
        builder.append(this.getProduto().getNome());
        
        builder.append("; Quantidade: ");
        builder.append(this.getQuantidade());
        
        builder.append("; Preço unitário: ");
        builder.append(nf.format(this.getPreco()));
        
        builder.append("; Subtotal: ");
        builder.append(nf.format(this.getSubtotal()));
        
        builder.append("\n");
        
        return builder.toString();
    }
}
