package net.ebserh.hctm.dto.aghu.suprimentos;

import java.io.Serializable;
import java.math.BigDecimal;

public class ConsumoMaterialDto implements Serializable {

    private Integer codigoMaterial;

    private Integer codigoGrupo;

    private Integer codigoContabil;

    private String grupo;

    private String material;

    private String unidade;

    private BigDecimal valorUnitario;

    private Integer quantidadeConsumida;

    private String centroCusto;

    private String dataCompetencia;

	public Integer getCodigoMaterial() {
		return codigoMaterial;
	}

	public void setCodigoMaterial(Integer codigoMaterial) {
		this.codigoMaterial = codigoMaterial;
	}

	public Integer getCodigoGrupo() {
		return codigoGrupo;
	}

	public void setCodigoGrupo(Integer codigoGrupo) {
		this.codigoGrupo = codigoGrupo;
	}

	public Integer getCodigoContabil() {
		return codigoContabil;
	}

	public void setCodigoContabil(Integer codigoContabil) {
		this.codigoContabil = codigoContabil;
	}

	public String getGrupo() {
		return grupo;
	}

	public void setGrupo(String grupo) {
		this.grupo = grupo;
	}

	public String getMaterial() {
		return material;
	}

	public void setMaterial(String material) {
		this.material = material;
	}

	public String getUnidade() {
		return unidade;
	}

	public void setUnidade(String unidade) {
		this.unidade = unidade;
	}

	public BigDecimal getValorUnitario() {
		return valorUnitario;
	}

	public void setValorUnitario(BigDecimal valorUnitario) {
		this.valorUnitario = valorUnitario;
	}

	public Integer getQuantidadeConsumida() {
		return quantidadeConsumida;
	}

	public void setQuantidadeConsumida(Integer quantidadeConsumida) {
		this.quantidadeConsumida = quantidadeConsumida;
	}

	public String getCentroCusto() {
		return centroCusto;
	}

	public void setCentroCusto(String centroCusto) {
		this.centroCusto = centroCusto;
	}

	public String getDataCompetencia() {
		return dataCompetencia;
	}

	public void setDataCompetencia(String dataCompetencia) {
		this.dataCompetencia = dataCompetencia;
	}
    
}
