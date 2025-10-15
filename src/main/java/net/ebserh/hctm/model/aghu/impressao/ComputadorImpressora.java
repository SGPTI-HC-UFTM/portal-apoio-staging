package net.ebserh.hctm.model.aghu.impressao;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "imp_computador_impressora")
public class ComputadorImpressora implements Serializable {

    public static final Integer CLASSE_TICKETS = 2;

    @Id
    private Integer id;

    @Column(name = "id_classe_impressao")
    private Integer idClasseImpressao;

    @ManyToOne
    @JoinColumn(name = "id_computador")
    private Computador computador;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_impressora")
    private Impressora impressora;

    @Override
    public String toString() {
        return "ComputadorImpressora [id=" + id + "]";
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
        ComputadorImpressora other = (ComputadorImpressora) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIdClasseImpressao() {
        return idClasseImpressao;
    }

    public void setIdClasseImpressao(Integer idClasseImpressao) {
        this.idClasseImpressao = idClasseImpressao;
    }

    public Computador getComputador() {
        return computador;
    }

    public void setComputador(Computador computador) {
        this.computador = computador;
    }

    public Impressora getImpressora() {
        return impressora;
    }

    public void setImpressora(Impressora impressora) {
        this.impressora = impressora;
    }

}
