package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "status_projeto")
@NamedQuery(name = "StatusProjeto.findAll", query = """
SELECT
    s
FROM
    StatusProjeto s
ORDER BY
    s.descricao""")
public class StatusProjeto extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
