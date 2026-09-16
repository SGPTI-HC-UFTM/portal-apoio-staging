package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "projetos_pesquisadores")
@NamedQuery(name = "ProjetoPesquisador.findByProjeto", query = """
SELECT
    p
FROM
    ProjetoPesquisador p
WHERE
    p.projeto = :projeto""")
public class ProjetoPesquisador extends AbstractEntity {

    @Size(max = 100)
    private String funcao;

    @ManyToOne
    @JoinColumn(name = "projeto_id")
    private Projeto projeto;

    @ManyToOne
    @JoinColumn( name = "pesquisador_id")
    private Pesquisador pesquisador;

    public String getFuncao() {
        return funcao;
    }

    public void setFuncao(String funcao) {
        this.funcao = funcao;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Pesquisador getPesquisador() {
        return pesquisador;
    }

    public void setPesquisador(Pesquisador pesquisador) {
        this.pesquisador = pesquisador;
    }

}
