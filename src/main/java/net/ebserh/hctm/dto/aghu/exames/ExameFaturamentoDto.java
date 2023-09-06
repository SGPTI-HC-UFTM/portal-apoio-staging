package net.ebserh.hctm.dto.aghu.exames;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class ExameFaturamentoDto implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private Integer solicitacao;
	
	private Integer itemSolicitacao;
	
	private String exame;
	
	private Long sigtap;
	
	private String cbo;
	
	private String cns;
	
	private String situacao;
	
	private Integer prontuario;
	
	private String nome;
	
	private String sexo;
	
	private LocalDate dataNascimento;
	
	private Integer idade;
	
	private String cor;
	
	private Integer codigoCor;
	
	private Long cartaoSus;
	
	private Integer codigoIbge;
	
	private Integer cep;
	
	private Integer tipoLogradouro;
	
	private String logradouro;
	
	private Integer numero;
	
	private String complemento;
	
	private String bairro;
	
	private Integer codigoEndereco;
	
	private LocalDateTime dthrExame;
	
	private Integer nacionalidade;
	
	private String origemPedido;
	
	private LocalDateTime dthrInternacao;
	
	private LocalDateTime dthrAlta;
	
	private String motivoAlta;

	/*
	 * Auto-generated
	 */
	@Override
	public String toString() {
		return "ExameFaturamentoDto [solicitacao=" + solicitacao + ", itemSolicitacao=" + itemSolicitacao + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(itemSolicitacao, solicitacao);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ExameFaturamentoDto other = (ExameFaturamentoDto) obj;
		return Objects.equals(itemSolicitacao, other.itemSolicitacao) && Objects.equals(solicitacao, other.solicitacao);
	}

	public Integer getSolicitacao() {
		return solicitacao;
	}

	public void setSolicitacao(Integer solicitacao) {
		this.solicitacao = solicitacao;
	}

	public Integer getItemSolicitacao() {
		return itemSolicitacao;
	}

	public void setItemSolicitacao(Integer itemSolicitacao) {
		this.itemSolicitacao = itemSolicitacao;
	}

	public String getExame() {
		return exame;
	}

	public void setExame(String exame) {
		this.exame = exame;
	}

	public Long getSigtap() {
		return sigtap;
	}

	public void setSigtap(Long sigtap) {
		this.sigtap = sigtap;
	}

	public String getCbo() {
		return cbo;
	}

	public void setCbo(String cbo) {
		this.cbo = cbo;
	}

	public Integer getProntuario() {
		return prontuario;
	}

	public void setProntuario(Integer prontuario) {
		this.prontuario = prontuario;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public LocalDate getDataNascimento() {
		return dataNascimento;
	}

	public void setDataNascimento(LocalDate dataNascimento) {
		this.dataNascimento = dataNascimento;
	}

	public Integer getIdade() {
		return idade;
	}

	public void setIdade(Integer idade) {
		this.idade = idade;
	}

	public String getCor() {
		return cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public Long getCartaoSus() {
		return cartaoSus;
	}

	public void setCartaoSus(Long cartaoSus) {
		this.cartaoSus = cartaoSus;
	}

	public Integer getCodigoIbge() {
		return codigoIbge;
	}

	public void setCodigoIbge(Integer codigoIbge) {
		this.codigoIbge = codigoIbge;
	}

	public Integer getTipoLogradouro() {
		return tipoLogradouro;
	}

	public void setTipoLogradouro(Integer tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
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

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public Integer getCodigoEndereco() {
		return codigoEndereco;
	}

	public void setCodigoEndereco(Integer codigoEndereco) {
		this.codigoEndereco = codigoEndereco;
	}

	public LocalDateTime getDthrExame() {
		return dthrExame;
	}

	public void setDthrExame(LocalDateTime dthrExame) {
		this.dthrExame = dthrExame;
	}

	public Integer getCep() {
		return cep;
	}

	public void setCep(Integer cep) {
		this.cep = cep;
	}

	public Integer getCodigoCor() {
		return codigoCor;
	}

	public void setCodigoCor(Integer codigoCor) {
		this.codigoCor = codigoCor;
	}

	public Integer getNacionalidade() {
		return nacionalidade;
	}

	public void setNacionalidade(Integer nacionalidade) {
		this.nacionalidade = nacionalidade;
	}

	public String getCns() {
		return cns;
	}

	public void setCns(String cns) {
		this.cns = cns;
	}

	public String getSituacao() {
		return situacao;
	}

	public void setSituacao(String situacao) {
		this.situacao = situacao;
	}

	public String getOrigemPedido() {
		return origemPedido;
	}

	public void setOrigemPedido(String origemPedido) {
		this.origemPedido = origemPedido;
	}

	public LocalDateTime getDthrInternacao() {
		return dthrInternacao;
	}

	public void setDthrInternacao(LocalDateTime dthrInternacao) {
		this.dthrInternacao = dthrInternacao;
	}

	public LocalDateTime getDthrAlta() {
		return dthrAlta;
	}

	public void setDthrAlta(LocalDateTime dthrAlta) {
		this.dthrAlta = dthrAlta;
	}

	public String getMotivoAlta() {
		return motivoAlta;
	}

	public void setMotivoAlta(String motivoAlta) {
		this.motivoAlta = motivoAlta;
	}
		
}
