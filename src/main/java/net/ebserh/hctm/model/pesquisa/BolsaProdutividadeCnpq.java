package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "bolsas_produtividade_cnpq")
@NamedQuery(name = "BolsaProdutividadeCnpq.findAll",
        query = "SELECT b "
            + "FROM BolsaProdutividadeCnpq b "
            + "ORDER BY b.descricao")
public class BolsaProdutividadeCnpq extends AbstractEntity {

    @Size(max = 10)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
