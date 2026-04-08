package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "instituicoes")
@NamedQuery(name = "Instituicao.findAll", query = """
SELECT
    i
FROM
    Instituicao i
ORDER BY
    i.nome""")
@NamedQuery(name = "Instituicao.findByNome", query = """
SELECT
    i
FROM
    Instituicao i
WHERE
    i.nome = :nome""")
public class Instituicao extends AbstractEntity {

    @Size(max = 100)
    private String nome;

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }
}