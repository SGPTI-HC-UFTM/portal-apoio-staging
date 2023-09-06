package net.ebserh.hctm.dto.aghu.suprimentos;

import java.math.BigDecimal;

public class EstoqueAlmoxarifadoDto {

    private String grupo;

    private Integer codMaterial;

    private String material;

    private Integer codAlmoxarifado;

    private Integer quantidadeGeral;

    private Integer quantidadeAlmoxarifado;

    private BigDecimal mediaMensal;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public Integer getCodMaterial() {
        return codMaterial;
    }

    public void setCodMaterial(Integer codMaterial) {
        this.codMaterial = codMaterial;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public Integer getCodAlmoxarifado() {
        return codAlmoxarifado;
    }

    public void setCodAlmoxarifado(Integer codAlmoxarifado) {
        this.codAlmoxarifado = codAlmoxarifado;
    }

    public Integer getQuantidadeGeral() {
        return quantidadeGeral;
    }

    public void setQuantidadeGeral(Integer quantidadeGeral) {
        this.quantidadeGeral = quantidadeGeral;
    }

    public Integer getQuantidadeAlmoxarifado() {
        return quantidadeAlmoxarifado;
    }

    public void setQuantidadeAlmoxarifado(Integer quantidadeAlmoxarifado) {
        this.quantidadeAlmoxarifado = quantidadeAlmoxarifado;
    }

    public BigDecimal getMediaMensal() {
        return mediaMensal;
    }

    public void setMediaMensal(BigDecimal mediaMensal) {
        this.mediaMensal = mediaMensal;
    }
    
}
