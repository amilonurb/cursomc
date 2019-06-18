package br.com.brlima.cursomc.model.cliente;

public enum TipoCliente {

    PESSOA_FISICA(1, "Pessoa Física"), PESSOA_JURIDICA(2, "Pessoa Jurídica");

    private Integer key;
    private String nome;

    private TipoCliente(Integer key, String nome) {
        this.key = key;
        this.nome = nome;
    }

    public Integer getKey() {
        return key;
    }

    public String getNome() {
        return nome;
    }

    public static TipoCliente toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (TipoCliente tipoCliente : TipoCliente.values()) {
            if (tipoCliente.getKey().equals(codigo)) {
                return tipoCliente;
            }
        }

        throw new IllegalArgumentException("ID inválido: " + codigo);
    }
}
