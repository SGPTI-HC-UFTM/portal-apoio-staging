package net.ebserh.hctm.dto.aghu.indicadores;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class IndicadorPreceptoriaDto {
	
	private BigInteger comPreceptoria;
	
	private BigInteger semPreceptoria;
	
	private List<String> ocorrencias;
	
	public IndicadorPreceptoriaDto() {
		comPreceptoria = BigInteger.valueOf(0);
		semPreceptoria = BigInteger.valueOf(0);
		ocorrencias = new ArrayList<String>();
	}
	
	public BigInteger getComPreceptoria() {
		return comPreceptoria;
	}

	public void setComPreceptoria(BigInteger comPreceptoria) {
		this.comPreceptoria = comPreceptoria;
	}

	public BigInteger getSemPreceptoria() {
		return semPreceptoria;
	}

	public void setSemPreceptoria(BigInteger semPreceptoria) {
		this.semPreceptoria = semPreceptoria;
	}

	public List<String> getOcorrencias() {
		return ocorrencias;
	}

	public void setOcorrencias(List<String> ocorrencias) {
		this.ocorrencias = ocorrencias;
	}
	
}
