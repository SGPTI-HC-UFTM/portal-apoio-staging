package net.ebserh.hctm.model.utineonatalpediatrica;

public enum ClassificacaoIdadeGestacional {

    MAIOR_OU_IGUAL_A_32_SEMANAS("Maior ou Igual a 32 semanas"),
    MENOR_QUE_32_SEMANAS("Menor que 32 semanas");

    private final String descricao;

    ClassificacaoIdadeGestacional(String descricao) {
        this.descricao = descricao;
    }
}
