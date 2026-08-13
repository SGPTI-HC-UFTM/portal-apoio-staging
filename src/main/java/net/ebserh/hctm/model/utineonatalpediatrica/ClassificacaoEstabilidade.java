package net.ebserh.hctm.model.utineonatalpediatrica;

public enum ClassificacaoEstabilidade{

    ESTAVEL("ESTAVEL"),
    INSTAVEL("INSTAVEL");

    private final String descricao;

    ClassificacaoEstabilidade(String descricao) {
        this.descricao = descricao;
    }
}
