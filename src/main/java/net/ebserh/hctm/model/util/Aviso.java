package net.ebserh.hctm.model.util;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Size;

@Entity
@Table(schema = "apoio", name = "avisos")
@NamedQueries({
        @NamedQuery(name = "Aviso.findAll", query = "SELECT a " +
                "FROM Aviso a " +
                "ORDER BY a.dthrInicial"),
        @NamedQuery(name = "Aviso.findAvisosDia", query = "SELECT a " +
                "FROM Aviso a " +
                "WHERE a.dthrInicial <= :horaAtual and a.dthrFinal >= :horaAtual " +
                "ORDER BY a.dthrInicial DESC")
})
public class Aviso implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    @Size(max = 255, message = "O campo \"Título\" deve conter no máximo 255 caracteres")
    private String titulo;

    @Column
    private String informacao;

    @Column(name = "data_inicial")
    private LocalDateTime dthrInicial;

    @Column(name = "data_final")
    private LocalDateTime dthrFinal;

    @Column()
    private String usuario;

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitulo() {
        return this.titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getInformacao() {
        return this.informacao;
    }

    public void setInformacao(String informacao) {
        this.informacao = informacao;
    }

    public LocalDateTime getDthrInicial() {
        return this.dthrInicial;
    }

    public void setDthrInicial(LocalDateTime dthrInicial) {
        this.dthrInicial = dthrInicial;
    }

    public LocalDateTime getDthrFinal() {
        return this.dthrFinal;
    }

    public void setDthrFinal(LocalDateTime dthrFinal) {
        this.dthrFinal = dthrFinal;
    }

    public String getUsuario() {
        return this.usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Aviso)) {
            return false;
        }
        Aviso aviso = (Aviso) o;
        return Objects.equals(id, aviso.id) && Objects.equals(titulo, aviso.titulo)
                && Objects.equals(informacao, aviso.informacao) && Objects.equals(dthrInicial, aviso.dthrInicial)
                && Objects.equals(dthrFinal, aviso.dthrFinal) && Objects.equals(usuario, aviso.usuario);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, informacao, dthrInicial, dthrFinal, usuario);
    }

}
