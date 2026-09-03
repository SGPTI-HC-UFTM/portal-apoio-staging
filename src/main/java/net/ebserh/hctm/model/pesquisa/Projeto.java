package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "projetos")
public class Projeto extends AbstractEntity {

    @Size(max = 200)
    private String titulo;

    private Boolean financiamento;

    @ManyToOne
    @JoinColumn(name = "tipo_projeto_id")
    private TipoProjeto tipo;

    @ManyToOne
    @JoinColumn(name = "status_projeto_id")
    private StatusProjeto status;

    @ManyToOne
    @JoinColumn(name = "fonte_financiadora_id")
    private FonteFinanciadora fonteFinanciadora;

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Boolean getFinanciamento() {
        return financiamento;
    }

    public void setFinanciamento(Boolean financiamento) {
        this.financiamento = financiamento;
    }

    public TipoProjeto getTipo() {
        return tipo;
    }

    public void setTipo(TipoProjeto tipo) {
        this.tipo = tipo;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        this.status = status;
    }

    public FonteFinanciadora getFonteFinanciadora() {
        return fonteFinanciadora;
    }

    public void setFonteFinanciadora(FonteFinanciadora fonteFinanciadora) {
        this.fonteFinanciadora = fonteFinanciadora;
    }

}
