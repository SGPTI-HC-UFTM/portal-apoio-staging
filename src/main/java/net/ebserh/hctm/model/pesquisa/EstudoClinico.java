package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "estudos_clinicos")
@NamedQuery(name = "EstudoClinico.findByTitulo", query = """
SELECT
    e
FROM
    EstudoClinico e
WHERE
    UPPER(e.titulo) LIKE :titulo
ORDER BY
    e.titulo""")
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
