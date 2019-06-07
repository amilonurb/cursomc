package br.com.brlima.cursomc.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonManagedReference;

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
	private String email;
	private String cpfOuCnpj;
	private Integer codigoTipoCliente;

	@JsonManagedReference
	@OneToMany(mappedBy = "cliente")
	private List<Endereco> enderecos = new ArrayList<>();

	@ElementCollection
	@CollectionTable(name = "TELEFONE")
	private Set<String> telefones = new HashSet<>();
	
	@OneToMany(mappedBy = "cliente")
	private List<Pedido> pedidos = new ArrayList<>();

	public Cliente(Long id, String nome, String email, String cpfOuCnpj, TipoCliente tipoCliente) {
		this.id = id;
		this.nome = nome;
		this.email = email;
		this.cpfOuCnpj = cpfOuCnpj;
		this.codigoTipoCliente = tipoCliente.getKey();
	}

	public TipoCliente getTipoCliente() {
		return TipoCliente.toEnum(this.codigoTipoCliente);
	}

	public void setTipoCliente(TipoCliente tipoCliente) {
		this.codigoTipoCliente = tipoCliente.getKey();
	}
}
