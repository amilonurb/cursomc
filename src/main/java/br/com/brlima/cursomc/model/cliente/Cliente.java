package br.com.brlima.cursomc.model.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.brlima.cursomc.model.enums.TipoCliente;
import br.com.brlima.cursomc.model.localizacao.Endereco;
import br.com.brlima.cursomc.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Cliente implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private String cpfOuCnpj;

    private Integer codigoTipoCliente;

    @JsonIgnore
    private String password;

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.codigoTipoCliente = (tipoCliente == null) ? null : tipoCliente.getKey();
        this.password = password;
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(this.codigoTipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.codigoTipoCliente = tipoCliente.getKey();
    }
}
