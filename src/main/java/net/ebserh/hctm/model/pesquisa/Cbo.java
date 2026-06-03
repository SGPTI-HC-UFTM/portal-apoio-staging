package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "cbos")
@NamedQuery(name = "Cbo.findAll", query = """
SELECT
    c
FROM
    Cbo c
ORDER BY
    c.descricao""")
@NamedQuery(name = "Cbo.findByDescricao", query = """
SELECT
    c
FROM
    Cbo c
WHERE
    function('TRANSLATE', function('UPPER', c.descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')
    = function('TRANSLATE', function('UPPER', :descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')""")
public class Cbo extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
