package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "estudos_clinicos")
public class EstudoClinico extends AbstractEntity {

    @Size(max = 300)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "pesquisador_id")
    private Pesquisador responsavel;

    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEstudoClinico status;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Pesquisador getResponsavel() {
        return responsavel;
    }

    public void setResponsavel(Pesquisador responsavel) {
        this.responsavel = responsavel;
    }

    public StatusEstudoClinico getStatus() {
        return status;
    }

    public void setStatus(StatusEstudoClinico status) {
        this.status = status;
    }
}
