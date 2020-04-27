package br.com.brlima.cursomc.model.enums;

public enum EstadoPagamento {

    PENDENTE(1, "Pendente"),
    QUITADO(2, "Quitado"),
    CANCELADO(3, "Cancelado");

    private Integer key;
    private String nome;

    private EstadoPagamento(Integer key, String nome) {
        this.key = key;
        this.nome = nome;
    }

    public Integer getKey() {
        return key;
    }

    public String getNome() {
        return nome;
    }

    public static EstadoPagamento toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (EstadoPagamento tipoCliente : EstadoPagamento.values()) {
            if (tipoCliente.getKey().equals(codigo)) {
                return tipoCliente;
            }
        }

        throw new IllegalArgumentException("ID inválido: " + codigo);
    }
}
