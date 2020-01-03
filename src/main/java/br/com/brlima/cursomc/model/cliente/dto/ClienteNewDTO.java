package br.com.brlima.cursomc.model.cliente.dto;

import java.io.Serializable;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

import br.com.brlima.cursomc.service.validation.ClienteInsert;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@ClienteInsert
@NoArgsConstructor
@Getter
@Setter
public class ClienteNewDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /*
     * Cliente
     */
    @NotEmpty(message = "Preenchimento obrigatório")
    @Length(min = 5, message = "O nome deve ter pelo menos 5 letrass")
    private String nome;

    @NotEmpty(message = "Preenchimento obrigatório")
    @Email(message = "E-mail inválido")
    private String email;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String password;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cpfOuCnpj;

    private Integer codigoTipoCliente;

    /*
     * Endereço
     */
    @NotEmpty(message = "Preenchimento obrigatório")
    private String logradouro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String numero;

    private String complemento;
    private String bairro;

    @NotEmpty(message = "Preenchimento obrigatório")
    private String cep;

    /**
     * Telefones
     */
    @NotEmpty(message = "Preenchimento obrigatório")
    private String telefoneObrigatorio;

    private String telefoneOpcional1;
    private String telefoneOpcional2;

    private Long cidadeId;
}
