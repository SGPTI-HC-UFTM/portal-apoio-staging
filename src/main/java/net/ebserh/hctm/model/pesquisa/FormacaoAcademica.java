package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "formacoes_academicas")
@NamedQuery(name = "FormacaoAcademica.findAll", query = """
SELECT
    f
FROM
    FormacaoAcademica f
ORDER BY
    f.nome""")
@NamedQuery(name = "FormacaoAcademica.findByNome", query = """
SELECT
    f
FROM
    FormacaoAcademica f
WHERE
    f.nome = :nome""")
public class FormacaoAcademica extends AbstractEntity {

    @Size(max = 50)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}