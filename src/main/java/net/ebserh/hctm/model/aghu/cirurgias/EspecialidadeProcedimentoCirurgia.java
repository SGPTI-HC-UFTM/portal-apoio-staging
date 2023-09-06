package net.ebserh.hctm.model.aghu.cirurgias;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.Especialidade;

@Entity
@Table(schema = "agh", name = "mbc_especialidade_proc_cirgs")
public class EspecialidadeProcedimentoCirurgia implements Serializable {

    @EmbeddedId
    private EspecialidadeProcedimentoCirurgiaId id;

    @ManyToOne
    @JoinColumn(name = "pci_seq", insertable = false, updatable = false)
    private ProcedimentoCirurgico procedimentoCirurgico;

    @ManyToOne
    @JoinColumn(name = "esp_seq", insertable = false, updatable = false)
    private Especialidade especialidade;

    private String situacao;

    @Override
    public String toString() {
        return "EspecialidadeProcedimentoCirurgia [id=" + id + "]";
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
        EspecialidadeProcedimentoCirurgia other = (EspecialidadeProcedimentoCirurgia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public EspecialidadeProcedimentoCirurgiaId getId() {
        return id;
    }

    public void setId(EspecialidadeProcedimentoCirurgiaId id) {
        this.id = id;
    }

    public ProcedimentoCirurgico getProcedimentoCirurgico() {
        return procedimentoCirurgico;
    }

    public void setProcedimentoCirurgico(ProcedimentoCirurgico procedimentoCirurgico) {
        this.procedimentoCirurgico = procedimentoCirurgico;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
