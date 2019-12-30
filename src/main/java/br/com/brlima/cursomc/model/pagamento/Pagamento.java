package br.com.brlima.cursomc.model.pagamento;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.brlima.cursomc.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public abstract class Pagamento implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @EqualsAndHashCode.Include
    private Long id;

    private Integer codigoEstadoPagamento;

    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "pedido_id")
    @MapsId
    private Pedido pedido;

    public Pagamento(Long id, EstadoPagamento estadoPagamento, Pedido pedido) {
        this.id = id;
        this.codigoEstadoPagamento = (estadoPagamento == null) ? null : estadoPagamento.getKey();
        this.pedido = pedido;
    }

    public EstadoPagamento getEstadoPagamento() {
        return EstadoPagamento.toEnum(this.codigoEstadoPagamento);
    }

    public void setEstadoPagamento(EstadoPagamento estadoPagamento) {
        this.codigoEstadoPagamento = estadoPagamento.getKey();
    }
}
