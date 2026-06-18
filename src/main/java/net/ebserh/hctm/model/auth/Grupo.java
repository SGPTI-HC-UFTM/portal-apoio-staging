package net.ebserh.hctm.model.auth;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "auth", name = "grupos")
@NamedQuery(name = "Grupo.findAll", query = """
SELECT
    g
FROM
    Grupo g
ORDER BY
    g.grupo""")
@NamedQuery(name = "Grupo.findByGrupo", query = """
SELECT
    g
FROM
    Grupo g
WHERE
    UPPER(g.grupo) = :grupo""")
public class Grupo extends AbstractEntity {

    @Size(max = 32)
    private String grupo;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

}
