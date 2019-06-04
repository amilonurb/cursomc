package br.com.brlima.cursomc.model;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@AllArgsConstructor
public class Categoria implements Serializable {
	private static final long serialVersionUID = 5009143847763774982L;
	private Long id;
	@EqualsAndHashCode.Exclude
	private String nome;
}
