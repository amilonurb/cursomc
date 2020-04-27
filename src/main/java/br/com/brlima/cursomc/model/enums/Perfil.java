package br.com.brlima.cursomc.model.enums;

public enum Perfil {

    // Prefixo ROLE_ é por regra do Spring Security
    ADMIN(1, "ROLE_ADMIN"),
    CLIENTE(2, "ROLE_CLIENTE");

    private Integer key;
    private String nome;

    private Perfil(Integer key, String nome) {
        this.key = key;
        this.nome = nome;
    }

    public Integer getKey() {
        return key;
    }

    public String getNome() {
        return nome;
    }

    public static Perfil toEnum(Integer codigo) {
        if (codigo == null) {
            return null;
        }

        for (Perfil perfil : Perfil.values()) {
            if (perfil.getKey().equals(codigo)) {
                return perfil;
            }
        }

        throw new IllegalArgumentException("ID inválido: " + codigo);
    }
}
