package net.ebserh.hctm.model.aghu.servidores;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(schema = "agh", name = "rap_qualificacoes")
public class Qualificacao implements Serializable {

    @Id
    @Column(name = "pes_codigo")
    private Integer pesCodigo;

    @Column(name = "tql_codigo")
    private Integer tipoQualificao;

    @Column(name = "nro_reg_conselho")
    private String numeroConselho;

    public Integer getPesCodigo() {
        return this.pesCodigo;
    }

    public void setPesCodigo(Integer pesCodigo) {
        this.pesCodigo = pesCodigo;
    }

    public Integer getTipoQualificao() {
        return this.tipoQualificao;
    }

    public void setTipoQualificao(Integer tipoQualificao) {
        this.tipoQualificao = tipoQualificao;
    }

    public String getNumeroConselho() {
        return this.numeroConselho;
    }

    public void setNumeroConselho(String numeroConselho) {
        this.numeroConselho = numeroConselho;
    }

}