package net.ebserh.hctm.model.utineonatalpediatrica;

public enum ClassificacaoPeso {

    MAIOR_OU_IGUAL_A_1500("Maior ou Igual a 1.500g"),
    MENOR_QUE_1500("Menor que 1.500g");

    private final String descricao;

    ClassificacaoPeso(String descricao) {
        this.descricao = descricao;
    }
}
