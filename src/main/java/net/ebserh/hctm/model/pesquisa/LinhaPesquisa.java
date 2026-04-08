package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "linha_pesquisa")
@NamedQuery(name = "LinhaPesquisa.findAll", query = """
SELECT
    l
FROM
    LinhaPesquisa l
ORDER BY
    l.descricao""")

@NamedQuery(name = "LinhaPesquisa.findByDescricao", query = """
SELECT
    l
FROM
    LinhaPesquisa l
WHERE
    UPPER(l.descricao) = :descricao""")
public class LinhaPesquisa extends AbstractEntity {

    @Size(max = 100)
    private String descricao;
    
    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
