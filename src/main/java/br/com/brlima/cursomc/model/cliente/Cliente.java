package br.com.brlima.cursomc.model.cliente;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import br.com.brlima.cursomc.model.enums.Perfil;
import br.com.brlima.cursomc.model.enums.TipoCliente;
import br.com.brlima.cursomc.model.localizacao.Endereco;
import br.com.brlima.cursomc.model.pedido.Pedido;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Data
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

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "PERFIL")
    private Set<Integer> perfis = new HashSet<>();

    @OneToMany(mappedBy = "cliente", cascade = CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "TELEFONE")
    private Set<String> telefones = new HashSet<>();

    @JsonIgnore
    @OneToMany(mappedBy = "cliente")
    private List<Pedido> pedidos = new ArrayList<>();

    public Cliente() {
        // Todo cliente, por padrão, terá este perfil
        this.addPerfil(Perfil.CLIENTE);
    }

    public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente, String password) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.cpfOuCnpj = cpfOuCnpj;
        this.codigoTipoCliente = (tipoCliente == null) ? null : tipoCliente.getKey();
        this.password = password;

        this.addPerfil(Perfil.CLIENTE);
    }

    public Set<Perfil> getPerfis() {
        return this.perfis.stream().map(Perfil::toEnum).collect(Collectors.toSet());
    }

    public void addPerfil(Perfil perfil) {
        this.perfis.add(perfil.getKey());
    }

    public TipoCliente getTipoCliente() {
        return TipoCliente.toEnum(this.codigoTipoCliente);
    }

    public void setTipoCliente(TipoCliente tipoCliente) {
        this.codigoTipoCliente = tipoCliente.getKey();
    }
}
