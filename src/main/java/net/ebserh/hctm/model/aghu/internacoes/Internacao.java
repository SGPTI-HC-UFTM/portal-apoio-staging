package net.ebserh.hctm.model.aghu.internacoes;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedNativeQuery;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;

import net.ebserh.hctm.model.aghu.Cid;
import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;

@Entity
@Table(schema = "agh", name = "ain_internacoes")
@NamedQuery(name = "Internacao.findAtivasByProntuarioIn",
	query = "SELECT i "
		+ "FROM Internacao i "
		+ "JOIN i.paciente p "
		+ "WHERE i.indPacienteInternado = 'S' "
		+ "AND p.prontuario IN :prontuarios")
@NamedQuery(name = "Internacao.countPacientesByAnoMes",
	query = "SELECT COUNT(DISTINCT p) "
		+ "FROM Internacao i "
		+ "JOIN i.paciente p "
		+ "WHERE FUNCTION('MONTH', i.dthrInternacao) = :mes "
		+ "AND FUNCTION('YEAR', i.dthrInternacao) = :ano")
@NamedQuery(name = "Internacao.countInternacoesBySexoAnoMes",
	query = "SELECT COUNT(DISTINCT i) "
		+ "FROM Internacao i "
		+ "JOIN i.paciente p "
		+ "WHERE FUNCTION('MONTH', i.dthrInternacao) = :mes "
		+ "AND FUNCTION('YEAR', i.dthrInternacao) = :ano "
		+ "AND p.sexo = :sexo")
@NamedQuery(name = "Internacao.countObitosBySexoAnoMes",
	query = "SELECT COUNT(DISTINCT i) "
		+ "FROM Internacao i "
		+ "JOIN i.paciente p "
		+ "WHERE FUNCTION('MONTH', i.dthrAltaMedica) = :mes "
		+ "AND FUNCTION('YEAR', i.dthrAltaMedica) = :ano "
		+ "AND p.sexo = :sexo "
		+ "AND i.tipoAltaMedica.descricao IN ('"
		+ TipoAltaMedica.OBITO + "', '" + TipoAltaMedica.OBITO_MAIS_24H + "', '" + TipoAltaMedica.OBITO_MENOS_24H + "')")
@NamedQuery(name = "Internacao.countAltasByAnoMes",
	query = "SELECT COUNT(DISTINCT i) "
		+ "FROM Internacao i "
		+ "WHERE FUNCTION('MONTH', i.dthrAltaMedica) = :mes "
		+ "AND FUNCTION('YEAR', i.dthrAltaMedica) = :ano")
@NamedQuery(name = "Internacao.countAltasByDescricaoAnoMes",
	query = "SELECT COUNT(DISTINCT i) "
		+ "FROM Internacao i "
		+ "JOIN i.tipoAltaMedica t "
		+ "WHERE FUNCTION('MONTH', i.dthrAltaMedica) = :mes "
		+ "AND FUNCTION('YEAR', i.dthrAltaMedica) = :ano "
		+ "AND t.descricao = :descricao")
@NamedNativeQuery(name = "Internacao.topInternacoesPorCid",
	query = "SELECT " 
		+ " tmp1.cid AS cid, " 
		+ " tmp1.internacoes AS internacoes, " 
		+ " CAST(tmp1.internacoes AS REAL) / tmp2.total * 100 AS porcentagem "  
		+ "FROM ( "
		+ " SELECT " 
		+ "  ac.codigo AS cid, " 
		+ "  COUNT(DISTINCT ai.seq) AS internacoes " 
		+ " FROM " 
		+ "  agh.mpm_alta_diag_principais madp " 
		+ "  JOIN agh.agh_cids ac ON madp.cid_seq = ac.seq  " 
		+ "  JOIN agh.agh_atendimentos aa ON madp.asu_apa_atd_seq = aa.seq " 
		+ "  JOIN agh.ain_internacoes ai ON aa.int_seq = ai.seq  " 
		+ " WHERE  " 
		+ "  DATE_PART('YEAR', ai.dthr_internacao) = :ano  " 
		+ "  AND DATE_PART('MONTH', ai.dthr_internacao) = :mes  " 
		+ "  AND madp.ind_situacao = 'A'  " 
		+ " GROUP BY  " 
		+ "  ac.codigo  " 
		+ " ORDER BY  " 
		+ "  COUNT(DISTINCT ai.seq) DESC  " 
		+ " ) AS tmp1, (  " 
		+ " SELECT  " 
		+ "  COUNT(DISTINCT ai.seq) AS total  " 
		+ " FROM  " 
		+ "  agh.ain_internacoes ai " 
		+ "  JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq " 
		+ "  JOIN agh.mpm_alta_diag_principais madp ON madp.asu_apa_atd_seq = aa.seq " 
		+ " WHERE  " 
		+ "  DATE_PART('YEAR', ai.dthr_internacao) = :ano  " 
		+ "  AND DATE_PART('MONTH', ai.dthr_internacao) = :mes  " 
		+ "  AND madp.ind_situacao = 'A' " 
		+ " ) AS tmp2  " 
		+ "LIMIT 10") 
