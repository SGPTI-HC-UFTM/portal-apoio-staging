package net.ebserh.hctm.model.aghu.prescricoes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

import net.ebserh.hctm.model.aghu.Atendimento;
import net.ebserh.hctm.model.aghu.servidores.Servidor;

@Entity
@Table(schema = "agh", name = "mpm_prescricao_dietas")
@NamedQuery(name = "PrescricaoDietaAghu.findInternadosByDataInicio",
    query = "SELECT p " 
        + "FROM PrescricaoDietaAghu p " 
        + "JOIN p.atendimento a " 
        + "WHERE FUNCTION('DATE', p.dthrInicio) = :dataInicio "
        + "AND a.origem = 'I' " 
        + "ORDER BY p.dthrInicio")
@NamedQuery(name = "PrescricaoDietaAghu.findByDataInicio",
    query = "SELECT p " 
        + "FROM PrescricaoDietaAghu p " 
        + "WHERE FUNCTION('DATE', p.dthrInicio) = :dataInicio "
        + "ORDER BY p.dthrInicio")
@NamedQuery(name = "PrescricaoDietaAghu.findByDataInicioUnidadeFuncional",
    query = "SELECT p " 
        + "FROM PrescricaoDietaAghu p " 
        + "JOIN p.atendimento a " 
        + "WHERE FUNCTION('DATE', p.dthrInicio) = :dataInicio "
        + "AND a.unidadeFuncional = :unidadeFuncional "
        + "ORDER BY p.dthrInicio")
public class PrescricaoDietaAghu implements Serializable {

    @EmbeddedId
    private PrescricaoDietaPK id;

    @Column(name = "dthr_inicio")
    private LocalDateTime dthrInicio;

    @Column(name = "dthr_fim")
    private LocalDateTime dthrFim;

    @Column(name = "criado_em")
    private LocalDateTime criadoEm;

    private String observacao;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ser_matricula", referencedColumnName = "matricula"),
        @JoinColumn(name = "ser_vin_codigo", referencedColumnName = "vin_codigo")
    })
    private Servidor servidor;

    @ManyToOne
    @JoinColumn(name = "atd_seq")
    private Atendimento atendimento;

    @OneToMany(mappedBy = "prescricaoDieta", fetch = FetchType.EAGER)
    private List<ItemPrescricaoDietaAghu> itens;

    @Override
    public String toString() {

        String resultado = "Item\n\n";

            for (ItemPrescricaoDietaAghu i : itens) {

                resultado = resultado + i.getTipoItemDieta().getDescricao() + " " + i.getNumVezes() + " vezes " 
                +  i.getFrequencia() + " " + i.getTipoFrequenciaAprazamento().getDescricao() + " Quantidade: " + i.getQuantidade() +" \n";
                
            }

        resultado = resultado + "\nObservação:\n" + this.observacao + "\n\nData Hora Inicio:\n" + this.dthrInicio + "\nData Hora Fim:\n" + this.dthrFim ;

        return resultado;
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
        PrescricaoDietaAghu other = (PrescricaoDietaAghu) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public PrescricaoDietaPK getId() {
        return id;
    }

    public void setId(PrescricaoDietaPK id) {
        this.id = id;
    }

    public LocalDateTime getDthrInicio() {
        return dthrInicio;
    }

    public void setDthrInicio(LocalDateTime dthrInicio) {
        this.dthrInicio = dthrInicio;
    }

    public LocalDateTime getDthrFim() {
        return dthrFim;
    }

    public void setDthrFim(LocalDateTime dthrFim) {
        this.dthrFim = dthrFim;
    }

    public LocalDateTime getCriadoEm() {
        return criadoEm;
    }

    public void setCriadoEm(LocalDateTime criadoEm) {
        this.criadoEm = criadoEm;
    }

    public Servidor getServidor() {
        return servidor;
    }

    public void setServidor(Servidor servidor) {
        this.servidor = servidor;
    }

    public Atendimento getAtendimento() {
        return atendimento;
    }

    public void setAtendimento(Atendimento atendimento) {
        this.atendimento = atendimento;
    }

    public List<ItemPrescricaoDietaAghu> getItens() {
        return itens;
    }

    public void setItens(List<ItemPrescricaoDietaAghu> itens) {
        this.itens = itens;
    }

    public String getObservacao() {
        return observacao;
    }

    public void setObservacao(String observacao) {
        this.observacao = observacao;
    }
    
}
