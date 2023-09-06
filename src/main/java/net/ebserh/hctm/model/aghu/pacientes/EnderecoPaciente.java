package net.ebserh.hctm.model.aghu.pacientes;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinColumns;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(schema = "agh",
	name = "aip_enderecos_pacientes")
@NamedQueries({
	@NamedQuery(name = "EnderecoPaciente.findByProntuario",
			query = "SELECT e "
					+ "FROM EnderecoPaciente e "
					+ "WHERE e.paciente.prontuario = :prontuario "
					+ "ORDER BY e.enderecoPacientePk.seq")
})
public class EnderecoPaciente implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EmbeddedId
	private EnderecoPacientePK enderecoPacientePk;
	
	@ManyToOne
	@JoinColumn(name = "pac_codigo", insertable = false, updatable = false)
	private Paciente paciente;
	
	@ManyToOne
	@JoinColumn(name = "cdd_codigo")
	private Cidade cidade;
	
	@ManyToOne
	@JoinColumn(name = "bcl_clo_lgr_codigo")
	private Logradouro logradouro;
	
	@Column(name = "cep")
	private Integer cep;
	
	@Column(name = "nro_logradouro")
	private Integer numero;
	
	@Column(name = "compl_logradouro")
	private String complemento;
	
	@Column(name = "bairro")
	private String bairroNC; // Bairro não cadastrado
	
	@ManyToOne
	@JoinColumns({
			@JoinColumn(name = "bcl_bai_codigo", 
					referencedColumnName = "bai_codigo", 
					insertable = false, 
					updatable = false),
			@JoinColumn(name = "bcl_clo_cep", 
					referencedColumnName="clo_cep", 
					insertable=false, 
					updatable=false),
			@JoinColumn(name = "bcl_clo_lgr_codigo", 
					referencedColumnName="clo_lgr_codigo", 
					insertable=false, 
					updatable=false)
	})
	private BairroCepLogradouro bairroCepLogradouro;
	
	@Column(name = "cidade")
	private String municipio;
	
	@Column(name = "logradouro")
	private String endereco;
	
	@ManyToOne
	@JoinColumn(name = "bcl_bai_codigo")
	private Bairro bairro;
	
	@Column(name = "uf_sigla")
	private String uf;
		
	public Paciente getPaciente() {
		return paciente;
	}

	public void setPaciente(Paciente paciente) {
		this.paciente = paciente;
	}

	public Cidade getCidade() {
		return cidade;
	}

	public void setCidade(Cidade cidade) {
		this.cidade = cidade;
	}

	public Logradouro getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(Logradouro logradouro) {
		this.logradouro = logradouro;
	}

	public EnderecoPacientePK getEnderecoPacientePk() {
		return enderecoPacientePk;
	}

	public void setEnderecoPacientePk(EnderecoPacientePK enderecoPacientePk) {
		this.enderecoPacientePk = enderecoPacientePk;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Integer getNumero() {
		return numero;
	}

	public void setNumero(Integer numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getMunicipio() {
		return municipio;
	}

	public void setMunicipio(String municipio) {
		this.municipio = municipio;
	}

	public String getEndereco() {
		return endereco;
	}

	public void setEndereco(String endereco) {
		this.endereco = endereco;
	}

	public String getBairroNC() {
		return bairroNC;
	}

	public void setBairroNC(String bairroNC) {
		this.bairroNC = bairroNC;
	}

	public Bairro getBairro() {
		return bairro;
	}

	public void setBairro(Bairro bairro) {
		this.bairro = bairro;
	}

	public String getUf() {
		return uf;
	}

	public void setUf(String uf) {
		this.uf = uf;
	}

	public BairroCepLogradouro getBairroCepLogradouro() {
		return bairroCepLogradouro;
	}

	public void setBairroCepLogradouro(BairroCepLogradouro bairroCepLogradouro) {
		this.bairroCepLogradouro = bairroCepLogradouro;
	}
	
}
