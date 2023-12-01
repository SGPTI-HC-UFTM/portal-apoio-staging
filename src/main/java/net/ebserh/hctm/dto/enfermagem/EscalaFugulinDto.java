package net.ebserh.hctm.dto.enfermagem;

public class EscalaFugulinDto {

    private Integer estadoMental = 0;

    private Integer oxigenacao = 0;

    private Integer sinaisVitais = 0;

    private Integer motilidade = 0;

    private Integer deambulacao = 0;

    private Integer alimentacao = 0;

    private Integer cuidadoCorporal = 0;

    private Integer eliminacao = 0;

    private Integer terapeutica = 0;

    private Integer total = 0;

    public Integer calcScore() {

        total = estadoMental + oxigenacao + sinaisVitais + motilidade + deambulacao + alimentacao + cuidadoCorporal
                + eliminacao + terapeutica;

        return total;
    }

    public String validar() {

        if (estadoMental == null) {
            return "Por favor selecione uma das opções da área de cuidado: Estado Mental.";
        }
        if (oxigenacao == null) {
            return "Por favor selecione uma das opções da área de cuidado: Oxigenação.";
        }
        if (sinaisVitais == null) {
            return "Por favor selecione uma das opções da área de cuidado: Sinais Vitais.";
        }
        if (motilidade == null) {
            return "Por favor selecione uma das opções da área de cuidado: Motilidade.";
        }
        if (deambulacao == null) {
            return "Por favor selecione uma das opções da área de cuidado: Deambulação.";
        }
        if (alimentacao == null) {
            return "Por favor selecione uma das opções da área de cuidado: Alimentação.";
        }
        if (cuidadoCorporal == null) {
            return "Por favor selecione uma das opções da área de cuidado: Cuidado Corporal.";
        }
        if (eliminacao == null) {
            return "Por favor selecione uma das opções da área de cuidado: Eliminação.";
        }
        if (terapeutica == null) {
            return "Por favor selecione uma das opções da área de cuidado: Terapêutica.";
        }
        return "";
    }

    public Integer getEstadoMental() {
        return this.estadoMental;
    }

    public void setEstadoMental(Integer estadoMental) {
        this.estadoMental = estadoMental;
    }

    public Integer getOxigenacao() {
        return this.oxigenacao;
    }

    public void setOxigenacao(Integer oxigenacao) {
        this.oxigenacao = oxigenacao;
    }

    public Integer getSinaisVitais() {
        return this.sinaisVitais;
    }

    public void setSinaisVitais(Integer sinaisVitais) {
        this.sinaisVitais = sinaisVitais;
    }

    public Integer getMotilidade() {
        return this.motilidade;
    }

    public void setMotilidade(Integer motilidade) {
        this.motilidade = motilidade;
    }

    public Integer getDeambulacao() {
        return this.deambulacao;
    }

    public void setDeambulacao(Integer deambulacao) {
        this.deambulacao = deambulacao;
    }

    public Integer getAlimentacao() {
        return this.alimentacao;
    }

    public void setAlimentacao(Integer alimentacao) {
        this.alimentacao = alimentacao;
    }

    public Integer getCuidadoCorporal() {
        return this.cuidadoCorporal;
    }

    public void setCuidadoCorporal(Integer cuidadoCorporal) {
        this.cuidadoCorporal = cuidadoCorporal;
    }

    public Integer getEliminacao() {
        return this.eliminacao;
    }

    public void setEliminacao(Integer eliminacao) {
        this.eliminacao = eliminacao;
    }

    public Integer getTerapeutica() {
        return this.terapeutica;
    }

    public void setTerapeutica(Integer terapeutica) {
        this.terapeutica = terapeutica;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public String getRespostaEscrita() {
        String resposta = "";

        if (this.total < 17) {
            resposta = "mínimo (12-17)";
        }
        if (this.total >= 18 && this.total <= 22) {
            resposta = "intermediário (18-22)";
        }
        if (this.total >= 23 && this.total <= 28) {
            resposta = "alta dependência (23-28)";
        }
        if (this.total >= 29 && this.total <= 34) {
            resposta = "semi-intensivo (29-34)";
        }
        if (this.total > 34) {
            resposta = "intensivo (Acima de 34)";
        }

        return resposta;
    }

}
