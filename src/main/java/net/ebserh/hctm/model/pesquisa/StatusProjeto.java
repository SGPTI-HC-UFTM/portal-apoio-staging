package net.ebserh.hctm.model.pesquisa;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
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