@NamedNativeQuery(name = "Internacao.topObitosPorCid",
	query = "SELECT "
		+ " tmp1.cid AS cid, "
		+ " tmp1.internacoes AS internacoes, "
		+ " CAST(tmp1.internacoes AS REAL) / tmp2.total * 100 AS porcentagem "
		+ "FROM ( "
		+ " SELECT "
		+ "  ac.codigo AS cid, "
		+ "  COUNT(DISTINCT ai.seq) AS internacoes "
		+ " FROM "
		+ "  agh.mpm_alta_diag_principais madp "
		+ "  JOIN agh.agh_cids ac ON madp.cid_seq = ac.seq "
		+ "  JOIN agh.agh_atendimentos aa ON madp.asu_apa_atd_seq = aa.seq "
		+ "  JOIN agh.ain_internacoes ai ON aa.int_seq = ai.seq "
		+ "  JOIN agh.ain_tipos_alta_medica atam ON ai.tam_codigo = atam.codigo "
		+ " WHERE "
		+ "  DATE_PART('YEAR', ai.dthr_alta_medica) = :ano "
		+ "  AND DATE_PART('MONTH', ai.dthr_alta_medica) = :mes "
		+ "  AND madp.ind_situacao = 'A' "
		+ "  AND atam.descricao IN ('"
		+ TipoAltaMedica.OBITO + "', '" + TipoAltaMedica.OBITO_MAIS_24H + "', '" + TipoAltaMedica.OBITO_MENOS_24H + "') "
		+ " GROUP BY "
		+ "  ac.codigo "
		+ " ORDER BY "
		+ "  COUNT(DISTINCT ai.seq) DESC "
		+ " ) AS tmp1, ( "
		+ " SELECT "
		+ "  COUNT(DISTINCT ai.seq) AS total "
		+ " FROM "
		+ "  agh.ain_internacoes ai "
		+ "  JOIN agh.agh_atendimentos aa ON aa.int_seq = ai.seq "
		+ "  JOIN agh.mpm_alta_diag_principais madp ON madp.asu_apa_atd_seq = aa.seq "
		+ "  JOIN agh.ain_tipos_alta_medica atam ON ai.tam_codigo = atam.codigo "
		+ " WHERE "
		+ "  DATE_PART('YEAR', ai.dthr_alta_medica) = :ano "
		+ "  AND DATE_PART('MONTH', ai.dthr_alta_medica) = :mes "
		+ "  AND madp.ind_situacao = 'A' "
		+ "  AND atam.descricao IN ('"
		+ TipoAltaMedica.OBITO + "', '" + TipoAltaMedica.OBITO_MAIS_24H + "', '" + TipoAltaMedica.OBITO_MENOS_24H + "') "
		+ " ) AS tmp2 "
		+ "LIMIT 10")
@NamedNativeQuery(name = "Internacao.internacoesPorSexoEIdade",
	query = "SELECT "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento)), "
		+ " COUNT (DISTINCT ai.seq) "
		+ "FROM "
		+ " agh.ain_internacoes ai "
		+ " JOIN agh.aip_pacientes ap ON ai.pac_codigo = ap.codigo "
		+ "WHERE "
		+ " ap.sexo = :sexo "
		+ " AND DATE_PART('YEAR', ai.dthr_alta_medica) = :ano "
		+ " AND DATE_PART('MONTH', ai.dthr_alta_medica) = :mes "
		+ "GROUP BY "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento)) "
		+ "ORDER BY "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento))")
@NamedNativeQuery(name = "Internacao.obitosPorSexoEIdade",
	query = "SELECT "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento)), "
		+ " COUNT (DISTINCT ai.seq) "
		+ "FROM "
		+ " agh.ain_internacoes ai "
		+ " JOIN agh.aip_pacientes ap ON ai.pac_codigo = ap.codigo "
		+ " JOIN agh.ain_tipos_alta_medica atam ON ai.tam_codigo = atam.codigo "
		+ "WHERE "
		+ " ap.sexo = :sexo "
		+ " AND DATE_PART('YEAR', ai.dthr_alta_medica) = :ano "
		+ " AND DATE_PART('MONTH', ai.dthr_alta_medica) = :mes "
		+ " AND atam.descricao IN ('"
		+ TipoAltaMedica.OBITO + "', '" + TipoAltaMedica.OBITO_MAIS_24H + "', '" + TipoAltaMedica.OBITO_MENOS_24H + "') "
		+ " GROUP BY "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento)) "
		+ "ORDER BY "
		+ " DATE_PART ('YEAR', AGE(ap.dt_nascimento))")
