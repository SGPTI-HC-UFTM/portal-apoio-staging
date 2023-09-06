package net.ebserh.hctm.model.aghu.cirurgias;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Entity
@Table(schema = "agh", name = "mbc_cirurgias")
public class Cirurgia implements Serializable {

    @Id
    @Column(name = "seq")
    private Integer id;

    private LocalDateTime data;

    private String situacao;

    @Column(name = "natureza_agend")
    private String naturezaAgendamento;

    @ManyToOne
    @JoinColumn(name = "esp_seq")
    private Especialidade especialidade;

    @ManyToOne
    @JoinColumn(name = "pac_codigo")
    private Paciente paciente;

    @OneToMany(mappedBy = "cirurgia")
    private List<ProcedimentoCirurgia> procedimentos = new ArrayList<>();

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
        Cirurgia other = (Cirurgia) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Cirurgia [id=" + id + "]";
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }

    public String getNaturezaAgendamento() {
        return naturezaAgendamento;
    }

    public void setNaturezaAgendamento(String naturezaAgendamento) {
        this.naturezaAgendamento = naturezaAgendamento;
    }

    public Especialidade getEspecialidade() {
        return especialidade;
    }

    public void setEspecialidade(Especialidade especialidade) {
        this.especialidade = especialidade;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public List<ProcedimentoCirurgia> getProcedimentos() {
        return procedimentos;
    }

    public void setProcedimentos(List<ProcedimentoCirurgia> procedimentos) {
        this.procedimentos = procedimentos;
    }
    
}
