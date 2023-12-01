package net.ebserh.hctm.dto.enfermagem;

public class EscalaRassDto {

    private String classificacao;

    public String validar() {

        if (classificacao == null) {
            return "Por favor selecione uma das opçõe.";
        }
        return "";
    }

    public String getClassificacao() {
        return this.classificacao;
    }

    public void setClassificacao(String classificacao) {
        this.classificacao = classificacao;
    }
}
