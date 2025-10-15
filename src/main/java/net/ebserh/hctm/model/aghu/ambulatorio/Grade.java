package net.ebserh.hctm.model.aghu.ambulatorio;

import java.io.Serializable;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinColumns;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import net.ebserh.hctm.model.aghu.Especialidade;
import net.ebserh.hctm.model.aghu.UnidadeFuncional;
import net.ebserh.hctm.model.aghu.servidores.Servidor;


@Entity
@Table(schema = "agh", name = "aac_grade_agendamen_consultas")
public class Grade implements Serializable {

	@Id
	private Integer seq;
	
	@ManyToOne
	@JoinColumn(name = "usl_unf_seq")
	private UnidadeFuncional unidadeFuncional;
	
	@ManyToOne
	@JoinColumn(name = "esp_seq")
	private Especialidade especialidade;
	
	@ManyToOne
	@JoinColumns({
		@JoinColumn(name = "pre_ser_matricula", referencedColumnName = "matricula", nullable=true),
        @JoinColumn(name = "pre_ser_vin_codigo", referencedColumnName = "vin_codigo", nullable=true)
    }) 
	private Servidor servidor;

	@ManyToOne
	@JoinColumn(name = "eqp_seq")
	private Equipe equipe;
	
	/*
	 * Auto-generated
	 */
	public Integer getSeq() {
		return seq;
	}

	public void setSeq(Integer seq) {
		this.seq = seq;
	}

	public UnidadeFuncional getUnidadeFuncional() {
		return unidadeFuncional;
	}

	public void setUnidadeFuncional(UnidadeFuncional unidadeFuncional) {
		this.unidadeFuncional = unidadeFuncional;
	}

	public Especialidade getEspecialidade() {
		return especialidade;
	}

	public void setEspecialidade(Especialidade especialidade) {
		this.especialidade = especialidade;
	}

	public Servidor getServidor() {
		return servidor;
	}

	public void setServidor(Servidor servidor) {
		this.servidor = servidor;
	}

	public Equipe getEquipe() {
		return equipe;
	}

	public void setEquipe(Equipe equipe) {
		this.equipe = equipe;
	}

}
