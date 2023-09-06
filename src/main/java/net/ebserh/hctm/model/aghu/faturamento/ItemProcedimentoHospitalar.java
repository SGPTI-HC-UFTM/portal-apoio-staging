package net.ebserh.hctm.model.aghu.faturamento;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "agh", name = "fat_itens_proced_hospitalar")
@NamedQuery(name = "ItemProcedimentoHospitalar.findAll", query = "SELECT i "
        + "FROM ItemProcedimentoHospitalar i "
        + "ORDER BY i.descricao")
@NamedQuery(name = "ItemProcedimentoHospitalar.findAtivos", query = "SELECT i "
        + "FROM ItemProcedimentoHospitalar i "
        + "WHERE i.situacao = 'A' "
        + "ORDER BY i.descricao")
@NamedQuery(name = "ItemProcedimentoHospitalar.findByCodTabela", query = "SELECT i "
        + "FROM ItemProcedimentoHospitalar i "
        + "WHERE i.codTabela = :codTabela")
@NamedQuery(name = "ItemProcedimentoHospitalar.findByDescricao", query = "SELECT i FROM ItemProcedimentoHospitalar i "
        + "WHERE descricao LIKE concat('%', :descricao, '%') "
        + "ORDER BY i.descricao")
@NamedQuery(name = "ItemProcedimentoHospitalar.findByCodigo", query = "SELECT i FROM ItemProcedimentoHospitalar i "
        + "WHERE i.codTabela = :codigo")
@NamedQuery(name = "ItemProcedimentoHospitalar.findByPhoSeqAndSeq", query = "SELECT iph "
        + "FROM ItemProcedimentoHospitalar iph "
        + "WHERE iph.id.phoSeq = :phoSeq "
        + "AND iph.id.seq = :seq")
public class ItemProcedimentoHospitalar implements Serializable {

    @EmbeddedId
    private ItemProcedimentoHospitalarPK itemProcedimentoHospitalarPK;

    @Column(name = "cod_tabela")
    private Long codTabela;

    private String descricao;

    @Column(name = "ind_situacao")
    private String situacao;

    public String getCodTabelaFormatado() {
		if (codTabela == null)
			return "";

		String sigtapStr = String.format("%010d", codTabela);
		return String.format("%s.%s.%s.%s-%s",
			sigtapStr.substring(0, 2),
			sigtapStr.substring(2, 4),
			sigtapStr.substring(4, 6),
			sigtapStr.substring(6, 9),
			sigtapStr.substring(9));
    }

    @Override
    public String toString() {
        return "ItemProcedimentoHospitalar [itemProcedimentoHospitalarPK=" + itemProcedimentoHospitalarPK + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result
                + ((itemProcedimentoHospitalarPK == null) ? 0 : itemProcedimentoHospitalarPK.hashCode());
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
        ItemProcedimentoHospitalar other = (ItemProcedimentoHospitalar) obj;
        if (itemProcedimentoHospitalarPK == null) {
            if (other.itemProcedimentoHospitalarPK != null)
                return false;
        } else if (!itemProcedimentoHospitalarPK.equals(other.itemProcedimentoHospitalarPK))
            return false;
        return true;
    }

    public ItemProcedimentoHospitalarPK getItemProcedimentoHospitalarPK() {
        return itemProcedimentoHospitalarPK;
    }

    public void setItemProcedimentoHospitalarPK(ItemProcedimentoHospitalarPK itemProcedimentoHospitalarPK) {
        this.itemProcedimentoHospitalarPK = itemProcedimentoHospitalarPK;
    }

    public Long getCodTabela() {
        return codTabela;
    }

    public void setCodTabela(Long codTabela) {
        this.codTabela = codTabela;
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
