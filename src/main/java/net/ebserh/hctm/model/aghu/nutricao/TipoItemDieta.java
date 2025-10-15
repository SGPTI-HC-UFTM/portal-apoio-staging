package net.ebserh.hctm.model.aghu.nutricao;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "anu_tipo_item_dietas")
@NamedQuery(name = "TipoItemDieta.findAtivos",
    query = "SELECT t " +
        "FROM TipoItemDieta t " +
        "WHERE t.situacao = 'A' " +
        "ORDER BY t.descricao")
public class TipoItemDieta implements Serializable {

    @Id
    private Integer seq;

    private String descricao;

    @Column(name = "sintaxe_medico")
    private String nomeMedico;

    @Column(name = "sintaxe_nutricao")
    private String nomeNutricao;

    @Column(name = "ind_situacao")
    private String situacao;

    @Override
    public String toString() {
        return "TipoItemDieta [seq=" + seq + "]";
    }

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
        TipoItemDieta other = (TipoItemDieta) obj;
        if (seq == null) {
            if (other.seq != null)
                return false;
        } else if (!seq.equals(other.seq))
            return false;
        return true;
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

    public String getNomeMedico() {
        return nomeMedico;
    }

    public void setNomeMedico(String nomeMedico) {
        this.nomeMedico = nomeMedico;
    }

    public String getNomeNutricao() {
        return nomeNutricao;
    }

    public void setNomeNutricao(String nomeNutricao) {
        this.nomeNutricao = nomeNutricao;
    }

    public String getSituacao() {
        return situacao;
    }

    public void setSituacao(String situacao) {
        this.situacao = situacao;
    }
    
}
