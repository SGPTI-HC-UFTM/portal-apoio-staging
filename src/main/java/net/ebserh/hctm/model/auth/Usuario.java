package net.ebserh.hctm.model.auth;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.model.util.AbstractEntity;

import java.util.List;

@Entity
@Table(schema = "auth", name = "usuarios")
public class Usuario extends AbstractEntity {

    @Size(max = 256)
    private String login;

    @Size(max = 256)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(schema = "auth", name = "usuarios_grupos",
            joinColumns = { @JoinColumn(name = "usuario_id") },
            inverseJoinColumns = { @JoinColumn(name = "grupo_id") })
    private List<Grupo> grupos;

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public List<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(List<Grupo> grupos) {
        this.grupos = grupos;
    }

}
