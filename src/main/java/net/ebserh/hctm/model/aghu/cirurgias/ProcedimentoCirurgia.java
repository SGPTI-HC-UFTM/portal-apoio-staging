package net.ebserh.hctm.model.aghu.cirurgias;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

@Entity
@Table(schema = "agh", name = "mbc_proc_esp_por_cirurgias")
@NamedQuery(name = "ProcedimentoCirurgia.findByCirurgiaData",
    query = "SELECT p "
        + "FROM ProcedimentoCirurgia p "
        + "JOIN p.cirurgia c "
        + "WHERE FUNCTION('DATE', c.data) = :dataReferencia "
        + "AND p.responsabilidade = 'AGND' "
        + "ORDER BY c.data, p.especialidadeProcedimentoCirurgia.procedimentoCirurgico.descricao")
public class ProcedimentoCirurgia implements Serializable {
    
    @EmbeddedId
    private ProcedimentoCirurgiaId id;

    @Column(name = "ind_resp_proc")
    private String responsabilidade;

    @ManyToOne
    @JoinColumn(name = "crg_seq")
    private Cirurgia cirurgia;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "epr_pci_seq", referencedColumnName = "pci_seq"),
        @JoinColumn(name = "epr_esp_seq", referencedColumnName = "esp_seq")})
    private EspecialidadeProcedimentoCirurgia especialidadeProcedimentoCirurgia;

    @Transient
    private Boolean realizado = false;

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
        ProcedimentoCirurgia other = (ProcedimentoCirurgia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "ProcedimentoCirurgia [id=" + id + "]";
    }

    public ProcedimentoCirurgiaId getId() {
        return id;
    }

    public void setId(ProcedimentoCirurgiaId id) {
        this.id = id;
    }

    public Cirurgia getCirurgia() {
        return cirurgia;
    }

    public void setCirurgia(Cirurgia cirurgia) {
        this.cirurgia = cirurgia;
    }

    public EspecialidadeProcedimentoCirurgia getEspecialidadeProcedimentoCirurgia() {
        return especialidadeProcedimentoCirurgia;
    }

    public void setEspecialidadeProcedimentoCirurgia(EspecialidadeProcedimentoCirurgia especialidadeProcedimentoCirurgia) {
        this.especialidadeProcedimentoCirurgia = especialidadeProcedimentoCirurgia;
    }

    public String getResponsabilidade() {
        return responsabilidade;
    }

    public void setResponsabilidade(String responsabilidade) {
        this.responsabilidade = responsabilidade;
    }

    public Boolean getRealizado() {
        return realizado;
    }

    public void setRealizado(Boolean realizado) {
        this.realizado = realizado;
    }

}
