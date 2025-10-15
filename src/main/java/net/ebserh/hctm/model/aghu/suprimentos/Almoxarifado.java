package net.ebserh.hctm.model.aghu.suprimentos;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "sce_almoxarifados")
@NamedQuery(name = "Almoxarifado.findAtivos", 
    query = "SELECT a "
        + "FROM Almoxarifado a "
        + "WHERE a.situacao = 'A' "
        + "ORDER BY a.descricao")
public class Almoxarifado implements Serializable {

    @Id
    private Integer seq;

    private String descricao;

    @Column(name = "ind_situacao")
    private String situacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
        Almoxarifado other = (Almoxarifado) obj;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Almoxarifado [seq=" + seq + "]";
    }

    public Integer getSeq() {
        return seq;
    }

    public void setSeq(Integer seq) {
        this.seq = seq;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
