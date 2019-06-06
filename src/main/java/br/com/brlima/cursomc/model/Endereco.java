package br.com.brlima.cursomc.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
public class Endereco implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@EqualsAndHashCode.Include
	private Long id;
	private String logradouro;
	private String numero;
	private String complemento;
	private String bairro;
	private String cep;

	@ManyToOne
	@JoinColumn(name = "cidade_id")
	private Cidade cidade;

	@ManyToOne
	@JoinColumn(name = "cliente_id")
	private Cliente cliente;

	public Endereco(Long id, String logradouro, String numero, String complemento, String bairro, String cep, Cidade cidade, Cliente cliente) {
		this.id = id;
		this.logradouro = logradouro;
		this.numero = numero;
		this.complemento = complemento;
		this.bairro = bairro;
		this.cep = cep;
		this.cidade = cidade;
		this.cliente = cliente;
	}

}
