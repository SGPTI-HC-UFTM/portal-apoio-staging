package net.ebserh.hctm.dto.aghu.indicadores;

public class PrescricoesEletronicasManuaisDto {

    private String siglaUnidade;

    private Integer prescricoesEletronicas;

    private Integer prescricoesManuais;
    
    public PrescricoesEletronicasManuaisDto(String siglaUnidade, Integer prescricoesEletronicas,
            Integer prescricoesManuais) {
        this.siglaUnidade = siglaUnidade;
        this.prescricoesEletronicas = prescricoesEletronicas;
        this.prescricoesManuais = prescricoesManuais;
    }

    public String getSiglaUnidade() {
        return siglaUnidade;
    }

    public void setSiglaUnidade(String siglaUnidade) {
        this.siglaUnidade = siglaUnidade;
    }

    public Integer getPrescricoesEletronicas() {
        return prescricoesEletronicas;
    }

    public void setPrescricoesEletronicas(Integer prescricoesEletronicas) {
        this.prescricoesEletronicas = prescricoesEletronicas;
    }

    public Integer getPrescricoesManuais() {
        return prescricoesManuais;
    }

    public void setPrescricoesManuais(Integer prescricoesManuais) {
        this.prescricoesManuais = prescricoesManuais;
    }
    
}
