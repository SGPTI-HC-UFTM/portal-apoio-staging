package net.ebserh.hctm.model.aghu.impressao;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(schema = "agh", name = "imp_computador")
@NamedQuery(name = "Computador.findByIp",
    query = "SELECT c " +
        "FROM Computador c " +
        "WHERE c.ip = :ip")
public class Computador implements Serializable {

    @Id
    private Integer id;

    @Column(name = "ip_computador")
    private String ip;

    @Column(name = "nome_computador")
    private String nome;

    @OneToMany(mappedBy = "computador", fetch = FetchType.EAGER)
    private List<ComputadorImpressora> computadoresImpressoras;

    /*
     * Auto-generated
     */
    @Override
    public String toString() {
        return "Computador [id=" + id + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Computador other = (Computador) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        return true;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

	public List<ComputadorImpressora> getComputadoresImpressoras() {
		return computadoresImpressoras;
	}

	public void setComputadoresImpressoras(List<ComputadorImpressora> computadoresImpressoras) {
		this.computadoresImpressoras = computadoresImpressoras;
	}

}
