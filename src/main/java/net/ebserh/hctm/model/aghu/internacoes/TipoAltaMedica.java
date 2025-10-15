package net.ebserh.hctm.model.aghu.internacoes;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(schema = "agh", name = "ain_tipos_alta_medica")
public class TipoAltaMedica implements Serializable {

    public static final String OBITO_MENOS_24H = "OBITO -24 HORAS";

    public static final String OBITO_MAIS_24H = "OBITO +24 HORAS";

    public static final String MEDICA = "ALTA MÉDICA";

    public static final String OBITO = "ÓBITO";
    
    public static final String TRANSFERENCIA = "TRANSFERÊNCIA PARA OUTRO HOSPITAL";

    public static final String DESISTENCIA = "DESISTÊNCIA DO TRATAMENTO";

    public static final String EVASAO = "EVASAO";

    @Id
    private String codigo;

    private String descricao;

    @Column(name = "ind_situacao")
    private String situacao;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
        TipoAltaMedica other = (TipoAltaMedica) obj;
        if (codigo == null) {
            if (other.codigo != null)
                return false;
        } else if (!codigo.equals(other.codigo))
            return false;
        return true;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
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
