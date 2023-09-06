package net.ebserh.hctm.model.aghu.ambulatorio;

import java.io.Serializable;
import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import net.ebserh.hctm.model.aghu.pacientes.Paciente;
import net.ebserh.hctm.model.aghu.servidores.Servidor;

@Entity
@Table(schema = "agh", name = "aac_consultas")
@NamedQuery(name = "Consulta.findByUf",
        query = "select c from Consulta c " + 
            "join c.grade g " + 
            "join g.unidadeFuncional uf " + 
            "join c.paciente p " + 
            "where c.situacaoConsulta = 'M'" + 
            "and uf.seq = :ufSeq " + 
            "and year(c.dthrConsulta) = :ano " + 
            "and month(c.dthrConsulta) = :mes " + 
            "and day(c.dthrConsulta) = :dia " + 
            "order by p.prontuario")
@NamedQuery(name = "Consulta.findByUfAndEspecialidade",
        query = "select c from Consulta  c " + 
            "join c.grade g " + 
            "join g.unidadeFuncional uf " + 
            "join c.paciente p " + 
            "where c.situacaoConsulta = 'M' " + 
            "and uf.seq = :ufSeq " + 
            "and year(c.dthrConsulta) = :ano " + 
            "and month(c.dthrConsulta) = :mes " + 
            "and day(c.dthrConsulta) = :dia " + 
            "and c.grade.especialidade.seq = :especialidadeSeq " + 
            "order by p.prontuario")
@NamedQuery(name = "Consulta.findProntuariosDiaria",
        query = "select distinct p.prontuario " + 
            "from Consulta c " + 
            "join c.grade g " + 
            "join c.paciente p " + 
            "left join g.servidor s " + 
            "left join g.equipe e " + 
            "left join s.pessoa pe " + 
            "where c.situacaoConsulta = 'M' " + 
            "and year(c.dthrConsulta) = :ano " + 
            "and month(c.dthrConsulta) = :mes " + 
            "and day(c.dthrConsulta) = :dia " + 
            "and p.prontuario is not null " + 
            "order by p.prontuario")
@NamedQuery(name = "Consulta.findByProntuario",
        query = "SELECT c " + 
            "FROM Consulta c " + 
            "JOIN c.grade g " + 
            "JOIN g.especialidade e " + 
            "JOIN c.paciente p " + 
            "JOIN c.retorno r " + 
            "WHERE p.prontuario = :prontuario " + 
            "ORDER BY c.dthrConsulta DESC")
@NamedQuery(name = "Consulta.findByProntuarioAndLoginResponsavelAtendimento",
        query = "SELECT c " +
            "FROM Consulta c " +
            "JOIN c.grade g " +
            "JOIN g.servidor s " +
            "JOIN c.paciente p " +
            "WHERE p.prontuario = :prontuario " +
            "AND UPPER(s.usuario) = :login " +
            "ORDER BY c.dthrConsulta DESC")
@NamedQuery(name = "Consulta.findGeradasByGradeAndDthrConsultaBetween",
        query = "SELECT c " +
            "FROM Consulta c " +
            "WHERE c.situacaoConsulta = 'G' " +
            "AND c.grade = :grade " + 
            "AND DATE(c.dthrConsulta) BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY c.dthrConsulta")
@NamedQuery(name = "Consulta.findGeradasByEspecialidadeAndDthrConsultaBetween",
        query = "SELECT c " +
            "FROM Consulta c " +
            "WHERE c.situacaoConsulta = 'G' " +
            "AND c.grade.especialidade = :especialidade " + 
            "AND DATE(c.dthrConsulta) BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY c.dthrConsulta")
@NamedQuery(name = "Consulta.findGeradasByCondicaoAtendimentoAndEspecialidadeAndDthrConsultaBetween",
        query = "SELECT c " +
            "FROM Consulta c " +
            "JOIN c.grade g " +
            "WHERE g.especialidade = :especialidade " + 
            "AND c.condicaoAtendimento = :condicaoAtendimento " + 
            "AND c.situacaoConsulta = 'G' " +
            "AND DATE(c.dthrConsulta) BETWEEN :dataInicial AND :dataFinal " +
            "ORDER BY c.dthrConsulta")
@NamedQuery(name = "Consulta.findByData",
        query = "SELECT c "
            + "FROM Consulta c " 
            + "WHERE c.situacaoConsulta = 'M' " 
            + "AND DATE(c.dthrConsulta) = :dataReferencia " 
            + "ORDER BY c.grade.equipe.nome, c.paciente.prontuario")
public class Consulta implements Serializable {

	@Id
	private Integer numero;

	@Column(name = "dt_consulta")
	private LocalDateTime dthrConsulta;
	
	@Column(name = "ind_sit_consulta")
	private String situacaoConsulta;
	
	@ManyToOne
	@JoinColumn(name = "grd_seq")
	private Grade grade;
	
	@ManyToOne
	@JoinColumn(name = "fag_caa_seq")
	private CondicaoAtendimento condicaoAtendimento;
	
	@ManyToOne
	@JoinColumn(name = "pac_codigo")
	private Paciente paciente;

    @ManyToOne
    @JoinColumn(name = "ret_seq")
    private Retorno retorno;

    @ManyToOne
    @JoinColumns({
        @JoinColumn(name = "ser_matricula_atendido", referencedColumnName = "matricula"),
        @JoinColumn(name = "ser_vin_codigo_atendido", referencedColumnName = "vin_codigo")
    })
    private Servidor responsavelAtendimento;

    @Transient
    private boolean solicitada = false;

	/*
     * Auto-generated
     */
	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public CondicaoAtendimento getCondicaoAtendimento() {
		return condicaoAtendimento;
	}

	public void setCondicaoAtendimento(CondicaoAtendimento condicaoAtendimento) {
		this.condicaoAtendimento = condicaoAtendimento;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

    public LocalDateTime getDthrConsulta() {
        return dthrConsulta;
    }

    public void setDthrConsulta(LocalDateTime dthrConsulta) {
        this.dthrConsulta = dthrConsulta;
    }

    public String getSituacaoConsulta() {
        return situacaoConsulta;
    }

    public void setSituacaoConsulta(String situacaoConsulta) {
        this.situacaoConsulta = situacaoConsulta;
    }

	public Retorno getRetorno() {
		return retorno;
	}

	public void setRetorno(Retorno retorno) {
		this.retorno = retorno;
	}

	public Servidor getResponsavelAtendimento() {
		return responsavelAtendimento;
	}

	public void setResponsavelAtendimento(Servidor responsavelAtendimento) {
		this.responsavelAtendimento = responsavelAtendimento;
	}

	public boolean isSolicitada() {
		return solicitada;
	}

	public void setSolicitada(boolean solicitada) {
		this.solicitada = solicitada;
	}

}
