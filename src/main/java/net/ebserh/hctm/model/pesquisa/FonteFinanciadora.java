package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "fontes_financiadoras")
@NamedQuery(name = "FonteFinanciadora.findAll", query = """
SELECT
    f
FROM
    FonteFinanciadora f
ORDER BY
    f.descricao""")
@NamedQuery(name = "FonteFinanciadora.findByDescricao", query = """
SELECT
    f
FROM
    FonteFinanciadora f
WHERE
    function('TRANSLATE', function('UPPER', f.descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')
    = function('TRANSLATE', function('UPPER', :descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')""")
public class FonteFinanciadora extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    private Boolean ativa;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Boolean getAtiva() {
        return ativa;
    }

    public void setAtiva(Boolean ativa) {
        this.ativa = ativa;
    }

}
