package net.ebserh.hctm.dto.enfermagem;

public class EscalaBradenDto {
    
    private Integer percepcaoSensorial = 0;

    private Integer atividade = 0;

    private Integer umidade = 0;

    private Integer mobilidade = 0;

    private Integer nutricao = 0;

    private Integer friccao = 0;

    private Integer total = 0;

    public Integer calcScore() {
        total = percepcaoSensorial + atividade + umidade + mobilidade + nutricao + friccao;
        return total;
    }

    public String validar() {

        if (percepcaoSensorial == null) {
            return "Por favor selecione uma das opções de Percepção Sensorial.";
        }
        if (atividade == null) {
            return "Por favor selecione uma das opções de Atividade.";
        }
        if (umidade == null) {
            return "Por favor selecione uma das opções de Umidade.";
        }
        if (mobilidade == null) {
            return "Por favor selecione uma das opções de Mobilidade.";
        }
        if (nutricao == null) {
            return "Por favor selecione uma das opções de Nutrição.";
        }
        if (friccao == null) {
            return "Por favor selecione uma das opções de Fricção e Cisalhamento.";
        }
        return "";
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPercepcaoSensorial() {
        return this.percepcaoSensorial;
    }

    public void setPercepcaoSensorial(Integer percepcaoSensorial) {
        this.percepcaoSensorial = percepcaoSensorial;
    }

    public Integer getAtividade() {
        return atividade;
    }

    public void setAtividade(Integer atividade) {
        this.atividade = atividade;
    }

    public Integer getUmidade() {
        return this.umidade;
    }

    public void setUmidade(Integer umidade) {
        this.umidade = umidade;
    }

    public Integer getMobilidade() {
        return this.mobilidade;
    }

    public void setMobilidade(Integer mobilidade) {
        this.mobilidade = mobilidade;
    }

    public Integer getNutricao() {
        return this.nutricao;
    }

    public void setNutricao(Integer nutricao) {
        this.nutricao = nutricao;
    }

    public Integer getFriccao() {
        return this.friccao;
    }

    public void setFriccao(Integer friccao) {
        this.friccao = friccao;
    }

}
