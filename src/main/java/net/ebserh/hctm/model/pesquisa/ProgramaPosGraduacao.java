package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "programas_pos_graduacao")
@NamedQuery(name = "ProgramaPosGraduacao.findAll", query = """
SELECT
    p
FROM
    ProgramaPosGraduacao p
ORDER BY
    p.descricao""")
@NamedQuery(name = "ProgramaPosGraduacao.findByDescricao", query = """
SELECT
    p
FROM
    ProgramaPosGraduacao p
WHERE
    function('TRANSLATE', function('UPPER', p.descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')
    = function('TRANSLATE', function('UPPER', :descricao),'ÁÀÂÃÄÉÈÊËÍÌÎÏÓÒÔÕÖÚÙÛÜÇ','AAAAAEEEEIIIIOOOOOUUUUC')""")
public class ProgramaPosGraduacao extends AbstractEntity {

    @Size(max = 100)
    private String descricao;

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}