package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.Period;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(schema = "agh", name = "aip_pacientes")
@NamedQuery(name = "Paciente.findByProntuario",
	query = "SELECT p "
		+ "FROM Paciente p "
		+ "WHERE p.prontuario = :prontuario")
@NamedQuery(name = "Paciente.findByProntuarioIn",
	query = "SELECT p "
		+ "FROM Paciente p "
		+ "WHERE p.prontuario IN :prontuarios")
public class Paciente implements Serializable {

	@Id
	private Integer codigo;

	private Integer prontuario;

	private String nome;

	@Column(name = "nome_mae")
	private String nomeMae;

	@Column(name = "nome_pai")
	private String nomePai;

	@Column(name = "dt_nascimento")
	private LocalDate dtNascimento;

	@Column(name = "nro_cartao_saude")
	private Long cartaoSus;

	private String sexo;

	private String cor;

	@Column(name = "estado_civil")
	private String estadoCivil;

	@Column(name = "grau_instrucao")
	private String grauInstrucao;

	@Column(name = "ddd_fone_residencial")
	private String dddFoneResidencial;

	@Column(name = "ddd_fone_recado")
	private String dddFoneRecado;

	@Column(name = "fone_residencial")
	private String foneResidencial;

	@Column(name = "fone_recado")
	private String foneRecado;

	private Long cpf;

	@ManyToOne
	@JoinColumn(name = "nac_codigo")
	private Nacionalidade nacionalidade;

	@ManyToOne
	@JoinColumn(name = "cdd_codigo")
	private Cidade cidadeNaturalidade;

	@Transient
	private String naturalidade;

	@Transient
	private String procedencia;

	public Integer getIdade() {
		if (dtNascimento == null)
			return null;

		return Period.between(dtNascimento, LocalDate.now()).getYears();
	}

	public String getIdadeStr() {
		if (dtNascimento == null)
			return "";

		Period p = Period.between(dtNascimento, LocalDate.now());

		if (p.getYears() != 0)
			return String.valueOf(p.getYears()) + " anos";

		return String.valueOf(p.getMonths()) + " meses";
	}

	public String getCorApac() {
		if (cor == null)
			return "99";

		String codigo = null;
		switch (cor) {
			case "B":
				codigo = "01";
				break;
			case "P":
				codigo = "02";
				break;
			case "M": // PARDO
				codigo = "03";
				break;
			case "A":
				codigo = "04";
				break;
			default:
				codigo = "99";
				break;
		}

		return codigo;
	}
	
	public String getIniciaisNome() {
		if (nome == null || nome.isBlank())
			return "";

		String[] partes = nome.split("\\s+");
		String iniciais = "";
		for (int i = 0; i < partes.length; ++i) {
			iniciais += (partes[i].charAt(0) + ".");
		}

		return iniciais;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((codigo == null) ? 0 : codigo.hashCode());
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
		Paciente other = (Paciente) obj;
		if (codigo == null) {
			if (other.codigo != null)
				return false;
		} else if (!codigo.equals(other.codigo))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Paciente [codigo=" + codigo + "]";
	}

	public Integer getCodigo() {
		return codigo;
	}

	public void setCodigo(Integer codigo) {
		this.codigo = codigo;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(int prontuario) {
		this.prontuario = prontuario;
	}

	public LocalDate getDtNascimento() {
		return dtNascimento;
	}

	public void setDtNascimento(LocalDate dtNascimento) {
		this.dtNascimento = dtNascimento;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public String getNomeMae() {
		return nomeMae;
	}

	public void setNomeMae(String nomeMae) {
		this.nomeMae = nomeMae;
	}

	public String getNomePai() {
		return nomePai;
	}

	public void setNomePai(String nomePai) {
		this.nomePai = nomePai;
	}

	public Long getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(Long cartaoSus) {
		this.cartaoSus = cartaoSus;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Cidade getCidadeNaturalidade() {
		return cidadeNaturalidade;
	}

	public void setCidadeNaturalidade(Cidade cidadeNaturalidade) {
		this.cidadeNaturalidade = cidadeNaturalidade;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getEstadoCivil() {
		return estadoCivil;
	}

	public void setEstadoCivil(String estadoCivil) {
		this.estadoCivil = estadoCivil;
	}

	public String getGrauInstrucao() {
		return grauInstrucao;
	}

	public void setGrauInstrucao(String grauInstrucao) {
		this.grauInstrucao = grauInstrucao;
	}

	public String getDddFoneResidencial() {
		return dddFoneResidencial;
	}

	public void setDddFoneResidencial(String dddFoneResidencial) {
		this.dddFoneResidencial = dddFoneResidencial;
	}

	public String getDddFoneRecado() {
		return dddFoneRecado;
	}

	public void setDddFoneRecado(String dddFoneRecado) {
		this.dddFoneRecado = dddFoneRecado;
	}

	public String getFoneResidencial() {
		return foneResidencial;
	}

	public void setFoneResidencial(String foneResidencial) {
		this.foneResidencial = foneResidencial;
	}

	public String getFoneRecado() {
		return foneRecado;
	}

	public void setFoneRecado(String foneRecado) {
		this.foneRecado = foneRecado;
	}

	public Nacionalidade getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Nacionalidade nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getNaturalidade() {
		return naturalidade;
	}

	public void setNaturalidade(String naturalidade) {
		this.naturalidade = naturalidade;
	}

	public String getProcedencia() {
		return procedencia;
	}

	public void setProcedencia(String procedencia) {
		this.procedencia = procedencia;
	}

	public Long getCpf() {
		return cpf;
	}

	public void setCpf(Long cpf) {
		this.cpf = cpf;
	}

}
