package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "niveis_formacao")
@NamedQuery(name = "NivelFormacao.findAll", query = """
SELECT
    o
FROM
    NivelFormacao o
ORDER BY
    o.descricao""")
@NamedQuery(name = "NivelFormacao.findByDescricao", query = """
SELECT
    o
FROM
    NivelFormacao o
WHERE
    function('TRANSLATE', function('UPPER', o.descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')
    = function('TRANSLATE', function('UPPER', :descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')""")
public class NivelFormacao extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    public String getNivel() {
        return descricao;
    }

    public void setNivel(String descricao) {
        this.descricao = descricao;
    }
}