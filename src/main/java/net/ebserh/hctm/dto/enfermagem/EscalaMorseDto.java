package net.ebserh.hctm.dto.enfermagem;

public class EscalaMorseDto {
    

    private Integer historicoQueda;

    private Integer diagSecundario;

    private Integer auxilioDeambular;

    private Integer terapiaEndovenosa;

    private Integer marcha;

    private Integer estadoMental;

    private Integer total = 0;

    public Integer calcScore() {
        total = historicoQueda + diagSecundario + auxilioDeambular + terapiaEndovenosa + marcha + estadoMental;
        return total;
    }

    public String validar() {

        if (historicoQueda == null) {
            return "Por favor selecione uma das opções de histórico de queda recente.";
        }
        if (diagSecundario == null) {
            return "Por favor selecione uma das opções de diagnóstico secundário.";
        }
        if (auxilioDeambular == null) {
            return "Por favor selecione uma das opções de auxílio deambular.";
        }
        if (terapiaEndovenosa == null) {
            return "Por favor selecione uma das opções de terapia endovenosa";
        }
        if (marcha == null) {
            return "Por favor selecione uma das opções de marcha.";
        }
        if (estadoMental == null) {
            return "Por favor selecione uma das opções de estado mental.";
        }
        return "";
    }

    public Integer getHistoricoQueda() {
        return this.historicoQueda;
    }

    public void setHistoricoQueda(Integer historicoQueda) {
        this.historicoQueda = historicoQueda;
    }

    public Integer getDiagSecundario() {
        return this.diagSecundario;
    }

    public void setDiagSecundario(Integer diagSecundario) {
        this.diagSecundario = diagSecundario;
    }

    public Integer getAuxilioDeambular() {
        return this.auxilioDeambular;
    }

    public void setAuxilioDeambular(Integer auxilioDeambular) {
        this.auxilioDeambular = auxilioDeambular;
    }

    public Integer getTerapiaEndovenosa() {
        return this.terapiaEndovenosa;
    }

    public void setTerapiaEndovenosa(Integer terapiaEndovenosa) {
        this.terapiaEndovenosa = terapiaEndovenosa;
    }

    public Integer getMarcha() {
        return this.marcha;
    }

    public void setMarcha(Integer marcha) {
        this.marcha = marcha;
    }

    public Integer getEstadoMental() {
        return this.estadoMental;
    }

    public void setEstadoMental(Integer estadoMental) {
        this.estadoMental = estadoMental;
    }

    public Integer getTotal() {
        return this.total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

}
