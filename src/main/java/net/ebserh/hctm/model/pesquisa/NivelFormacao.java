package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "pesquisa", name = "niveis_formacao")
@NamedQuery(name = "NivelFormacao.findAll",
        query = "SELECT n "
                + "FROM NivelFormacao n "
                + "ORDER BY n.nivel")
public class NivelFormacao extends AbstractEntity {

    @Size(max = 30)
    private String nivel;

    public String getNivel() {
        return nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

}