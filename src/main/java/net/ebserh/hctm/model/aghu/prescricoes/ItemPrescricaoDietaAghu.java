package net.ebserh.hctm.model.aghu.prescricoes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.nutricao.TipoItemDieta;

@Entity
@Table(schema = "agh", name = "mpm_item_prescricao_dietas")
public class ItemPrescricaoDietaAghu implements Serializable {
    
    @EmbeddedId
    private ItemPrescricaoDietaPK id;

    private Double quantidade;

    private Integer frequencia;

    @Column(name = "duracao_solicitada")
    private Integer duracaoSolicitada;

    @Column(name = "num_vezes")
    private Integer numVezes;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "pdt_atd_seq", referencedColumnName = "atd_seq", insertable = false, updatable = false),
        @JoinColumn(name = "pdt_seq", referencedColumnName = "seq", insertable = false, updatable = false)
    }) 
    private PrescricaoDietaAghu prescricaoDieta;

    @ManyToOne
    @JoinColumn(name = "tfq_seq")
    private TipoFrequenciaAprazamento tipoFrequenciaAprazamento;

    @ManyToOne
    @JoinColumn(name = "tid_seq", insertable = false, updatable = false)
    private TipoItemDieta tipoItemDieta;

    @Override
    public String toString() {
        return "ItemPrescricaoDieta [id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ItemPrescricaoDietaAghu other = (ItemPrescricaoDietaAghu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public ItemPrescricaoDietaPK getId() {
        return id;
    }

    public void setId(ItemPrescricaoDietaPK id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Integer getFrequencia() {
        return frequencia;
    }

    public void setFrequencia(Integer frequencia) {
        this.frequencia = frequencia;
    }

    public Integer getDuracaoSolicitada() {
        return duracaoSolicitada;
    }

    public void setDuracaoSolicitada(Integer duracaoSolicitada) {
        this.duracaoSolicitada = duracaoSolicitada;
    }

    public Integer getNumVezes() {
        return numVezes;
    }

    public void setNumVezes(Integer numVezes) {
        this.numVezes = numVezes;
    }

    public PrescricaoDietaAghu getPrescricaoDieta() {
        return prescricaoDieta;
    }

    public void setPrescricaoDieta(PrescricaoDietaAghu prescricaoDieta) {
        this.prescricaoDieta = prescricaoDieta;
    }

    public TipoFrequenciaAprazamento getTipoFrequenciaAprazamento() {
        return tipoFrequenciaAprazamento;
    }

    public void setTipoFrequenciaAprazamento(TipoFrequenciaAprazamento tipoFrequenciaAprazamento) {
        this.tipoFrequenciaAprazamento = tipoFrequenciaAprazamento;
    }

    public TipoItemDieta getTipoItemDieta() {
        return tipoItemDieta;
    }

    public void setTipoItemDieta(TipoItemDieta tipoItemDieta) {
        this.tipoItemDieta = tipoItemDieta;
    }

}
