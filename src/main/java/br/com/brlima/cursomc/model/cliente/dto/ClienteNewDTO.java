package br.com.brlima.cursomc.model.cliente.dto;

import java.io.Serializable;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Representa o objeto de criação de um Cliente
 * -> Dados do Cliente + Telefone(s) + Endereço(s)
 * @author brlima
 *
 */
@NoArgsConstructor
@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    
    /*
     * Cliente
     */
    private String nome;
    private String email;
    private String cpfOuCnpj;
    private Integer codigoTipoCliente;

    /*
     * Endereço
     */
    private String logradouro;
    private String numero;
    private String complemento;
    private String bairro;
    private String cep;
    
    /**
     * Telefones
     */
    private String telefoneObrigatorio;
    private String telefoneOpcional1;
    private String telefoneOpcional2;
    
    private Long cidadeId;
}
