package net.ebserh.hctm.dto.enfermagem;

public class EscalaGlasgowDto {

    private Integer aberturaOcular = 0;

    private Integer respostaVerbal = 0;

    private Integer respostaMotora = 0;

    private Integer total = 0;

    public Integer calcScore() {
        total = aberturaOcular + respostaVerbal + respostaMotora;
        return total;
    }

    public String validar() {

        if (aberturaOcular == null) {
            return "Por favor selecione uma das opções de Abertura Ocular.";
        }
        if (respostaVerbal == null) {
            return "Por favor selecione uma das opções de Resposta Verbal.";
        }
        if (respostaMotora == null) {
            return "Por favor selecione uma das opções de Resposta Motora.";
        }
        return "";
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getAberturaOcular() {
        return this.aberturaOcular;
    }

    public void setAberturaOcular(Integer aberturaOcular) {
        this.aberturaOcular = aberturaOcular;
    }

    public Integer getRespostaVerbal() {
        return this.respostaVerbal;
    }

    public void setRespostaVerbal(Integer respostaVerbal) {
        this.respostaVerbal = respostaVerbal;
    }

    public Integer getRespostaMotora() {
        return this.respostaMotora;
    }

    public void setRespostaMotora(Integer respostaMotora) {
        this.respostaMotora = respostaMotora;
    }

    public String getRespostaEscrita() {
        String resposta = "";

        if (this.total >= 13 && this.total <= 15) {
            resposta = "trauma leve";
        }
        if (this.total >= 9 && this.total <= 12) {
            resposta = "trauma moderado";
        }
        if (this.total >= 3 && this.total <= 8) {
            resposta = "trauma grave";
        }

        return resposta;
    }

}
