package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "orgaos_financiadores")
@NamedQuery(name = "OrgaoFinanciador.findAll", query = """
SELECT
    o
FROM
    OrgaoFinanciador o
ORDER BY
    o.descricao""")
public class OrgaoFinanciador extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
