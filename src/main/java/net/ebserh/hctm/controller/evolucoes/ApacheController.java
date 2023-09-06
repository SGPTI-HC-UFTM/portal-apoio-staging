package net.ebserh.hctm.controller.evolucoes;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import net.ebserh.hctm.exception.CustomRuntimeException;
import net.ebserh.hctm.model.aghu.pacientes.Paciente;
import net.ebserh.hctm.model.evolucoes.Apache2;
import net.ebserh.hctm.model.evolucoes.Apache2Opcao;
import net.ebserh.hctm.service.aghu.pacientes.PacientesService;
import net.ebserh.hctm.service.evolucoes.ApacheService;
import net.ebserh.hctm.util.FacesUtils;

@Named
@ViewScoped
public class ApacheController implements Serializable {

    @Inject
    private Logger logger;

    @Inject
    private PacientesService pacientesService;

    @Inject
    private ApacheService apacheService;

    private Integer prontuario;

    private Paciente paciente;

    private Apache2 apache2 = new Apache2();

    private List<Apache2Opcao> opcoesTemperatura;

    @PostConstruct
    public void init() {
        try {
            opcoesTemperatura = apacheService.buscaPorQuestao(1);
        } catch (Exception e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void buscaPaciente() {
		try {
			paciente = pacientesService.buscaPorProntuario(prontuario);
			if (paciente == null) {
				FacesUtils.showError("Nenhum paciente encontrado com o prontuário informado.");
				return;
			}
		} catch (CustomRuntimeException e) {
			FacesUtils.showError(e.getMessage());
		} catch (Exception e) {
			logger.log(Level.SEVERE, e.getMessage(), e);
			FacesUtils.showError("Ocorreu um erro ao pesquisar os pacientes.");
		}
    }

    public Integer getProntuario() {
        return prontuario;
    }

    public void setProntuario(Integer prontuario) {
        this.prontuario = prontuario;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public Apache2 getApache2() {
        return apache2;
    }

    public void setApache2(Apache2 apache2) {
        this.apache2 = apache2;
    }

    public List<Apache2Opcao> getOpcoesTemperatura() {
        return opcoesTemperatura;
    }

    public void setOpcoesTemperatura(List<Apache2Opcao> opcoesTemperatura) {
        this.opcoesTemperatura = opcoesTemperatura;
    }
    
}
