package br.com.brlima.cursomc.model.pagamento;

import java.time.LocalDate;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
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
@JsonTypeName("pagamentoBoleto")
public class PagamentoBoleto extends Pagamento {

    private static final long serialVersionUID = 1L;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataPagamento;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate dataVencimento;

    public PagamentoBoleto(Long id, EstadoPagamento estadoPagamento, Pedido pedido, LocalDate dataPagamento, LocalDate dataVencimento) {
        super(id, estadoPagamento, pedido);
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
    }
}
