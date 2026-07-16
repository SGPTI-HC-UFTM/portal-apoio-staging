package net.ebserh.hctm.model.auth;

import jakarta.persistence.*;
import net.ebserh.hctm.model.util.AbstractEntity;

@Entity
@Table(schema = "auth", name = "usuarios_grupos")
public class UsuarioGrupo extends AbstractEntity {

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "grupo_id")
    private Grupo grupo;

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Grupo getGrupo() {
        return grupo;
    }

    public void setGrupo(Grupo grupo) {
        this.grupo = grupo;
    }

}
