package net.ebserh.hctm.dto.aghu.internacoes;

import java.io.Serializable;

public class InternacoesCidDto implements Serializable {
    
    private String cid;

    private Integer internacoes;

    private Double percentual;

    public String getCid() {
        return cid;
    }

    public void setCid(String cid) {
        this.cid = cid;
    }

    public Integer getInternacoes() {
        return internacoes;
    }

    public void setInternacoes(Integer internacoes) {
        this.internacoes = internacoes;
    }

    public Double getPercentual() {
        return percentual;
    }

    public void setPercentual(Double percentual) {
        this.percentual = percentual;
    }

}