@NamedQuery(name = "Internacao.findByLeitoUnidadeFuncionalSigla", 
	query = "SELECT i "
		+ "FROM Internacao i "
		+ "JOIN i.leito l "	
		+ "JOIN l.unidadeFuncional u "
		+ "WHERE u.sigla = :sigla "
		+ "AND i.indPacienteInternado = 'S' "
		+ "ORDER BY i.paciente.nome")
public class Internacao implements Serializable {
	
	@Id
	private Integer seq;
	
	@Column(name = "dthr_internacao")
	private LocalDateTime dthrInternacao;
	
	@Column(name = "dthr_alta_medica")
	private LocalDateTime dthrAltaMedica;
	
	@Column(name = "ind_paciente_internado")
	private String indPacienteInternado;
	
	@ManyToOne
	@JoinColumn(name = "pac_codigo")
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "unf_seq")
	private UnidadeFuncional unidadeFuncional;
	
	@ManyToOne
	@JoinColumn(name = "lto_lto_id")
	private Leito leito;

	@ManyToOne
	@JoinColumn(name = "tam_codigo")
	private TipoAltaMedica tipoAltaMedica;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "agh.ain_cids_internacao", 
		joinColumns = { @JoinColumn(name = "int_seq") },
		inverseJoinColumns = { @JoinColumn(name = "cid_seq") })
	private List<Cid> cids;
	
	@ManyToOne
	@JoinColumn(name = "esp_seq")
	private Especialidade especialidade;

	@Transient
	private String pendencias;
	
	public String cidsStr() {
		String cidsStr = "";
		
		for (Cid cid : cids)
			cidsStr += (cid.getDescricao() + " ");
		
		return cidsStr;
	}
	
	public String unidadeInternacao() {
		if (unidadeFuncional != null)
			return unidadeFuncional.getDescricao();
		
		if (leito != null && leito.getUnidadeFuncional() != null)
			return leito.getUnidadeFuncional().getDescricao();
		
		return "";
	}
		
	public Integer getPermanencia() {
		if (dthrInternacao != null) 
			return Long.valueOf(dthrInternacao.until(LocalDateTime.now(), ChronoUnit.DAYS)).intValue();

		return 0;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((seq == null) ? 0 : seq.hashCode());
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
		Internacao other = (Internacao) obj;
		if (seq == null) {
			if (other.seq != null)
				return false;
		} else if (!seq.equals(other.seq))
			return false;
		return true;
	}
	
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public LocalDateTime getDthrInternacao() {
		return dthrInternacao;
	}

	public void setDthrInternacao(LocalDateTime dthrInternacao) {
		this.dthrInternacao = dthrInternacao;
	}

	public UnidadeFuncional getUnidadeFuncional() {
		return unidadeFuncional;
	}

	public void setUnidadeFuncional(UnidadeFuncional unidadeFuncional) {
		this.unidadeFuncional = unidadeFuncional;
	}

	public Leito getLeito() {
		return leito;
	}

	public void setLeito(Leito leito) {
		this.leito = leito;
	}

	public List<Cid> getCids() {
		return cids;
	}

	public void setCids(List<Cid> cids) {
		this.cids = cids;
	}

	public LocalDateTime getDthrAltaMedica() {
		return dthrAltaMedica;
	}

	public void setDthrAltaMedica(LocalDateTime dthrAltaMedica) {
		this.dthrAltaMedica = dthrAltaMedica;
	}

	public String getIndPacienteInternado() {
		return indPacienteInternado;
	}

	public void setIndPacienteInternado(String indPacienteInternado) {
		this.indPacienteInternado = indPacienteInternado;
	}

	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public TipoAltaMedica getTipoAltaMedica() {
		return tipoAltaMedica;
	}

	public void setTipoAltaMedica(TipoAltaMedica tipoAltaMedica) {
		this.tipoAltaMedica = tipoAltaMedica;
	}

	public String getPendencias() {
		return pendencias;
	}

	public void setPendencias(String pendencias) {
		this.pendencias = pendencias;
	}
	
}
